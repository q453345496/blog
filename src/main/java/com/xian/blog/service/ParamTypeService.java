package com.xian.blog.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ParamTypeDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.ParamType;

@Service
@Transactional
public class ParamTypeService {

	@Resource
	private ParamTypeDao paramTypeDao;

	public List<ParamType> list(Wrapper<ParamType> wrapper) {
		return paramTypeDao.selectList(wrapper);
	}

	public DataGridResult page(Page<ParamType> page, Wrapper<ParamType> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<ParamType> datas = paramTypeDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public int update(ParamType paramType) {
		ParamType byCode = getByCode(paramType.getCode());
		if (byCode != null && !Objects.equals(paramType.getId(), byCode.getId())) {
			throw new CheckException("code已经存在:" + paramType.getCode());
		}
		return paramTypeDao.updateById(paramType);
	}

	public int save(ParamType paramType) {
		ParamType byCode = getByCode(paramType.getCode());
		if (byCode != null) {
			throw new CheckException("code已经存在:" + paramType.getCode());
		}
		return paramTypeDao.insert(paramType);
	}

	public int delete(Long id) {
		return paramTypeDao.deleteById(id);
	}

	public ParamType get(Long id) {
		return paramTypeDao.selectById(id);
	}

	public ParamType getByCode(String code) {
		ParamType paramType = new ParamType();
		paramType.setCode(code);
		return paramTypeDao.selectOne(paramType);
	}
}
