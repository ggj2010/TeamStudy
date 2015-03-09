package com.team.gaoguangjin.jdbc.transaction.cglib;

import java.sql.SQLException;

public interface TransactionInterface {
	
	void insert() throws SQLException;
	
	void insertErrow() throws SQLException;
}
