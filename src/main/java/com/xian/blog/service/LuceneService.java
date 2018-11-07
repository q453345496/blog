package com.xian.blog.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.Blog;

public class LuceneService {
	private static final Logger LOG = LoggerFactory.getLogger(LuceneService.class);

	public static final String PATH_INDEX = "D://lucenedata";
	public static FSDirectory DIR;
	static {
		try {
			DIR = FSDirectory.open(Paths.get(PATH_INDEX));
		} catch (IOException e) {
			LOG.error("open directory error", e);
		}
	}

	public static void addIndex(Blog blog) {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		try (IndexWriter writer = new IndexWriter(DIR, iwc)) {
			Document document = new Document();
			document.add(new StringField("id", blog.getId().toString(), Store.YES));
			document.add(new TextField("title", blog.getTitle(), Store.YES));
			document.add(new TextField("content", blog.getContentNoTag(), Store.YES));
			writer.addDocument(document);
		} catch (IOException e) {
			LOG.error("add Index error", e);
		}
	}

	public static void updateIndex(Blog blog) {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		try (IndexWriter writer = new IndexWriter(DIR, iwc)) {
			Document document = new Document();
			document.add(new StringField("id", blog.getId().toString(), Store.YES));
			document.add(new TextField("title", blog.getTitle(), Store.YES));
			document.add(new TextField("content", blog.getContentNoTag(), Store.YES));

			writer.updateDocument(new Term("id", blog.getId().toString()), document);
		} catch (IOException e) {
			LOG.error("add Index error", e);
		}
	}

	public static void deleteIndex(Long id) {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		try (IndexWriter writer = new IndexWriter(DIR, iwc)) {
			writer.deleteDocuments(new Term("id", id.toString()));
		} catch (IOException e) {
			LOG.error("add Index error", e);
		}
	}

	public static void cleanAll() {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		try (IndexWriter writer = new IndexWriter(DIR, iwc)) {
			writer.deleteAll();
		} catch (IOException e) {
			LOG.error("clean All Index error", e);
		}

	}

	public static void search(String q, Page<Blog> pageInfo, boolean isHighlight) {
		List<Blog> datas = new ArrayList<>();
		long total = 0;
		if (StringUtils.isNotBlank(q)) {
			try (IndexReader reader = DirectoryReader.open(DIR)) {
				SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
				IndexSearcher is = new IndexSearcher(reader);
				BooleanQuery.Builder b = new BooleanQuery.Builder();
				QueryParser parser = new QueryParser("title", analyzer);
				QueryParser parser2 = new QueryParser("content", analyzer);
				b.add(new BooleanClause(parser.parse(q), BooleanClause.Occur.SHOULD));
				b.add(parser2.parse(q), BooleanClause.Occur.SHOULD);
				BooleanQuery query = b.build();

				TopFieldCollector topFieldCollector = TopFieldCollector.create(new Sort(),
						pageInfo.getCurrent() * pageInfo.getSize(), false, false, false);
				is.search(b.build(), topFieldCollector);
				TopDocs hits = topFieldCollector.topDocs((pageInfo.getCurrent() - 1) * pageInfo.getSize(),
						pageInfo.getSize());

				Highlighter highlighter = null;
				if (isHighlight) {
					QueryScorer scorer = new QueryScorer(query);
					Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
					SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span class='highlight'>",
							"</span>");
					highlighter = new Highlighter(simpleHTMLFormatter, scorer);
					highlighter.setTextFragmenter(fragmenter);
				}

				for (ScoreDoc scoreDoc : hits.scoreDocs) {
					Document doc = is.doc(scoreDoc.doc);
					Blog blog = new Blog();
					blog.setId(Long.parseLong(doc.get("id")));
					blog.setTitle(doc.get("title"));
					blog.setSummary(StringUtils.substring(doc.get("content"), 0, 200));
					if (isHighlight) {
						String hTitle = highlighter.getBestFragment(analyzer, "title", doc.get("title"));
						if (StringUtils.isNotBlank(hTitle)) {
							blog.setTitle(hTitle);
						}
						String hContent = highlighter.getBestFragment(analyzer, "content", doc.get("content"));
						if (StringUtils.isNotBlank(hContent)) {
							blog.setSummary(StringUtils.substring(hContent, 0, 200));
						}
					}
					datas.add(blog);
				}
				total = hits.totalHits;
			} catch (Exception e) {
				LOG.error("search error", e);
				total = 0;
				datas = Collections.emptyList();
			}
		}
		pageInfo.setTotal(total);
		pageInfo.setRecords(datas);
	}

	public static void main(String[] args) {
		search("的", new Page<>(1, 3), false);
		search("的", new Page<>(2, 3), false);
		search("的", new Page<>(3, 3), false);
		search("的", new Page<>(4, 3), false);
	}

}
