package com.xian.blog.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xian.blog.common.DataGridResult;
import com.xian.blog.dao.ProxyServerDao;
import com.xian.blog.model.ProxyServer;

@Service
@Transactional
public class ProxyServerService {

	@Resource
	private ProxyServerDao proxyServerDao;

	public List<ProxyServer> list(Wrapper<ProxyServer> wrapper) {
		return proxyServerDao.selectList(wrapper);
	}

	public DataGridResult page(Page<ProxyServer> page, Wrapper<ProxyServer> wrapper) {
		DataGridResult vo = new DataGridResult();
		List<ProxyServer> datas = proxyServerDao.selectPage(page, wrapper);
		vo.setTotal(page.getTotal());
		vo.setRows(datas);
		return vo;
	}

	public int update(ProxyServer proxyServer) {
		return proxyServerDao.updateById(proxyServer);
	}

	public int save(ProxyServer proxyServer) {
		return proxyServerDao.insert(proxyServer);
	}

	public int delete(Long id) {
		return proxyServerDao.deleteById(id);
	}

	public int delete(Long[] ids) {
		return proxyServerDao.deleteBatchIds(Arrays.asList(ids));
	}

	public ProxyServer get(Long id) {
		return proxyServerDao.selectById(id);
	}

	public boolean insertBatch(List<ProxyServer> entityList) {
		try (SqlSession batchSqlSession = SqlHelper.sqlSessionBatch(ProxyServer.class)) {
			int size = entityList.size();
			int batchSize = 50;
			String sqlStatement = SqlHelper.table(ProxyServer.class).getSqlStatement(SqlMethod.INSERT_ONE.getMethod());
			for (int i = 0; i < size; i++) {
				batchSqlSession.insert(sqlStatement, entityList.get(i));
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
			}
			batchSqlSession.flushStatements();
		} catch (Throwable e) {
			throw new MybatisPlusException("Error: Cannot execute insertBatch Method. Cause", e);
		}
		return true;
	}

	public void saveNotExistByIp(ProxyServer proxyServer) {
		List<Object> list = proxyServerDao.selectObjs(//
				new EntityWrapper<ProxyServer>()//
						.eq("ip", proxyServer.getIp())//
						.eq("protocol", proxyServer.getProtocol())//
		);
		if (list.isEmpty()) {
			proxyServerDao.insert(proxyServer);
		}
	}
}
