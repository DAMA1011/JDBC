import java.sql.*;

public class QueryFirstDemo {

	// 將定義的常數統一寫在最上層，可讀性高，好維護
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbctest";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String SQL = "SELECT empName, empSalary FROM employee";
	
	public static void main(String[] args) {

		Connection conn = null;
		
		// 靜態 SQL 指令查詢
		try {
			// 步驟一：透過 java.lang.Class 類別的 Class.forName() 方法，到 Driver Manager 註冊，
			Class.forName(JDBC_DRIVER);
			
			// 步驟二：透過 DriverManager 類別的 getConnection() 方法，將登入資訊傳給資料庫並且取得連線，產生一個 Connection 物件
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 步驟三：透過 Connection 介面的 createStatement() 方法，產生一個可以建立靜態 SQL 指令的 Statement 物件
			Statement stat = conn.createStatement();
			
			// 步驟四：透過 Statement 介面的 executeQuery() 方法，執行 SQL 指令，產生一個 ResultSet 物件，接收回傳的資料
			ResultSet res = stat.executeQuery(SQL);
			
			// 步驟五：透過 ResultSet 介面的 getXXX() 方法，將資料取出
			while (res.next()) {
				System.out.print("name = " + res.getString("empName")); // 這邊的 getXXX，使用表格欄位的名稱
				System.out.println(", salary = " + res.getInt("empSalary"));
			}
			
			// Statement、ResultSet 須關閉，歸還資源，先建的後關，後建的先關
			res.close();
			stat.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		
		// (重要) Connection 是極為重要的資源，一定要關閉，建議寫在 finally 裡面，使它必定執行
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