 package com.myin.team25.controller;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.myin.team25.domain.MemberVo;
import com.myin.team25.service.MemberService;
import com.myin.team25.service.MemberServiceImpl;

@Controller
public class MemberController {

	@Autowired //DI(의존성주입) 사용|| bin에 등록하고 autowired를 찍어주면 된다.
	DriverManagerDataSource db;
	
	@Resource(name="sqlSession")
	SqlSession SqlSession;
	
	@Autowired
	MemberServiceImpl ms;
	
	@Autowired
	MemberService md;
	
	@RequestMapping(value="/MemberSelectController")
	public String SelectMemberAllCall(Model model){
		
		ArrayList<MemberVo> av = md.selectMemberAll();
				
		model.addAttribute("av",av);
		
		return "member/memberList";
	}
	
	@RequestMapping(value="/MemberLoginCheckController")
	public String LoginCheck(){
		
		
		return "member/memberLogin";
	}
	
	@RequestMapping(value="/MemberLoginCheckActionController", method=RequestMethod.POST)
	public String LoginCheckAction(@ModelAttribute("mv") MemberVo mv, Model model,HttpSession session) throws Exception{
		System.out.println("실행1");
		String url="";
		MemberVo mc= new MemberVo();
		
			mc= md.loginCheck(mv);
			System.out.println("실행2");
			
			if(mc !=null){
				model.addAttribute("sMemberId", mc.getMemberId());
				model.addAttribute("sMemberMidx",mc.getMemberMidx());
				model.addAttribute("sMemberName",mc.getMemberName());
			
				if(mv.getUseCookie() != null){
					int amount = 60 * 60 * 24 * 7;
					
					Calendar cal = Calendar.getInstance();
				    cal.setTime(new Date());
				    cal.add(Calendar.DATE, 7);
				    DateFormat df = new SimpleDateFormat("yy-MM-dd");   
				    String next = df.format(cal.getTime());
				   
					System.out.println(next);
					System.out.println("mvmidx="+mc.getMemberMidx());
					md.keepLogin(mc.getMemberMidx(), session.getId(), next);
					
				}
				
				} 
				String dest =(String)session.getAttribute("dest");
				
				//System.out.println("dest"+dest);
					if (dest != null){
						url = "redirect:"+dest+"";
					}else{
						url="redirect:/MemberSelectController";
					}
				//System.out.println("page"+page);	
				return url; 
			}
				
			
			
		
	
	@RequestMapping(value="/MemberModifyController")
	public String Modify(HttpSession session, Model model ){
		
		String memberId=(String)session.getAttribute("sMemberId");
		
		MemberVo av = md.selectMemberOne(memberId);
		
		model.addAttribute("av",av);
		
		return "member/memberModify";
	}
	
	@RequestMapping(value="/MemberModifyActionController")
	public String ModifyAtion(@ModelAttribute("mv") MemberVo mv,@RequestParam("memberEmail1") String memberEmail1,@RequestParam("memberEmail2") String memberEmail2,HttpSession session){
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		
		int bv= 0;
		String ip =null;
		String url="";
		
		try {
			InetAddress local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setMemberIp(ip);
		mv.setMemberMidx(memberMidx);
		mv.setMemberEmail(memberEmail1+memberEmail2);

		bv= md.updateMember(mv);
		
		
		if(bv==1){
			url= "redirect:/MemberSelectController";
		}else{		
			url= "redirect:/MemberModifyController";
		}
		
		
		return url;
	}
	
	@RequestMapping(value="/MemberJoinController")
	public String Join(){
		
		
		
		return "member/memberJoin";
	}
	
	@RequestMapping(value="/MemberJoinActionController")
	public String JoinAtion(@ModelAttribute("mv") MemberVo mv,@RequestParam("memberEmail1") String memberEmail1,@RequestParam("memberEmail2") String memberEmail2){
		
		
		int bv= 0;
		int av=0;
		String ip =null;
		String url="";
		int maxmidx=0;
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		av= md.maxMember();
		maxmidx=av+1;
		
		
		
		mv.setMemberMidx(maxmidx);
	
		mv.setMemberIp(ip);
		mv.setMemberEmail(memberEmail1+"@"+memberEmail2);
		
		bv= md.insertMember(mv);
		
		
		if(bv==1){
			url= "redirect:/MemberSelectController";
		}else{		
			url= "redirect:/MemberJoinController";
		}
		
		
		return url;
	}
	
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
			String page;
		Object obj = session.getAttribute("sMemberMidx");
		System.out.println("1="+obj);
		String url="";
		if(obj != null){
			System.out.println("2="+obj);
			
			
			session.removeAttribute("sMemberMidx");
			session.invalidate();


			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null){
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
				if(session.getId()== null){
				MemberVo mv = md.checkAutoLogin(session.getId());

				md.keepLogin(mv.getMemberMidx(), "", "");
				}else{
					System.out.println("ok");
				}
				
				System.out.println("찍힘3");
				//""빈값도 사용가능
			}
			
		}
			
		
		url="redirect:/MemberSelectController";
		return url;
	}
	
}
