package com.bac.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRest extends HttpServlet{
	
	private static final long serialVersionUID = 1L; //???

	/**
	 * 회원 리스트 조회
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printout = response.getWriter();  //??
        JSONObject JObject = new JSONObject();  //??
        
        UserDao dao = new UserDao();
                                                                       
        try {
			JObject.put("result", dao.getUser());
			
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
        
        UserDao dao = new UserDao();
        
        Map<String, String[]> paramMap = request.getParameterMap();
        System.out.print("paramMap: "+paramMap);
        System.out.print("paramMap: "+paramMap.size());
        System.out.print("paramMap: "+paramMap.toString());
        
        try{
        	JObject.put("result", dao.insertUser(paramMap)); //dao.insertUser(paramMap)
        	
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
        String id = request.getParameter("id");
        
        UserDao dao = new UserDao();
        try{
        	JObject.put("result", dao.deleteUser(id));
        }catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        printout.print(JObject);
        printout.flush();
	}

}