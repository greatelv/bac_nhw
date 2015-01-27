package com.bac.posting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostingDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 													
	static final String DB_URL = "jdbc:mysql://localhost/bac";
														
																
	static final String USER = "root";
	static final String PASS = "900418";

	public List<HashMap<String, Object>> getPosting() {
		Connection conn = null;
		Statement stmt = null;
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>(); //??
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement(); 
			String sql ="SELECT * FROM posting";  
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				HashMap<String, Object> item = new HashMap<String, Object>();
				
				item.put("seq", rs.getString("seq"));
				item.put("content", rs.getString("content"));
				item.put("regdate", rs.getString("regdate"));
				item.put("writer", rs.getString("writer"));
				
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
		
	}// end main

	public String insertPosting(Map<String, String[]> paramMap) {
		Connection conn = null;
		Statement stmt = null;
		String result = "success"; //

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "INSERT INTO posting (content, regdate, writer)"+
					"VALUES('"+paramMap.get("content")[0].toString()+"', now(), '"+paramMap.get("writer")[0].toString()+"')";


			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
			result = "fail";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		} finally {
		}

		return result;
	}
	
	public String deletePosting(String seq){
		Connection conn = null;
		Statement stmt = null;
		String result = "success";

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt = conn.createStatement();
			
			String sql= "DELETE FROM posting WHERE seq='"+seq+"' ";

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
