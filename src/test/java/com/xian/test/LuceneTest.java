package com.xian.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.model.Blog;
import com.xian.blog.service.LuceneService;

public class LuceneTest {
	private static long i = 1;

	public static void main(String[] args) throws IOException {
		//		update();
		//		delete();
		Page<Blog> page = new Page<>(1, 100);
		LuceneService.search("java", page, false);
		System.out.println(page.getTotal());
		for (Blog blog : page.getRecords()) {
			System.out.println(blog.getId() + "\t" + blog.getTitle() + "\t" + blog.getSummary());
		}
		//		LuceneService.cleanAll();
		//		init();
	}

	public static void delete() {
		LuceneService.deleteIndex(73l);
	}

	public static void update() {
		Blog blog = new Blog();
		blog.setId(73l);
		blog.setTitle("HttpUtil.java");
		blog.setContent("D:\\workspace\\blog\\src\\main\\java\\com\\xian\\blog\\util\\HttpUtilq.java");
		LuceneService.updateIndex(blog);
	}

	public static void init() throws IOException {
		Path path = Paths.get("D:\\workspace\\blog\\src\\main\\java");
		root(path);
		System.out.println(i);
	}

	public static void root(Path path) throws IOException {
		Files.list(path).forEach(new Consumer<Path>() {
			@Override
			public void accept(Path p) {
				if (p.toFile().isDirectory()) {
					try {
						root(p);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(p.toAbsolutePath());
					Blog blog = new Blog();
					blog.setId(i++);
					blog.setTitle(p.getFileName().toString());
					blog.setContent(p.toAbsolutePath().toString());
					LuceneService.addIndex(blog);
				}
			}
		});
	}

}
