import java.sql.*;

public class QueryOneDemo {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "SELECT * FROM employee WHERE empName = ?";
	
	public static void main(String[] args) {
	
		Connection conn = null;
		
		// 動態 SQL 指令，單一條件查詢
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 透過 Connection 介面的 prepareStatement() 方法，產生一個 PreparedStatement 物件
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			// 這此設定查詢條件
			String empName = "Amy";
			
			pstat.setString(1, empName);
			
			ResultSet res = pstat.executeQuery();
			
			// 單一筆回傳資料，用 if 條件式做處理
			if (res.next()) {
				System.out.print("salary = " + res.getInt("empSalary"));
				System.out.println(", hiredate = " + res.getDate("empHiredate"));
			} else {
				System.out.printf("Name = %s, 查無此人%n", empName);
			}
			
            empName = "Mary";
			
			pstat.setString(1, empName);
			
			res = pstat.executeQuery();
			
			if (res.next()) {
				System.out.print("salary = " + res.getInt("empSalary"));
				System.out.println(", hiredate = " + res.getDate("empHiredate"));
			} else {
				System.out.printf("Name = %s, 查無此人%n", empName);
			}
						
			res.close();
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