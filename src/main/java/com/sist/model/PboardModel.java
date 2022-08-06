package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class PboardModel {
   @RequestMapping("pboard/list.do")
   public String pboard_list(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(curpage*rowSize)-(rowSize-1);
	   int end=curpage*rowSize;
	   map.put("start", start);
	   map.put("end",end);
	   List<PboardVO> list=PboardDAO.pboardListData(map);
	   int totalpage=PboardDAO.pboardTotalPage();
	   
	   request.setAttribute("curpage", curpage);
       request.setAttribute("totalpage", totalpage);
       request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../pboard/list.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("pboard/insert.do")
   public String pboard_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../pboard/insert.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("pboard/insert_ok.do")
   public String pboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   
	   String name=request.getParameter("name");
	   System.out.println("name="+name);
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   
	   PboardVO vo=new PboardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   
	   PboardDAO.pboardInsert(vo);
	   return "redirect:../pboard/list.do";
   }
   @RequestMapping("pboard/detail.do")
   public String pboard_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   // 출력할 데이터를 보낸다 
	   String p_no=request.getParameter("p_no");
	   PboardVO vo=PboardDAO.pboardDetailData(Integer.parseInt(p_no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../pboard/detail.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("pboard/update.do")
   public String pboard_update(HttpServletRequest request,HttpServletResponse response)
   {
	   String p_no=request.getParameter("no");
	   PboardVO vo=PboardDAO.pboardUpdateData(Integer.parseInt(p_no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../pboard/update.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("pboard/pwd_check.do")
   public String pboard_pwd_check(HttpServletRequest request,HttpServletResponse response)
   {
	   String p_no=request.getParameter("p_no");
	   String pwd=request.getParameter("pwd");
	   String res=PboardDAO.pboardPwdCheck(Integer.parseInt(p_no), pwd);
	   request.setAttribute("res", res);
	   return "../pboard/update_ok.jsp";
   }
   
   @RequestMapping("pboard/update_ok.do")
   public String pboard_update_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex) {}
	   
	   String name=request.getParameter("name");
	   System.out.println("name="+name);
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   String p_no=request.getParameter("p_no");
	   PboardVO vo=new PboardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   vo.setP_no(Integer.parseInt(p_no));
	   PboardDAO.pboardUpdate(vo);
	   return "redirect:../pboard/detail.do?p_no="+p_no;
   }
   
   @RequestMapping("freeboard/delete.do")
   public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
   {
	   String p_no=request.getParameter("p_no");
	   String pwd=request.getParameter("pwd");
	   String result=PboardDAO.pboardDelete(Integer.parseInt(p_no), pwd);
	   request.setAttribute("result", result);
	   return "../pboard/delete.jsp";
   }
}





