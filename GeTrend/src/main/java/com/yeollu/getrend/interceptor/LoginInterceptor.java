package com.yeollu.getrend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class 	: LoginInterceptor.java
 * @Package	: com.yeollu.getrend.interceptor
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	:
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/**
	 * Overriding
	 * @Method	: preHandle
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: 모든 페이지에 접근할 때 로그인 여부를 확인하여 로그인 상태가 아닐 경우 로그인 페이지로 보낸다.
	 * @param request
	 * @param response
	 * @param handler
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("LoginInterceptor 실행");
		
		HttpSession session = request.getSession();
		String loginEmail = (String) session.getAttribute("loginemail");
		
		if(loginEmail == null) {
			response.sendRedirect(request.getContextPath() + "/users/userLogin");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
