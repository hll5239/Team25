package com.myin.team25.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myin.team25.domain.MemberVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN ="login";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception{
		System.out.println("µé¾î¿È ½ÇÇà");
		HttpSession session = request.getSession();
		
//		ModelMap modelMap = modelAndView.getModelMap();
//		Object memberVo = modelMap.get("mv");
//		
//		if(memberVo != null){
//			session.setAttribute("sMemberId", memberVo);
//		}
		Object sMemberId = modelAndView.getModel().get("sMemberId");
		Object sMemberMidx = modelAndView.getModel().get("sMemberMidx");
		Object sMemberName = modelAndView.getModel().get("sMemberName");
		
		//MemberVo mc =(MemberVo)request.getAttribute("mv");
		
		
		if(sMemberMidx != null){
			
			session.setAttribute("sMemberId", sMemberId);
			session.setAttribute("sMemberMidx", sMemberMidx);
			session.setAttribute("sMemberName", sMemberName);

				Cookie loginCookie = new Cookie("loginCookie", request.getParameter("useCookie"));
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*7);
				response.addCookie(loginCookie);
			
			//response.sendRedirect("/");
		}
		
	
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session= request.getSession();
		
		if(session.getAttribute("sMemberMidx") != null){
			session.removeAttribute("sMemberId");
			session.removeAttribute("sMemberMidx");
			session.removeAttribute("sMemberName");

		}
		
		if(session.getAttribute(LOGIN) != null){
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
}
