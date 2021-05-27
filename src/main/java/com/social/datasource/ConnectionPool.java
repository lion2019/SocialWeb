package com.social.datasource;

import javax.sql.DataSource;

/**
 * 連線池
 */
public class ConnectionPool {

	private static DataSource dataSource;

	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void setDataSource(DataSource dataSource) {
		ConnectionPool.dataSource = dataSource;
	}
}
