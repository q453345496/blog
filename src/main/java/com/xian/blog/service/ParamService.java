package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ParamDao;
import com.xian.blog.exception.CheckException;
import com.xian.blog.model.Param;

@Service
@Transactional
public class ParamService {

	@Resource
	private ParamDao paramDao;

	public List<Param> list(Wrapper<Param> wrapper) {
		return paramDao.selectList(wrapper);
	}

	public DataGridResult page(Page<Param> page, Wrapper<Param> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<Param> datas = paramDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public int update(Param param) {
		Param byCode = get(param.getTypeCode(), param.getKey());
		if (byCode != null && !Objects.equals(param.getId(), byCode.getId())) {
			throw new CheckException("key已经存在:" + param.getKey());
		}
		return paramDao.updateById(param);
	}

	public int save(Param param) {
		Param byCode = get(param.getTypeCode(), param.getKey());
		if (byCode != null) {
			throw new CheckException("key已经存在:" + param.getKey());
		}
		return paramDao.insert(param);
	}

	public int delete(Long[] ids) {
		return paramDao.deleteBatchIds(Arrays.asList(ids));
	}

	public Param get(Long id) {
		return paramDao.selectById(id);
	}

	public Param get(String typeCode, String key) {
		Param byCode = new Param();
		byCode.setKey(key);
		byCode.setTypeCode(typeCode);
		return paramDao.selectOne(byCode);
	}
}
