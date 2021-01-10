package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(value = { "/user/update.jsp", "/user/insert.jsp", "/hotel/update.jsp", "/hotel/insert.jsp",
        "/admin/update.jsp", "/admin/insert.jsp", "/chainStore/update.jsp", "/chainStore/insert.jsp",
        "/menu/update.jsp", "/menu/insert.jsp", "/notific/update.jsp", "/notific/insert.jsp",
        "/index.jsp" }, dispatcherTypes = { DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC })
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("在执行过滤器的核心代码之前");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        System.out.println("在执行过滤器的核心代码之后");
    }

    @Override
    public void destroy() {

    }

}
