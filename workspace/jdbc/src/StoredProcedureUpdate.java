import java.sql.*;

public class StoredProcedureUpdate {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "{call upd_emp_salary(?, ?)}";
	
	public static void main(String[] args) {

		Connection conn = null;
		
		// 透過資料庫的預存程序(Stored Procedure)來下 SQL 指令，用來更新資料
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 透過 Connection 介面的 prepareCall() 方法，產生一個 CallableStatement 物件
			CallableStatement cal = conn.prepareCall(SQL);
			
			cal.setInt(1, 63000); // IN，從 JAVA 給資料庫
			cal.setInt(2, 1001); // IN，從 JAVA 給資料庫
			
			// 沒有 executeQuery() 跟 executeUpdate() 方法，只接用一個統稱 execute() 方法，無法產生物件去接收回傳資料
			cal.execute();
			
			System.out.println("資料更新成功!!");
			
			cal.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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