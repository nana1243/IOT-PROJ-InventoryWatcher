package top.test;
import java.sql.DriverManager;
import java.sql.SQLException;
public class OracleTest {
	

		public static void main(String[] args) {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@15.165.163.102:1521:XE";

			String user = "TOP";
			String password = "111111";
			try {
				Class.forName(driver);
				System.out.println("jdbc driver 로딩 성공");
				DriverManager.getConnection(url, user, password);
				System.out.println("오라클 연결 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("jdbc driver 로딩 실패");
			} catch (SQLException e) {
				System.out.println("오라클 연결 실패");
			}
		}
	}


