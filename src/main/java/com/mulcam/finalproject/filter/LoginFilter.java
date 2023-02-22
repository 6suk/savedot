package com.mulcam.finalproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import com.mulcam.finalproject.dto.UserDTO;

@Component
public class LoginFilter implements Filter {

	private static final String[] whiteList = { "/login/*", "/", "/mate/list", "/mate/detail/*", "/login/", "/logout",
			"/join", "/savedot/upload/*", "/savedot/display/*", "/info/news", "/css/*", "/js/*" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		/** 화이트 리스트 제외, 로그인 체크 */
		if (isLoginCheckPath(requestURI)) {
			HttpSession session = httpRequest.getSession(false);
			if (isEmpty(session)) {
				httpResponse.sendRedirect("/mate/list"); // 로그인 페이지로 이동
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/** 화이트 리스트 제외 */
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
	}

	private boolean isEmpty(Object obj) {
		HttpSession session = (HttpSession) obj;
		if (session == null)
			return true;
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		if (userDTO != null)
			return false;
		return true;
	}

}
