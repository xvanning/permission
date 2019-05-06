package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 * Spring异常处理类
 *
 * @author xvanning
 * @create 2019-04-25 21:09
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    private static final String defaultMsg = "system error";

    // 要验证请求是数据请求还是页面请求，有两种方案：
    // 1. 通过request里面取出里面的Header，进行判断
    // 2. 通过直接判断url的后缀来进行判断
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv;
        String url = request.getRequestURL().toString();
        if (url.endsWith(".json")) {
            if ((ex instanceof PermissionException) || (ex instanceof ParamException)) {
                JsonData res = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView", res.toMap());
            } else {
                log.error("unknown json exception: url: " + url, ex);
                JsonData res = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", res.toMap());
            }
        } else if (url.endsWith(".page")) {
            log.error("unknown page exception: url: " + url, ex);
            JsonData res = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", res.toMap());
        } else {
            log.error("unknown exception: url: " + url, ex);
            JsonData res = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", res.toMap());
        }
        return mv;
    }
}