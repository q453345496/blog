package com.xian.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.xian.blog.common.DataGridResult;
import com.xian.blog.constants.UEditorConstant;
import com.xian.blog.dao.AttachmentDao;
import com.xian.blog.exception.UploadException;
import com.xian.blog.model.Attachment;
import com.xian.blog.util.FtpAdapter;

@Service
public class AttachmentService {
	private static final Logger LOG = LoggerFactory.getLogger(AttachmentService.class);

	@Resource
	private AttachmentDao attachmentDao;

	public List<Attachment> list(Map<String, Object> map) {
		return attachmentDao.list(map);
	}

	@Transactional
	public DataGridResult page(Map<String, Object> map) {
		DataGridResult vo = new DataGridResult();
		vo.setTotal(getTotal(map));
		vo.setRows(list(map));
		return vo;
	}

	public Integer getTotal(Map<String, Object> map) {
		return attachmentDao.getTotal(map);
	}

	@Transactional
	public int update(Attachment attachment) {
		attachment.setModifyTime(new Date());
		return attachmentDao.update(attachment);
	}

	@Transactional
	public int save(Attachment attachment) {
		Date now = new Date();
		attachment.setCreateTime(now);
		attachment.setModifyTime(now);
		return attachmentDao.save(attachment);
	}

	@Transactional
	public int delete(Long id) {
		return attachmentDao.delete(id);
	}

	public Attachment get(Long id) {
		return attachmentDao.get(id);
	}

	@Transactional
	public Attachment upload(MultipartFile upfile, String bizId, String bizType, String type) {
		if (upfile == null) {
			throw new UploadException("empty upfile");
		}
		String originalFilename = upfile.getOriginalFilename();
		String suffix = UEditorConstant.getSuffix(upfile.getContentType());
		String title = getTitle(suffix);
		String localPath = getSavepPath(bizId, bizType, type, title);

		FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
		try {
			ftpAdapter.upload(upfile.getInputStream(), localPath);
			Attachment attachment = new Attachment();
			attachment.setType(type);
			attachment.setName(title);
			attachment.setPath(localPath);
			attachment.setSize(upfile.getSize());
			attachment.setSourceURL(originalFilename);
			save(attachment);
			return attachment;
		} catch (Exception e) {
			String msg = String.format("上传异常[file=%s,path=%s]", originalFilename, localPath);
			LOG.error(msg, e);
			throw new UploadException(msg);
		} finally {
			FtpAdapter.closeFtpAdapter(ftpAdapter);
		}

	}

	public static String getTitle(String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis())//
				.append((Math.random() + "").substring(2, 8))//随机六位
				.append(suffix);
		return sb.toString();
	}

	public static String getSavepPath(String bizId, String bizType, String type, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(bizType)//
				.append("/").append(bizId)//
				.append("/").append(type)//
				.append("/").append(name);//
		return sb.toString();
	}
}
