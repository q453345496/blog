package com.xian.blog.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

	public List<Attachment> list(Wrapper<Attachment> wrapper) {
		return attachmentDao.selectList(wrapper);
	}

	@Transactional
	public DataGridResult page(Page<Attachment> page, Wrapper<Attachment> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<Attachment> datas = attachmentDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	@Transactional
	public int update(Attachment attachment) {
		return attachmentDao.updateById(attachment);
	}

	@Transactional
	public int save(Attachment attachment) {
		return attachmentDao.insert(attachment);
	}

	@Transactional
	public int delete(Long id) {
		return attachmentDao.deleteById(id);
	}

	public Attachment get(Long id) {
		return attachmentDao.selectById(id);
	}

	@Transactional
	public Attachment upload(MultipartFile upfile, String bizId, String bizType, String type) {
		if (upfile == null) {
			throw new UploadException("empty upfile");
		}
		String originalFilename = upfile.getOriginalFilename();
		String suffix = FTPConstant.getSuffix(originalFilename);
		String title = FTPConstant.newTitle(suffix);
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
			FtpAdapter.close(ftpAdapter);
		}

	}

	@Transactional
	public Attachment captureRemoteData(String sourceUrl, String bizId, String bizType, String type) {
		if (HttpUtil.inHostList(sourceUrl)) {
			throw new UploadException("in our server");
		}
		try {
			InputStream inputStream = HttpUtil.get(sourceUrl);
			String suffix = FTPConstant.getSuffix(sourceUrl);
			String title = FTPConstant.newTitle(suffix);
			String localPath = FTPConstant.getSavepPath(bizId, bizType, type, title);
			FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
			try {
				ftpAdapter.upload(inputStream, localPath);
				Attachment attachment = new Attachment();
				attachment.setType(type);
				attachment.setName(title);
				attachment.setPath(localPath);
				attachment.setSize(Long.valueOf(inputStream.available()));
				attachment.setSourceURL(sourceUrl);
				attachment.setBizId(bizId);
				attachment.setBizType(bizType);
				save(attachment);
				return attachment;
			} finally {
				FtpAdapter.close(ftpAdapter);
			}

		} catch (Exception e) {
			LOG.error(String.format("captureRemoteData error:[sourceUrl:%s,bizId:%s,bizType:%s,type:%s]", sourceUrl,
					bizId, bizType, type), e);
			throw new UploadException(e.getMessage());
		}
	}

}
