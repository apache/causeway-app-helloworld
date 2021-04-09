package org.apache.isis.security.spring.webmodule;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.core.interaction.session.InteractionFactory;
import org.apache.isis.core.security.authentication.Authentication;
import org.apache.isis.core.security.authentication.standard.SimpleAuthentication;

/**
 * This is a patch to replace the version provided in 2.0.0-M5
 */
public class SpringSecurityFilter implements Filter {

    @Autowired
    private InteractionFactory isisInteractionFactory;

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        org.springframework.security.core.Authentication springAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if(springAuthentication==null
                || !springAuthentication.isAuthenticated()) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return; // not authenticated
        }

        String principalIdentity;
        Object principal = springAuthentication.getPrincipal();
        if (principal instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            final Object login = oAuth2User.getAttributes().get("login");
            principalIdentity = login instanceof String ? (String)login : oAuth2User.getName();
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return; // unknown principal type, not handled
        }

        UserMemento user = UserMemento.ofNameAndRoleNames(principalIdentity,
                Stream.of("org.apache.isis.viewer.wicket.roles.USER"));
        SimpleAuthentication authentication = SimpleAuthentication.validOf(user);
        authentication.setType(Authentication.Type.EXTERNAL);

        isisInteractionFactory.runAuthenticated(
                authentication,
                ()->{
                    filterChain.doFilter(servletRequest, servletResponse);
                });
    }

}
