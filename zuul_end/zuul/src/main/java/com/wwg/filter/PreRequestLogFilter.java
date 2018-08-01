package com.wwg.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by W2G on 2018/8/1.
 * 实现zuul的自定义过滤器
 */
public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger LOGGER= LoggerFactory.getLogger(PreRequestLogFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();

        PreRequestLogFilter.LOGGER.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
