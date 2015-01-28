package com.bac.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //final = can't change
	static final String DB_URL = "jdbc:mysql://localhost/bac";  //static final = make it a standard value`
	static final  String USER = "root";
	static final String PASS = "900418";

	public List<HashMap<String, Object>> getUser() {
		Connection conn = null;
		Statement stmt = null;
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>(); //??
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement();  //연결된 상태를 stmt로
			String sql ="SELECT * FROM user";  //user테이블의 모든 컬럼을 가져와라?
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				HashMap<String, Object> item = new HashMap<String, Object>();
				
				item.put("id", rs.getString("id"));
				item.put("pass", rs.getString("pass"));
				item.put("name", rs.getString("name"));
				item.put("regdate", rs.getString("regdate"));
				
				result.add(item);
			}
		
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(SQLException se){
			se.printStackTrace(); //???
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		
		return result;
		
	}//end main
	
	
	public String insertUser(Map<String, String[]> paramMap){
		Connection conn = null;
		Statement stmt = null;
		String result = "success";

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement();
			String sql= "INSERT INTO user (id, pass, name, regdate)"+
						"VALUES('"+paramMap.get("id")[0].toString()+"', '"+paramMap.get("pass")[0].toString()+"', '"+paramMap.get("name")[0].toString()+"', now())";

			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
			result = "fail";
		}catch(Exception e){
			e.printStackTrace();
			result = "fail";
		}finally{
		}

		return result;
	}
	
	public String deleteUser(String id){
		Connection conn = null;
		Statement stmt = null;
		String result = "success";

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt = conn.createStatement();
			System.out.println("id:"+id);
			String sql= "DELETE FROM user WHERE id='"+id+"' ";

			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
			result = "fail";
		}catch(Exception e){
			e.printStackTrace();
			result = "fail";
		}finally{
		}

		return result;
	}
}//end FirstExample