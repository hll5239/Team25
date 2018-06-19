package com.myin.team25.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
		
	 @Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
			
		 HttpSession session = request.getSession();
		 
		 if(session.getAttribute("sMemberMidx") == null){
			
			 saveDest(request);
			 response.sendRedirect(request.getContextPath()+"/MemberLoginCheckController");
			 return false;
		 }
		 return true;		
	 	}
	
	 private void saveDest(HttpServletRequest req){
	
		 String url = req.getRequestURI();		 
		 String query = req.getQueryString();
		 
		
		 
		 if (query ==null || query.equals("null")){
			 query = "";
		 }else {
			 query = "?" + query;
		 }
		 
		 if (req.getMethod().equals("GET")){
			 req.getSession().setAttribute("dest", url+query);
			 System.out.println("url:"+url+query);
		 }		 
	 }	
}
