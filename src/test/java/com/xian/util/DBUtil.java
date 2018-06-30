package com.xian.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtil {
	private static DataSource ds = null;
	private static final Logger LOG = LoggerFactory.getLogger(DBUtil.class);

	// TODO 测试方便
	static {
		init();
	}

	public static void init() {
		InputStream in = null;
		try {
			in = DBUtil.class.getResourceAsStream("/jdbc.properties");
			Properties props = new Properties();
			props.load(in);
			ds = DruidDataSourceFactory.createDataSource(props);
			test();
		} catch (Exception ex) {
			LOG.error("init error", ex);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void test() {
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			st = connection.createStatement();
			rs = st.executeQuery("SELECT 1 ");
			while (rs.next()) {
				LOG.info(rs.getLong(1)+"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(st);
			close(connection);
		}
	}

	private static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void close(Connection con) {
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement s) {
		if (null != s) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
	}
}
