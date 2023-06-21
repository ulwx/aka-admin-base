package com.github.ulwx.aka.admin.servlets;

import com.github.ulwx.aka.admin.servlets.utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "patchca", urlPatterns = "/validcode")
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 4968328161261528097L;

	public static String VAL_CODE_ID = "ValCode";

	@Override
	public void init() throws ServletException {
		super.init();

	}

	@Override
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	   response.setHeader("Pragma", "No-cache");
       response.setHeader("Cache-Control", "no-cache");
       response.setDateHeader("Expires", 0);
       response.setContentType("image/jpeg");
         
       //生成随机字串
       String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
       //存入会话session
       HttpSession session = request.getSession(true);
       //删除以前的
        session.setAttribute(VAL_CODE_ID, verifyCode.toLowerCase());
       //生成图片
       int w = 180, h = 80;

       VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
