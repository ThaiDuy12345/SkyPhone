package com.nhathanh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nhathanh.model.TaiKhoan;
import com.nhathanh.service.SessionService;

@Service
public class AuthInterceptor implements HandlerInterceptor{
//	@Autowired
//	SessionService session;
//	//Đối với mình vai trò 0 là Admin nhé mọi người
//	//Vai trò 1 là Employ
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		String uri = request.getRequestURI();
//		TaiKhoan user = session.get("user");
//		String error = "";
//		if(user == null) {
//			error = "Bạn chưa đăng nhập vui lòng đăng nhập!";
//		} else if(user.getVai_tro()==0 && uri.startsWith("/admin/")) {
//			error = "Access denied!";
//		}
//		if(error.length() > 0) {
//			session.set("security-uri", uri);
//			response.sendRedirect("/login?error=" + error);
//			return false;
//		}
//		//Chuyển tiếp đến Controller khi thõa điều kiện
//		return true;
//	}
}