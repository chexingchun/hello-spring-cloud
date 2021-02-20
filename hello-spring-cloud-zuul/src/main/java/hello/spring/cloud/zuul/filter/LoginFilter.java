package hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends ZuulFilter {
    public LoginFilter() {
        super();
    }

    /**
     * 过滤类型
     * pre:路由之前
     * routing:路由之时
     * post:路由之后
     * error:发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext=RequestContext.getCurrentContext();
        HttpServletRequest request=currentContext.getRequest();
        String token=request.getParameter("token");
        if(token==null){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                HttpServletResponse response=currentContext.getResponse();
                response.setContentType("text/html;charset=utf-8");
                currentContext.getResponse().getWriter().write("非法请求");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
