package jdbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DemoUpdate01 {
	public static void main(String[] args) {
		
		//부서아이디, 부서이름을 입력받아서 해당부서의 이름을 변경하는 update구문을 작성합니다.
		
		Scanner scan = new Scanner(System.in);
		System.out.print("부서아이디>");
		String id = scan.nextLine();
		
		System.out.print("부서이름>");
		String name = scan.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE DEPTS\r\n"
				+ "SET DEPARTMENT_NAME = ?\r\n"
				+ "WHERE DEPARTMENT_ID = ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521: xe";
		String uid = "HR";
		String upw = "HR";
		
		
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, upw, upw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name ); 
			pstmt.setString(2, id);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result);
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
