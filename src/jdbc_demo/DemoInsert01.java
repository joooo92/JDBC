package jdbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DemoInsert01 {
	
	public static void main(String[] args) {
		
		// 입력될 값은 콘솔로 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("부서이름>");
		String name = scan.nextLine();
		
		System.out.print("메니저아이디>");
		String manager_id = scan.nextLine();
		
		System.out.print("로케이션아이디>");
		String location_id = scan.nextLine();
		
		
		//데이터베이스 불러오는 문장
		String url = "jdbc:oracle:thin:@localhost:1521: xe";
		String uid = "HR";
		String upw = "HR";
		
		//insert, update, delete는 ResultSet객체가 필요없음
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO DEPTS(DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)\r\n"
				+ "VALUES(DEPTS_SEQ.NEXTVAL, ? , ?, ?)";
		
		
		try {
			//1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. conn
			conn = DriverManager.getConnection(url, upw, upw);
		
			//3.pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name); //첫번째 물음표, name
			pstmt.setInt(2, Integer.parseInt(manager_id)); //두번째 물음표, manager_id //정수로 변경
			pstmt.setString(3, location_id); //세번째 물음표, location_id
			
			//4. sql실행
			int result = pstmt.executeUpdate();
			System.out.println(result);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
	}

}
