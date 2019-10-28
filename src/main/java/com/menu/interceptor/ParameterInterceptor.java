package com.menu.interceptor;


import com.menu.util.AbstractInfoRequest;
import com.menu.util.AbstractPageRequest;
import com.menu.util.AbstractPageRequestUtils;
import com.menu.util.AbstractRequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName movies_box
 * @Author LHB
 * @Data 2019/5/23 11:30
 * @Version 1.0
 * @Description
 */
@Component
public class ParameterInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AbstractRequestUtils.remove();
        AbstractInfoRequest abstractInfoRequest=new AbstractInfoRequest();
        AbstractPageRequest abstractPageRequest=new AbstractPageRequest();
        String token = request.getHeader("token");
        if (token!=null&&token.trim()!=""){
            abstractInfoRequest.setToken(token);
            abstractPageRequest.setToken(token);
        }
        String facilitiesType = request.getHeader("facilitiesType");
        if (facilitiesType!=null&&facilitiesType.trim()!=""){
            abstractInfoRequest.setFacilitiesType(facilitiesType);
            abstractPageRequest.setFacilitiesType(facilitiesType);
        }
        String version = request.getHeader("version");
        if (version!=null&&version.trim()!=""){
            abstractInfoRequest.setVersion(version);
            abstractPageRequest.setFacilitiesType(version);
        }
        String currPage = request.getHeader("currPage");
        if (currPage!=null&&currPage.trim()!=""){
            abstractPageRequest.setCurrPage(Integer.valueOf(currPage));
        }
        String pageSize = request.getHeader("pageSize");
        if (pageSize!=null&&pageSize.trim()!=""){
            Integer integer = Integer.valueOf(pageSize);
            if (integer>200){
                integer=100;
            }
            abstractPageRequest.setPageSize(integer);
        }
        AbstractRequestUtils.set(abstractInfoRequest);
        AbstractPageRequestUtils.set(abstractPageRequest);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
