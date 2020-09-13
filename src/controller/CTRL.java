package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import vo.*;


/**
 * Servlet implementation class CTRL
 */
@WebServlet("/CTRL")
public class CTRL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CTRL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String cmd = "";
		String msg ="";
		cmd = request.getParameter("key");
		if(cmd.equals("start")) {
			request.setAttribute("id", "");
			request.setAttribute("message", "입력하라 아이디와 비밀번호");
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("join")) {
			RequestDispatcher view = request.getRequestDispatcher("join.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("search")) {
			postDAO pdao = new postDAO();
			msg = request.getParameter("keyword");
			if(msg.equals("all")) {
				session.setAttribute("keyword", "all");
				msg="%";
				}
			else {
				msg = "%"+request.getParameter("keyword")+"%";
			}

			int num = Integer.parseInt(request.getParameter("index"));
			session.setAttribute("index", num);
			num = num*10;
			ArrayList<postVO> postList = pdao.getpvoList(true,msg);
			List<postVO> list;
			
			if(postList.size() < num+10)
				list = postList.subList(num, postList.size());
			else
				list = postList.subList(num,num+10);
			
			
			request.setAttribute("postList", list);
			request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));
			RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("search1")) {
			msg = request.getParameter("keyword");
			postDAO pdao = new postDAO();
			postVO pvo = pdao.read(Integer.parseInt(msg));
			
			int num = Integer.parseInt(request.getParameter("index"));
			session.setAttribute("index", num);
			num = num*10;
			session.setAttribute("keyword", pvo.getName());
			ArrayList<postVO> postList = pdao.getpvoList(true,pvo.getName());
			List<postVO> list;
			
			if(postList.size() < num+10)
				list = postList.subList(num, postList.size());
			else
				list = postList.subList(num,num+10);
			
			
			request.setAttribute("postList", list);
			request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));
			RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("search2")) {
			postDAO pdao = new postDAO();
			msg = request.getParameter("keyword");
			if(msg.equals("all")) {
				session.setAttribute("keyword", "all");
				msg="%";
				}
			else {
				msg = "%"+request.getParameter("keyword")+"%";
			}
			boolean c;
			
			if(((String)session.getAttribute("opt")).equals("이 름")) {
				c = true;
			}
			else
				c = false;
			
			

			int num = Integer.parseInt(request.getParameter("index"));
			session.setAttribute("index", num);
			num = num*10;
			ArrayList<postVO> postList = pdao.getpvoList(c,msg);
			List<postVO> list;
			
			if(postList.size() < num+10)
				list = postList.subList(num, postList.size());
			else
				list = postList.subList(num,num+10);
			
			
			request.setAttribute("postList", list);
			request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));
			RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
			view.forward(request,response);
		}
		
		else if(cmd.equals("logout")) {
			request.setAttribute("id", ((userVO)session.getAttribute("IAM")).getId());
			session.invalidate();
			request.setAttribute("message", "로그아웃 되었다. 성공적으로");
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("write")) {
			postVO pvo = new postVO();
			request.setAttribute("pvo",pvo);
			request.setAttribute("msg", "글 작 성");
			RequestDispatcher view = request.getRequestDispatcher("writer.jsp");
			view.forward(request,response);
		}
		else if(cmd.equals("open")) {
			msg = request.getParameter("keyword");
			postDAO pdao = new postDAO();
			postVO pvo = pdao.read(Integer.parseInt(msg));
			
			
			cmtDAO cdao = new cmtDAO();
			ArrayList<cmtVO> cvoList = cdao.getcvoList(Integer.parseInt(msg));
			
			msg = "";
			request.setAttribute("cvoList", cvoList);
			request.setAttribute("msg", msg);
			request.setAttribute("pvo", pvo);
			RequestDispatcher view = request.getRequestDispatcher("open.jsp");
			view.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("(POST)Served at: ").append(request.getContextPath());
		

		String message ="";
		String cmd = "";
		HttpSession session = request.getSession();
		
		cmd = request.getParameter("key");
		
		if(cmd.equals("login")) {
			userDAO udao = new userDAO();
			userVO uvo = null;
			uvo = udao.login(request.getParameter("id"), request.getParameter("passwd"));
			if(uvo==null) {
				request.setAttribute("id","");
				request.setAttribute("message", "틀린 아이디 또는 비밀번호");
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				view.forward(request,response);
			}
			else {
				session.setAttribute("IAM", uvo);
				session.setAttribute("keyword","all");
				session.setAttribute("opt","이 름");
				postDAO pdao = new postDAO();
				ArrayList<postVO> postList = pdao.getpvoList(true,"%");
				
				int num = 0;
				List<postVO> list;
				if(postList.size() < num+10)
					list = postList.subList(num, postList.size());
				else
					list = postList.subList(num,num+10);
				
				session.setAttribute("index", num);
				request.setAttribute("postList", list);
				request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));

				RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
				view.forward(request,response);
			}
			
		}
		
		else if (cmd.equals("join")) {
			userVO uvo = new userVO();
			uvo.setId(request.getParameter("id"));
			uvo.setPasswd(request.getParameter("passwd"));
			uvo.setUsername(request.getParameter("username"));
			uvo.setMobile(request.getParameter("mobile"));
			uvo.setEmail(request.getParameter("email"));
			
			userDAO udao = new userDAO();
			request.setAttribute("id", "");
			if(udao.add(uvo)) { message = "가입 축하합니다."; request.setAttribute("id", uvo.getId());}
			else message = "가입 실패입니다.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request,response);
		}
		
		else if (cmd.equals("search")) {
			
			session.setAttribute("keyword",  request.getParameter("user_input"));
			String keyword;
			
			if(request.getParameter("opt").equals("이 름")) {
				
				session.setAttribute("opt", "이 름");
				
				if(request.getParameter("user_input").equals("all"))
					keyword = "%";
				else
					keyword = "%"+request.getParameter("user_input")+"%";
				

				postDAO pdao = new postDAO();
				ArrayList<postVO> postList = pdao.getpvoList(true,keyword);
				
				int num = Integer.parseInt(request.getParameter("index"));
				session.setAttribute("index", num);
				num = num*10;
				
				List<postVO> list;
				if(postList.size() < num+10)
					list = postList.subList(num, postList.size());
				else
					list = postList.subList(num,num+10);
				
				
				request.setAttribute("postList", list);
				request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));
				RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
				view.forward(request,response);
			}
			else {
				session.setAttribute("opt", "내 용");
				postDAO pdao = new postDAO();
				
				if(request.getParameter("user_input").equals("all"))
					keyword = "%";
				else
					keyword = "%"+request.getParameter("user_input")+"%";
				
				ArrayList<postVO> postList = pdao.getpvoList(false,keyword);
				
				int num = Integer.parseInt(request.getParameter("index"));

				session.setAttribute("index", num);
				num = num*10;
				
				List<postVO> list;
				if(postList.size() < num+10)
					list = postList.subList(num, postList.size());
				else
					list = postList.subList(num,num+10);
				
				request.setAttribute("postList", list);
				request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)));
				RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
				view.forward(request,response);
			}	

		}
		
		else if (cmd.equals("write")) {
			postVO pvo = new postVO();
			postDAO pdao = new postDAO();
			
			pvo.setId(((userVO)session.getAttribute("IAM")).getId());
			pvo.setName(((userVO)session.getAttribute("IAM")).getUsername());
			pvo.setHead(request.getParameter("head"));
			pvo.setBody(request.getParameter("body"));
			pvo.setNum(Integer.parseInt(request.getParameter("num")));
			
			if(pvo.getNum() == 0) {
				if(pdao.add(pvo)) { message = "작성되었다 성공적으로";
				pvo = pdao.last_one();
				}
				else message = "되지 않았다 작성 성공적으로";
			}
			else {
				if(pdao.update(pvo)) message = "변경되었다 성공적으로";
				else message = "변경되지 않았다 실패적으로";
				pvo = pdao.read(pvo.getNum());
			}
			cmtDAO cdao = new cmtDAO();
			ArrayList<cmtVO> cvoList = cdao.getcvoList(Integer.parseInt(request.getParameter("num")));
			request.setAttribute("cvoList", cvoList);
			request.setAttribute("msg",message);
			request.setAttribute("pvo", pvo);
			RequestDispatcher view = request.getRequestDispatcher("open.jsp");
			view.forward(request,response);
		}
		
		else if(cmd.equals("modify")) {
			String I =((userVO)session.getAttribute("IAM")).getId();
			String target = request.getParameter("id");
			postVO pvo = new postVO(Integer.parseInt(request.getParameter("num")),
			request.getParameter("id"),request.getParameter("name"),
			request.getParameter("head"),request.getParameter("body"));
			
			if(I.equals(target)) {	
				request.setAttribute("pvo", pvo);
				request.setAttribute("msg", "수정하기");
				RequestDispatcher view = request.getRequestDispatcher("writer.jsp");
				view.forward(request,response);
			}
			else {
				String msg = "당신은 가지고 있지 않다 권한을";
				
				cmtDAO cdao = new cmtDAO();
				ArrayList<cmtVO> cvoList = cdao.getcvoList(Integer.parseInt(request.getParameter("num")));
				request.setAttribute("cvoList", cvoList);
				
				request.setAttribute("msg", msg);
				request.setAttribute("pvo", pvo);
				RequestDispatcher view = request.getRequestDispatcher("open.jsp");
				view.forward(request,response);
			}
			
			
			
		
		}
		
		else if(cmd.equals("delete")) {
			postDAO pdao = new postDAO();
			String I =((userVO)session.getAttribute("IAM")).getId();
			String target = request.getParameter("id");
			
			if(I.equals(target)) {
				pdao.delete(request.getParameter("num"));
				ArrayList<postVO> postList = pdao.getpvoList(true,"%");
				
				
				request.setAttribute("size",  Integer.toString(((postList.size()-1)/10 +1)) );
				
				int num =0;
				
				List<postVO> list;
				if(postList.size() < num+10)
					list = postList.subList(num, postList.size());
				else
					list = postList.subList(num,num+10);
				
				request.setAttribute("postList", list);
				session.setAttribute("keyword","all");
				session.setAttribute("opt","이 름");
				
				RequestDispatcher view = request.getRequestDispatcher("posting.jsp");
				view.forward(request,response);
			}
			else {
				String msg = "당신은 가지고 있지 않다 권한을";
				postVO pvo = pdao.read(Integer.parseInt(request.getParameter("num")));

				cmtDAO cdao = new cmtDAO();
				ArrayList<cmtVO> cvoList = cdao.getcvoList(Integer.parseInt(request.getParameter("num")));
				request.setAttribute("cvoList", cvoList);
				request.setAttribute("msg", msg);
				request.setAttribute("pvo", pvo);
				RequestDispatcher view = request.getRequestDispatcher("open.jsp");
				view.forward(request,response);
			}
			
		}
		
		else if (cmd.equals("comment")) {
			cmtVO cvo = new cmtVO();
			cmtDAO cdao = new cmtDAO();
			
			userVO uvo = (userVO)session.getAttribute("IAM");
			
			cvo.setName(uvo.getUsername());
			cvo.setId(uvo.getId());
			cvo.setPosting_num(Integer.parseInt(request.getParameter("num")));
			cvo.setComment(request.getParameter("comment"));
			
			cdao.add(cvo);
			
			postDAO pdao = new postDAO();
			postVO pvo = pdao.read(Integer.parseInt(request.getParameter("num")));
			ArrayList<cmtVO> cvoList = cdao.getcvoList(Integer.parseInt(request.getParameter("num")));
			
			request.setAttribute("cvoList", cvoList);
			request.setAttribute("msg", " ");
			request.setAttribute("pvo", pvo);
			RequestDispatcher view = request.getRequestDispatcher("open.jsp");
			view.forward(request,response);
			
		}
	}

}
