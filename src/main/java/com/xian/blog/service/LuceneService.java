package com.xian.blog.service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			document.add(new TextField("content", blog.getContent(), Store.YES));
			document.add(new StoredField("thumb", blog.getThumb()));

			writer.addDocument(document);
		} catch (IOException e) {
			LOG.error("addIndex error", e);
		}

	}

	public static List<Blog> search(String q) {
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

		try (IndexReader reader = DirectoryReader.open(DIR)) {
			IndexSearcher is = new IndexSearcher(reader);
			BooleanQuery.Builder b = new BooleanQuery.Builder();
			QueryParser parser = new QueryParser("title", analyzer);
			QueryParser parser2 = new QueryParser("content", analyzer);
			b.add(parser.parse(q), BooleanClause.Occur.SHOULD);
			b.add(parser2.parse(q), BooleanClause.Occur.SHOULD);
			System.out.println(b.build());
			List<Blog> datas = new ArrayList<>();
			TopDocs hits = is.search(b.build(), 100);
			
			QueryScorer scorer = new QueryScorer(parser.parse(q));
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter);
			
			
			
			for (ScoreDoc scoreDoc : hits.scoreDocs) {
				Document doc = is.doc(scoreDoc.doc);
				Blog blog = new Blog();
				blog.setId(Long.parseLong(doc.get("id")));
				blog.setTitle(doc.get("title"));
				blog.setContent(doc.get("content"));
				blog.setThumb(doc.get("thumb"));
				datas.add(blog);
				
				String hTitle = highlighter.getBestFragment(analyzer, "content", doc.get("content"));
				System.out.println(hTitle);
			}
			return datas;
		} catch (Exception e) {
			LOG.error("search error", e);
		}
		return null;

	}
}
