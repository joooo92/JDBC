package jdbc.demo.mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptsDAO {

	//맴버변수
	//데이터베이스 불러오는 문장
	private String url = DBProperties.url;
	private String uid = DBProperties.uid;
	private String upw = DBProperties.upw;

	//생성자
	public DeptsDAO() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("CLASS FOR NAME ERR");
		}
	}
	
	//메서드 (데이터베이스 결과가 1행 -> VO를 반환
	// 		데이터베이스 결과가 여러행 -> List<VO>를 반환)
	
	public int /*void*/ insertDepts(DeptsVO vo) {

	      int result = 0; //0이 반환되면 실패, 1이 반환되면 성공

	      //insert, update, delete는 ResultSet 객체가 필요없음
	      Connection conn = null;
	      PreparedStatement pstmt = null;


	      String sql = "INSERT INTO DEPTS(DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID) "
	            + "VALUES(depts_seq.nextval, ?, ?, ?)";

	      try {

	         //2 conn
	         conn = DriverManager.getConnection(url, uid, upw);

	         //3 pstmt
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, vo.getDepartment_name());
	         pstmt.setInt(2, vo.getManager_id()); //정수로 변형
	         pstmt.setInt(3, vo.getLocation_id()); //자동형변환

	         //4 sql실행
	         result = pstmt.executeUpdate(); //성공하면 1, 실패하면 0

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {

	         try {
	            conn.close();
	            pstmt.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }


	      return result;
	   }
	
	
	
	
	
	
	public List<DeptsVO> getDepts(String name){
		
		//값을 반환할 리스트를 생성
		List<DeptsVO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM DEPTS WHERE DEPARTMENT_NAME = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;



		try {
			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				int department_id = rs.getInt("department_id");
				String department_name = rs.getString("department_name");
				int manager_id = rs.getInt("manager_id");
				int location_id = rs.getInt("location_id");

				//1행에 대한 값을 저장
				DeptsVO vo= new DeptsVO(department_id,department_name,manager_id,department_name);
				
				//리스트에 vo를 추가
				list.add(vo);
				
			}

		


		} catch (Exception e) {
			e.printStackTrace();
		}


		return list;







	}

}
