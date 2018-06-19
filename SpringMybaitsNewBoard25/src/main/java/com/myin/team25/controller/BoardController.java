package com.myin.team25.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myin.team25.domain.BoardVo;
import com.myin.team25.domain.CommentVo;
import com.myin.team25.domain.PageMaker;
import com.myin.team25.domain.SearchCriteria;
import com.myin.team25.service.BoardService;
import com.myin.team25.util.MediaUtils;
import com.myin.team25.util.UploadFileUtiles;


@Controller
public class BoardController {

	@Autowired //DI(의존성주입) 사용|| bin에 등록하고 autowired를 찍어주면 된다.
	DriverManagerDataSource db;
	
	@Resource(name ="uploadPath")
	private String uploadPath;
	
	@Autowired
	BoardService bd;
	
	@RequestMapping(value="/BoardSelectController")
	public String BoardSelectAllCall(SearchCriteria scri,Model model){
		int cnt=0;
		cnt=bd.TotalRecordCount(scri);
		System.out.println("cnt값은?"+cnt);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);	//pagemaker안에 Criteria 메소드에 cri값을 넣어준다. 그러면 Criteria클래스에 값이 들어간다.
		pageMaker.setTotalCount(cnt); //calcData메소드를 실행시켜서 start,end, prev, next 값을 넣어준다
		
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		alist= bd.SelectBoardAll(scri);	//impl에 cri값을 넣어줘서 impl에서 mapper에 cri값을 넣어줘서 pageStart와 pageEnd값을 넣어서 쿼리 실행시킴  
		model.addAttribute("alist",alist); 
		model.addAttribute("pageMaker",pageMaker);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/BoardList_AjaxController")
	public String BoardSelectAjax(SearchCriteria scri,Model model){
//		int cnt=0;
//		cnt=bd.TotalRecordCount(scri);
//		System.out.println("cnt값은?"+cnt);
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setScri(scri);	//pagemaker안에 Criteria 메소드에 cri값을 넣어준다. 그러면 Criteria클래스에 값이 들어간다.
//		pageMaker.setTotalCount(cnt); //calcData메소드를 실행시켜서 start,end, prev, next 값을 넣어준다
//		
//		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
//		alist= bd.SelectBoardAll(scri);	//impl에 cri값을 넣어줘서 impl에서 mapper에 cri값을 넣어줘서 pageStart와 pageEnd값을 넣어서 쿼리 실행시킴  
//		model.addAttribute("alist",alist); 
//		model.addAttribute("pageMaker",pageMaker);
		
		return "board/boardList_ajax";
	}
	
	@RequestMapping(value="/BoardListAjaxController/{page}")
	public @ResponseBody HashMap<String, Object> BoardListAjax(@PathVariable("page") int page, SearchCriteria scri,Model model){
		
		scri.setPage(page);
		int cnt=0;
		cnt=bd.TotalRecordCount(scri);
		System.out.println("cnt값은?"+cnt);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);	//pagemaker안에 Criteria 메소드에 cri값을 넣어준다. 그러면 Criteria클래스에 값이 들어간다.
		pageMaker.setTotalCount(cnt); //calcData메소드를 실행시켜서 start,end, prev, next 값을 넣어준다
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		alist= bd.SelectBoardAll(scri);	//impl에 cri값을 넣어줘서 impl에서 mapper에 cri값을 넣어줘서 pageStart와 pageEnd값을 넣어서 쿼리 실행시킴  
		
		map.put("alist", alist);
		map.put("pageMaker", pageMaker);
		
		//model.addAttribute("alist",alist); 
		//model.addAttribute("pageMaker",pageMaker);
		
		
		return map;
	}
	
	@RequestMapping(value="/BoardContentController")
	public String Content(SearchCriteria scri,@RequestParam("bBidx") int bBidx, Model model){

		

		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);	
		

		model.addAttribute("pageMaker",pageMaker);
		
		BoardVo bv= null;
		
		
		bv= bd.SelectBoardOne(bBidx);
		model.addAttribute("bv",bv);
	
		return "board/boardContent";
	}
	
	@RequestMapping(value="/BoardWriteController")
	public String Write(){
		
		return "board/boardWrite";
	}
	//modelAttribute는 1:1 매핑으로 값을 받아올때 사용한다.
	@RequestMapping(value="/BoardWriteActionController")
	public String WriteAction(@ModelAttribute("bv") BoardVo bv ,HttpSession session,Model model)  throws Exception{
		
//		System.out.println("들어옴"+bv);
//		MultipartFile file = bv.getUploadfile();
//		System.out.println("file="+file.getOriginalFilename());
//		String savedName = uploadFile(file.getOriginalFilename(),file.getBytes());
//		System.out.println("savedName="+savedName);
//		model.addAttribute("savedName",savedName);
//		String uploadeFileName= UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
//		String uploadeFileName=file.getOriginalFilename();
//		System.out.println("getOriginalFilename값="+file.getOriginalFilename());
//		System.out.println("filename값="+bv.getFilename());
//		System.out.println("upload값="+bv.getUploadfile());
		
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		int cv= 0;
		String url="";
		
		System.out.println("memberMidx값은?"+memberMidx);
		String ip =null;
		InetAddress local=null;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		 bv.setIp(ip); 
		 bv.setMemberMidx(memberMidx);
//		 bv.setFilename(bv.getFilename());
		cv= bd.InsertBoard(bv);

		
		if(cv==1){
			url= "redirect:/BoardSelectController";
		}else{
			url= "redirect:/BoardWriteController";
		}
		
		return url;
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadAjax" ,method = RequestMethod.POST, produces ="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax( MultipartFile file)  throws Exception{
		
//		System.out.println("originalName: "+ file.getOriginalFilename());
//		System.out.println("size: "+file.getSize());
//		System.out.println("contentType: "+file.getContentType());
		
		//String uploadeFileName= UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
		return new ResponseEntity<String>(UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()),HttpStatus.CREATED);
	}
	@ResponseBody
	@RequestMapping(value="/displayFile", produces ="text/plain;charset=UTF-8")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		
		InputStream in = null;
		ResponseEntity<byte[]> entity=null;
		
		try{
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType= MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null){
				headers.setContentType(mType);
			}else{
				
				fileName= fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+
							new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			
			entity= new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) throws Exception{
		
		String formatName= fileName.substring(fileName.lastIndexOf(".")+1);
		
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType != null){
			String front = fileName.substring(0,12);
			 String end = fileName.substring(14);
		new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
	//UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(),request),
//	private String uploadFile(String originalName, byte[] fileData)throws Exception{
//		
//		UUID uid = UUID.randomUUID();
//		
//		String savedName = uid.toString()+ "_"+originalName;
//		System.out.println("savedname2="+savedName);
//		File target = new File(uploadPath,savedName);
//		System.out.println("target="+target);
//		FileCopyUtils.copy(fileData, target);
//		
//
//		return savedName;
//	}
	
	@RequestMapping(value="/BoardModifyController")
	public String Modify(SearchCriteria scri,@RequestParam("bBidx") int bBidx, Model model){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		
		BoardVo bv= null;
		bv= bd.SelectBoardOne(bBidx);
		
		model.addAttribute("bv",bv);
		model.addAttribute("pageMaker",pageMaker);
		
		return "board/boardModify";
	}
	
	@RequestMapping(value="/BoardModifyActionController")
	public String ModifyAction(RedirectAttributes rttr,SearchCriteria scri,@ModelAttribute("vo") BoardVo vo, @RequestParam("bBidx") int bBidx,HttpSession session){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		int bv= 0;
		String ip =null;
		String url="";
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		 vo.setIp(ip);
		 vo.setMemberMidx(memberMidx);
		 vo.setbBidx(bBidx);
		
		bv= bd.UpdateBoard(vo);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addAttribute("bBidx", bBidx);
		
		if(bv==1){
			rttr.addFlashAttribute("msg", "Modify");
			url= "redirect:/BoardSelectController";
		}else{		
			rttr.addAttribute("bBidx", bBidx);
			url= "redirect:/BoardModifyController";
		}
		
		return url;
	}
	
	@RequestMapping(value="/BoardDeleteController")
	public String Delete(SearchCriteria scri,@RequestParam("bBidx") int bBidx, Model model){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		BoardVo bv= null;
		bv= bd.SelectBoardOne(bBidx);
		
		model.addAttribute("bv",bv);
		model.addAttribute("pageMaker",pageMaker);
		
		return "board/boardDelete";
	}
	
	@RequestMapping(value="/BoardDeleteActionController")
	public String DeleteAction(RedirectAttributes rttr,SearchCriteria scri, @ModelAttribute("vo") BoardVo vo, @RequestParam("bBidx") int bBidx,HttpSession session){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addAttribute("bBidx", bBidx);
		
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		
		int bv= 0;
		String ip =null;
		String url="";
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 vo.setIp(ip);
		 vo.setMemberMidx(memberMidx);
		 vo.setbBidx(bBidx);
		
		bv= bd.DeleteBoard(vo);

		
		if(bv==1){
			rttr.addFlashAttribute("msg", "Delete");
			url= "redirect:/BoardSelectController";
		}else{	
			rttr.addAttribute("bBidx", bBidx);
			url= "redirect:/BoardDeleteController";
		}
		
		return url;
	}
	
	@RequestMapping(value="/BoardReplyController")
	public String Reply(SearchCriteria scri,Model model,@ModelAttribute("bBidx") int bBidx,@ModelAttribute("oidx") int oidx,
			@ModelAttribute("updown") int updown,@ModelAttribute("leftright") int leftright){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		model.addAttribute("pageMaker",pageMaker);
		
		System.out.println("여긴 오니?");
		return "board/boardReply";
	}
	
	@RequestMapping(value="/BoardReplyActionController")
	public String ReplyAction(RedirectAttributes rttr,SearchCriteria scri,@ModelAttribute("vo") BoardVo vo,HttpSession session){
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setScri(scri);
		
		int memberMidx=(int)session.getAttribute("sMemberMidx");
		int bv= 0;

		String ip =null;
		String url="";
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ip= local.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("값들:"+vo.getbBidx()+"-"+vo.getOidx()+"-"+vo.getLeftright()+"-"+vo.getUpdown());
		 vo.setIp(ip);
		 vo.setMemberMidx(memberMidx);
		
		bv= bd.ReplyBoard(vo);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		rttr.addAttribute("bBidx", vo.getbBidx());
		
		if(bv==1){
			rttr.addFlashAttribute("msg", "Replay");
			url= "redirect:/BoardSelectController";
		}else{	
			rttr.addAttribute("bBidx", vo.getbBidx());
			rttr.addAttribute("oidx", vo.getOidx());
			rttr.addAttribute("updown", vo.getUpdown());
			rttr.addAttribute("leftright", vo.getLeftright());
			
			url= "redirect:/BoardReplyController";
		}
		
		return url;
	}
	
	///살려줘!!!!
	
}
