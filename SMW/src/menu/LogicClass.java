package menu;

import java.sql.*;
import java.util.*;

public class LogicClass {

	public void InsertPlayer() {
		
		Scanner scan = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Player(id,password,name,position,age,gender,do,si) VALUE(?,?,?,?,?,?,?,?)";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(
						"jdbc:mysql://192.168.246.5:4567/SoccerMatchingWeb","parksh", "1234");
			pstmt = con.prepareStatement(sql);
			
			System.out.println("회원정보를 입력하시오.");
			System.out.printf("ID:");
			String id = scan.next();
			System.out.printf("P/W:");
			String password = scan.next();
			System.out.printf("이름:");
			String name = scan.next();
			System.out.printf("포지션:");
			String position = scan.next();
			System.out.printf("나이:");
			int age = scan.nextInt();
			System.out.printf("성별:");
			String gender = scan.next();
			System.out.printf("주소(도):");
			String doo = scan.next();
			System.out.printf("주소(시):");
			String si = scan.next();
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, position);
			pstmt.setInt(5, age);
			pstmt.setString(6, gender);
			pstmt.setString(7, doo);
			pstmt.setString(8, si);
			
			int cnt = pstmt.executeUpdate(); 
			System.out.println(cnt + "명이 회원가입되었습니다.");	
			
			scan.close();
		}catch(Exception e) {System.out.println(e);}
		finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {}	
		}
	}
	////////////////////////////////////////////////////////////////////
	public void SelectPlayer() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM Player";
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(
						"jdbc:mysql://192.168.246.5:4567/SoccerMatchingWeb","parksh", "1234");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			while (rs.next()) {
				String id = rs.getString("id"); 
				String name = rs.getString("name"); 
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String position = rs.getString("position");
				
				System.out.println(id + "\t" + name + "\t" + age + "\t" + gender+ "\t"+position);
			}
		}catch(Exception e) {System.out.println(e);}
		finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {}
		}	
	}

////////////////////////////////////////////////////////////////////
	public void FindPlayer() {
		Scanner scan = new Scanner(System.in);

		String sql = "SELECT id,name,age,gender,position" + "FROM Player WHERE name = ";
				 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(
						"jdbc:mysql://192.168.246.5:4567/SoccerMatchingWeb","parksh", "1234");
			
			System.out.print("검색할 선수: "); // 검색할 선수를 입력받아서
			String find_name = scan.nextLine();
			
			pstmt = con.prepareStatement(sql + "'" + find_name + "'");

			rs = pstmt.executeQuery(); 
			System.out.println("----"+ find_name +"님  검색 결과-------");
			
			while (rs.next()) {
				String id = rs.getString("id"); 
				String name = rs.getString("name"); 
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String position = rs.getString("position");
				
				System.out.println(id + "\t" + name + "\t" + age + "\t" + gender+ "\t"+position);
			}
		}catch(Exception e) {System.out.println(e);}
		finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
				scan.close();
			} catch (Exception e) {}
		}
	}	
	
////////////////////////////////////////////////////////////////////
	public void DeletePlayer() {	
	}
}
////////////////////////////////////////////////////////////////////