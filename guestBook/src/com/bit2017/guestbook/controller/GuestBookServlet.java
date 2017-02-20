package com.bit2017.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit2017.guestbook.dao.GuestBookDao;
import com.bit2017.guestbook.vo.GuestBookVo;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
		
		if("deleteform".equals(actionName)) {
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		} else if("delete".equals(actionName)) {
			Long no = Long.parseLong( request.getParameter( "id" ) );
			String password = request.getParameter("password");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(password);
			
			GuestBookDao dao = new GuestBookDao();
			dao.delete(vo);
			
			response.sendRedirect(request.getContextPath()+"/gb");
		} else if("modifyform".equals(actionName)) {
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/modifyform.jsp");
			rd.forward(request, response);
			
		} else if("modify".equals(actionName)) {
			Long no = Long.parseLong(request.getParameter("id"));
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String content = request.getParameter("content");

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(password);
			vo.setName(name);
			vo.setContent(content);
			
			GuestBookDao dao = new GuestBookDao();
			dao.modify(vo);
			
			response.sendRedirect(request.getContextPath()+"/gb");
			
		} else if("add".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			GuestBookDao dao = new GuestBookDao();
			dao.insert(vo);
			
			response.sendRedirect(request.getContextPath()+"/gb");
			
		} else {
			//defalut request
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();
			
			//restore List Object in Request Scope
			request.setAttribute("list", list);
			
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
