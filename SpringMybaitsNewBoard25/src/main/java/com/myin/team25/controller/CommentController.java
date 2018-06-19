package com.myin.team25.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myin.team25.domain.CommentCriteria;
import com.myin.team25.domain.CommentVo;
import com.myin.team25.service.CommentService;
//@ResponseBody+@Controller=@RestController 1:1 하나의 링크에 하나의 객체
@RestController
public class CommentController {

	@Autowired
	CommentService cs;
	
	@RequestMapping(value="/comments/all/{bbidx}") 
	public ArrayList<CommentVo> CommentAll(@PathVariable("bbidx") int bbidx){
		ArrayList<CommentVo> alist= cs.SelectCommentAll(bbidx);

		return alist;
	}
	
	@RequestMapping(value="/comments/{block}/{bbidx}") 
	public HashMap<String, Object> MoreCommentList(@PathVariable("bbidx") int bbidx,@PathVariable("block") int block){
		System.out.println("들어왔");
		CommentCriteria cc = new CommentCriteria();
		
		int defaultblock=cc.getBlock();
		
		int perBlockNum=cc.getPerBlockNum();
		//int nextBlock =cc.getNextBlock();
		
		if(block ==0){
			block=defaultblock;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		System.out.println("들어왔2");
		System.out.println(cc.getBlock()+"||"+cc.getPerBlockNum()+"||"+bbidx+"||"+block);
		ArrayList<CommentVo> alist= cs.getCommentMore(bbidx,block,perBlockNum);
		System.out.println("들어왔3");
		map.put("alist", alist);
		map.put("nextBlock", block+1);
		System.out.println("들어왔4");
		System.out.println("map="+map);
		System.out.println(map.get("alist"));
		return map;
	}
	
	@RequestMapping(value="/comments",method=RequestMethod.POST, produces = "application/text; charset=utf8")
	public ResponseEntity<String> CommentWrite(@RequestBody CommentVo cv,HttpSession session){
		System.out.println("들어옴");
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		
		String ip =null;
		InetAddress local=null;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		 cv.setIp(ip); 
		 cv.setMemberMidx(memberMidx);
		 String msg=null;
		 ResponseEntity<String> entity=null;
		 
		 try{
			 cs.InsertCommentAll(cv);
			
			  msg = "등록되었습니다.";
			  entity = new ResponseEntity<String>(msg,HttpStatus.OK);
		 }catch(Exception e){
			 e.printStackTrace();
			 entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		 }
		// ArrayList<CommentVo> alist= cs.SelectCommentAll(cv.getBbidx());
		 //System.out.println("alist="+alist.get(0).getCcontent());
		return entity;
	}
	
	@RequestMapping(value="/comments/del",method=RequestMethod.DELETE, produces = "application/text; charset=utf8" )
	public  ResponseEntity<String> CommentDelete(@RequestBody CommentVo cv,HttpSession session){
		
		int memberMidx=(int)session.getAttribute("sMemberMidx");

		String ip =null;
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int result=0;
		String msg =null;
		 ResponseEntity<String> entity=null;
		 cv.setIp(ip);
		 cv.setMemberMidx(memberMidx);
		 
		try{
		
		 cs.DeleteComment(cv);
		 msg="삭제되었습니다.";
		 
		 entity = new ResponseEntity<String>(msg,HttpStatus.OK);
		 
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		//ArrayList<CommentVo> alist= cs.SelectCommentAll(cv.getBbidx());
		
		//CommentAll(cv.getBbidx());
		
		return entity;
	}
}
