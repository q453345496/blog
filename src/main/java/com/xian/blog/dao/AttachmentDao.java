package com.xian.blog.dao;

import java.util.List;
import java.util.Map;

import com.xian.blog.model.Attachment;

public interface AttachmentDao {

	public List<Attachment> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	public int update(Attachment attachment);

	public int save(Attachment attachment);

	public int delete(Long id);

	public Attachment get(Long id);
	
}
