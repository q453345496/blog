package com.xian.blog.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.constants.FTPConstant;
import com.xian.blog.dao.BlogTypeDao;
import com.xian.blog.exception.FtpException;
import com.xian.blog.model.BlogType;
import com.xian.blog.util.FtpAdapter;

@Service
@Transactional
public class BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

	public List<BlogType> list(Wrapper<BlogType> wrapper) {
		return blogTypeDao.selectList(wrapper);
	}

	public DataGridResult page(Page<BlogType> page, Wrapper<BlogType> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<BlogType> datas = blogTypeDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public int update(BlogType blogType, MultipartFile img) {
		if (img != null && img.getSize() > 0) {
			FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
			String name = FTPConstant.newTitle(img);
			try {
				ftpAdapter.upload(img.getInputStream(), name, FTPConstant.PATH_BLOG_TYPE);
				blogType.setImgPath(FTPConstant.PATH_BLOG_TYPE + "/" + name);
			} catch (Exception e) {
				throw new FtpException("上传失败", e);
			} finally {
				FtpAdapter.close(ftpAdapter);
			}
		}
		return blogTypeDao.updateById(blogType);
	}

	public int save(BlogType blogType, MultipartFile img) {
		if (img != null && img.getSize() > 0) {
			FtpAdapter ftpAdapter = FtpAdapter.getAndConnect();
			String name = FTPConstant.newTitle(img);
			try {
				ftpAdapter.upload(img.getInputStream(), name, FTPConstant.PATH_BLOG_TYPE);
				blogType.setImgPath(FTPConstant.PATH_BLOG_TYPE + "/" + name);
			} catch (IOException e) {
				throw new FtpException("上传失败", e);
			}
		}
		return blogTypeDao.insert(blogType);
	}

	public int delete(Long id) {
		return blogTypeDao.deleteById(id);
	}

	public BlogType get(Long id) {
		return blogTypeDao.selectById(id);
	}
	
	public void delete(Long[] ids) {
		blogTypeDao.deleteBatchIds(Arrays.asList(ids));
	}
	
}
