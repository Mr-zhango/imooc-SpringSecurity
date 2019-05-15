/**
 * 
 */
package com.imooc.security.rbac.service.impl;

import com.imooc.security.rbac.domain.Admin;
import com.imooc.security.rbac.service.RbacService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author zhailiang
 *
 */
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    /**
     * 判断是否匹配
     */
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();

		boolean hasPermission = false;

		// 这里的Admin是用户实体类，不是管理员的意思
		if (principal instanceof Admin) {
			//如果用户名是admin，就永远返回true
			if (StringUtils.equals(((Admin) principal).getUsername(), "admin")) {
				hasPermission = true;
			} else {
				// 读取用户所拥有权限的所有URL
				Set<String> urls = ((Admin) principal).getUrls();
				//循环所有的url,进行授权
				for (String url : urls) {
				    //如果url匹配,进行授权
					if (antPathMatcher.match(url, request.getRequestURI())) {
						hasPermission = true;
						break;
					}
				}
			}
		}

		return hasPermission;
	}

}
