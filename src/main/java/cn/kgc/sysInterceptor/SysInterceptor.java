package cn.kgc.sysInterceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SysInterceptor
 * @Description TODO 自定义拦截器类
 * @Author zhaojing
 * @Date 2020/10/23 11:39
 * @Version 1.0
 */
public class SysInterceptor extends HandlerInterceptorAdapter {

    //表示在调用具体的控制器之前，就拦截请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断session
       /* HttpSession session = request.getSession();

        Object obj = session.getAttribute(Constants.USER_SESSION);

        //如果是Null，说明没有登录，重定向到指定页面上
        if(obj == null){
            response.sendRedirect(request.getContextPath()+"/401.jsp");
            //不通过
            return false;
        }*/
        //通过
        return true;
    }
}
