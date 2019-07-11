package ink.educat.web.auth;

import ink.educat.security.service.api.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component(value = "AuthenticationFilter")
@Order(1)
public class AuthorizationFilter implements Filter {

    private final SecurityService securityService;

    @Autowired
    public AuthorizationFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
    }
}
