package ch.heigvd.amt.project2.api.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.heigvd.amt.project2.security.SecurityConstants.*;

// Sources: https://www.programcreek.com/java-api-examples/?class=javax.servlet.http.HttpServletRequest&method=getHeader

@Component
@Order(1)
public class AuthorizationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthorizationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String token = req.getHeader(AUTHORIZATION_TOKEN);

        if(token != null){
            String subject = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
            this.context.log(token);
            if(subject.matches("([0-9]*);(.*)admin(.*)|(.*)user(.*)")) {
                request.setAttribute("subject", subject);
                chain.doFilter(request, response);
            }
        } else {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {
        //we can close resources here
    }

}
