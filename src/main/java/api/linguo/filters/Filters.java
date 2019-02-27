package api.linguo.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Filters implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig config) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "Accept-Language, Content-Type, Content-Language, Authorization");
        res.addHeader("Access-Control-Max-Age", "3600");
        res.addHeader("Access-Control-Allow-Credentials", "true");

        if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
            res.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            chain.doFilter(req, res);
        }
    }
}