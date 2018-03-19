package com.xian.blog.service;

import java.net.HttpURLConnection;
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
import com.xian.blog.constants.FTPConstant;
import com.xian.blog.dao.AttachmentDao;
import com.xian.blog.exception.UploadException;
import com.xian.blog.model.Attachment;
import com.xian.blog.util.FtpAdapter;
import com.xian.blog.util.HttpUtil;

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
		String suffix = FTPConstant.getSuffix(originalFilename);
		String title = FTPConstant.getTitle(suffix);
		String localPath = FTPConstant.getSavepPath(bizId, bizType, type, title);

		FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
		try {
			ftpAdapter.upload(upfile.getInputStream(), localPath);
			Attachment attachment = new Attachment();
			attachment.setType(type);
			attachment.setName(title);
			attachment.setPath(localPath);
			attachment.setSize(upfile.getSize());
			attachment.setSourceURL(originalFilename);
			attachment.setBizId(bizId);
			attachment.setBizType(bizType);
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

	@Transactional
	public Attachment captureRemoteData(String sourceUrl, String bizId, String bizType, String type) {
		if (HttpUtil.inHostList(sourceUrl)) {
			throw new UploadException("in our server");
		}
		HttpURLConnection connection = null;
		try {
			connection = HttpUtil.getConnection(sourceUrl);
			if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
				LOG.error("Error Response Code:" + connection.getResponseCode());
				throw new UploadException("Error Response Code");
			}
			String suffix = FTPConstant.getSuffix(sourceUrl);
			String title = FTPConstant.getTitle(suffix);
			String localPath = FTPConstant.getSavepPath(bizId, bizType, type, title);
			FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
			try {
				ftpAdapter.upload(connection.getInputStream(), localPath);
				Attachment attachment = new Attachment();
				attachment.setType(type);
				attachment.setName(title);
				attachment.setPath(localPath);
				attachment.setSize((long) connection.getContentLength());
				attachment.setSourceURL(sourceUrl);
				attachment.setBizId(bizId);
				attachment.setBizType(bizType);
				save(attachment);
				return attachment;
			} finally {
				FtpAdapter.closeFtpAdapter(ftpAdapter);
			}

		} catch (Exception e) {
			LOG.error(String.format("captureRemoteData error:[sourceUrl:%s,bizId:%s,bizType:%s,type:%s]", sourceUrl,
					bizId, bizType, type), e);
			throw new UploadException(e.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
