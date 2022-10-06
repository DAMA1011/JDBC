import java.sql.*;

public class BatchUpdateTest {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "Passw0rd!";
	private static final String QUERY = "SELECT empno, salary FROM employee";
	private static final String UPDATE = "UPDATE employee SET salary = ? WHERE empno = ?";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(QUERY);

			ResultSet rs = pstmt.executeQuery();

			pstmt = conn.prepareStatement(UPDATE);

			while (rs.next()) {
				pstmt.setInt(1, rs.getInt("salary") + 2000);
				pstmt.setString(2, rs.getString("empno"));
				pstmt.addBatch();
			}
			pstmt.executeBatch();

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}