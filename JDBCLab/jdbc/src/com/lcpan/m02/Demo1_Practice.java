package com.lcpan.m02;

import java.sql.*; // 導入 java.sql 這個 API
/*
  Provides the API for accessing and processing data stored in a 
  data source (usually a relational database) using the Java programming language.)
*/
public class Demo1_Practice {
	
	public static void main(String[] args) {
	 
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
