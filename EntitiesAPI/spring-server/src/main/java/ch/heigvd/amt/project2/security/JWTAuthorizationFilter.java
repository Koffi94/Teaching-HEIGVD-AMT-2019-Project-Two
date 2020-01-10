package ch.heigvd.amt.project2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch.heigvd.amt.project2.security.SecurityConstants.HEADER_STRING;
import static ch.heigvd.amt.project2.security.SecurityConstants.SECRET;
import static ch.heigvd.amt.project2.security.SecurityConstants.TOKEN_PREFIX;

// https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String query = request.getRequestURI();
        List<String> queryArray = parseQuery(query);

        // parse the token
        if (token != null) {
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            // Only admins can reach /users
            if(queryArray.contains("/users/") && !user.contains("admin")) {
                return null;
            }

            // Only the user with id myId can reach /user/myId
            if(queryArray.contains("/user/") && (!user.contains(queryArray.get(queryArray.size() - 1)))) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        } else {
            return null;
        }
    }

    private List<String> parseQuery(String query) {
        String delims = "/";
        String[] tokens = query.split(delims);
        List<String> result = Arrays.asList(tokens);
        return result;
    }
}
