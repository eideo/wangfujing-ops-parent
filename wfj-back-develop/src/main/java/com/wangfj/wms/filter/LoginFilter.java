/**
 * 说明: 
 *     网站后台登陆拦截器
 * @Probject Name: shopin-back-demo
 * @Path: com.wangfj.wms.filterLoginFilter.java
 * @Create By chengsj
 * @Create In 2013-8-27 下午2:33:34
 * TODO
 */
package com.wangfj.wms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangfj.wms.util.CookiesUtil;

/**
 * @Class Name LoginFilter
 * @Author chengsj
 * @Create In 2013-8-27
 */
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
//		String username = (String)httpRequest.getSession().getAttribute("username");
		String username = CookiesUtil.getCookies(httpRequest, "username");
		if(username == null || "".equals(username)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
