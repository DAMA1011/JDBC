import java.io.*;
import java.sql.*;

public class InsertBLOB {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "INSERT INTO testBLOB VALUES (?, ?, ?)";
	
	public static void main(String[] args) {

		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String inFile = "res/Tomcat.gif";
			
			FileInputStream fis = new FileInputStream(inFile);
			
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			pstat.setInt(1, 101);
			pstat.setString(2, inFile);
			pstat.setBinaryStream(3, fis);
			
			int count = pstat.executeUpdate();
			
			System.out.println("成功加入資料筆數: " + count);
			
			pstat.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}