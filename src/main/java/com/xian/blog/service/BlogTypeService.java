package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.constants.FTPConstant;
import com.xian.blog.dao.BlogTypeDao;
import com.xian.blog.exception.CheckException;
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
		BlogType parent = blogTypeDao.selectById(blogType.getParentId());
		if (!parent.getParent()) {
			parent.setParent(true);
			blogTypeDao.updateById(parent);
		}
		blogType.setParent(false);
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
		return blogTypeDao.insert(blogType);
	}

	public void delete(Long id) {
		BlogType blogType = blogTypeDao.selectById(id);
		if (blogType != null) {
			if (blogType.getParent()) {
				throw new CheckException("该节点存在子节点，无法删除");
			}
			if (-1 == blogType.getParentId()) {
				throw new CheckException("根节点无法删除");
			}
			blogTypeDao.deleteById(id);
			int subCount = getSubCount(blogType.getParentId());
			if (subCount == 0) {
				BlogType parent = new BlogType();
				parent.setId(blogType.getParentId());
				parent.setParent(false);
				blogTypeDao.updateById(parent);
			}
		}
	}

	public int delete(Long[] ids) {
		return blogTypeDao.deleteBatchIds(Arrays.asList(ids));
	}

	public BlogType get(Long id) {
		return blogTypeDao.selectById(id);
	}

	public int getSubCount(Long parentId) {
		return blogTypeDao.selectCount(new EntityWrapper<BlogType>().eq("parent_id", parentId));
	}

	public BlogType getByCode(String code) {
		BlogType blogType = new BlogType();
		blogType.setCode(code);
		return blogTypeDao.selectOne(blogType);
	}
	
	public List<BlogType> listGroup(){
		return blogTypeDao.listGroup();
	}
}
