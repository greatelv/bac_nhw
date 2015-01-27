package com.bac.comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 													
	static final String DB_URL = "jdbc:mysql://localhost/bac";
														
																
	static final String USER = "root";
	static final String PASS = "900418";

	public List<HashMap<String, Object>> getComment() {
		Connection conn = null;
		Statement stmt = null;
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>(); //??
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement(); 
			String sql ="SELECT * FROM comment";  
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				HashMap<String, Object> item = new HashMap<String, Object>();
				
				item.put("seq", rs.getString("seq"));
				item.put("posting_seq", rs.getString("posting_seq"));
				item.put("content", rs.getString("content"));
				item.put("writer", rs.getString("writer"));
				item.put("regdate", rs.getString("regdate"));
				
				result.add(item);
			}
		
			rs.close();
			stmt.close();
			conn.close();
			
			}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		
		return result;
		
	}

	public String insertComment(Map<String, String[]> paramMap) {
		Connection conn = null;
		Statement stmt = null;
		String result = "success"; //

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "INSERT INTO comment (posting_seq, content, writer, regdate)"+
					"VALUES('"+paramMap.get("posting_seq")[0].toString()+"', '"+paramMap.get("content")[0].toString()+"', '"+paramMap.get("writer")[0].toString()+"', now())";


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
	
	public String deleteComment(String seq){
		Connection conn = null;
		Statement stmt = null;
		String result = "success";

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt = conn.createStatement();
			
			String sql= "DELETE FROM comment WHERE seq='"+seq+"' ";

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

}
	

