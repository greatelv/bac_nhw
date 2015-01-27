package com.bac.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentRest extends HttpServlet{
	
	private static final long serialVersionUID = 3L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printout = response.getWriter(); 
        JSONObject JObject = new JSONObject(); 
        
        CommentDao dao = new CommentDao();
                                                                       
        try {
			JObject.put("result", dao.getComment());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //??
		} 
        

        printout.print(JObject);
        printout.flush();
    }
	

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter printout = response.getWriter();
        JSONObject JObject = new JSONObject();
        
        CommentDao dao = new CommentDao();
        
        Map<String, String[]> paramMap = request.getParameterMap();
        System.out.print("paramMap: "+paramMap);
        System.out.print("paramMap: "+paramMap.size());
        System.out.print("paramMap: "+paramMap.toString());
        
        try{
        	JObject.put("result", dao.insertComment(paramMap));
        	
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        printout.print(JObject);
        printout.flush();
    }
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
        PrintWriter printout = response.getWriter();
        JSONObject JObject = new JSONObject();
        String seq = request.getParameter("seq");
        
        CommentDao dao = new CommentDao();
        try{
        	JObject.put("result", dao.deleteComment(seq));
        }catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        printout.print(JObject);
        printout.flush();
	}
}