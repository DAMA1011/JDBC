import java.sql.*;

public class UpdateDemo {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "UPDATE employee SET empSalary = ? WHERE empName = ?";
	
	public static void main(String[] args) {

		Connection conn = null;
		
		// 更新資料庫的資料
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 透過 Connection 介面的 prepareStatement() 方法，產生一個 PreparedStatement 物件
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			// 更新項目的內容，要跟資料庫表格符合
			pstat.setInt(1, 65000);
			pstat.setString(2, "Tina");
			
			// executeUpdate() 方法，回傳的是成功處理資料的數目(int)
			int count = pstat.executeUpdate();
			
			System.out.println("成功更新資料筆數: " + count);
			
			pstat.close();
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