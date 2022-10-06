import java.sql.*;

public class QueryMoreDemo {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "SELECT * FROM employee WHERE empID = ? AND empName = ?";
	
	public static void main(String[] args) {

		Connection conn = null;
		
		// 動態 SQL 指令，多條件查詢
		try {			
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 透過 Connection 介面的 prepareStatement() 方法，產生一個 PreparedStatement 物件
			PreparedStatement pstat = conn.prepareStatement(SQL);
			
			// 這此設定查詢條件
			int empID = 1003;
			String empName = "Amy";
			
			pstat.setInt(1, empID);			
			pstat.setString(2, empName);
			
			ResultSet res = pstat.executeQuery();
			
			// 單一筆回傳資料，用 if 條件式做處理
			if (res.next()) {
				System.out.print("salary = " + res.getInt("empSalary"));
				System.out.print(", hiredate = " + res.getDate("empHiredate"));
				System.out.println(", deptnum = " + res.getInt("empDeptnum"));
			} else {
				System.out.printf("ID = %d, Name = %s, 查無此人%n", empID, empName);
			}
			
		    empID = 1004;
			empName = "Kevin";
			
			pstat.setInt(1, empID);			
			pstat.setString(2, empName);
			
			res = pstat.executeQuery();
			
			if (res.next()) {
				System.out.print("salary = " + res.getInt("empSalary"));
				System.out.print(", hiredate = " + res.getDate("empHiredate"));
				System.out.println(", deptnum = " + res.getInt("empDeptnum"));
			} else {
				System.out.printf("ID = %d, Name = %s, 查無此人%n", empID, empName);
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