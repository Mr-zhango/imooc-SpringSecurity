/**
 * 
 */
package com.imooc.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhailiang
 *
 */
public interface RbacService {

    /**
     * 判断的是否拥有权限
     * @param request
     * @param authentication
     * @return
     */
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
