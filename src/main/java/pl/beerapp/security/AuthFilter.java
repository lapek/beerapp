package pl.beerapp.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;
import pl.beerapp.entities.enumeration.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthFilter extends GenericFilterBean {

    private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header",
            EXPIRE_ERROR_MSG = "Token has expired",
            JWT_ERROR_MSG = "Unable to parse JWT",
            JWT_INVALID_MSG = "Invalid JWT token";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authHeader = httpRequest.getHeader(AuthUtils.AUTH_HEADER_KEY);

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, AUTH_ERROR_MSG);
        } else {
            JWTClaimsSet claimSet = null;
            try {
                claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
                request.setAttribute("claims", claimSet);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(claimSet));
                if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
                } else {
                    chain.doFilter(request, response);
                }
            } catch (ParseException e) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_ERROR_MSG);
            } catch (JOSEException e) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_INVALID_MSG);
            }
        }
    }

    private Authentication getAuthentication(JWTClaimsSet claims) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        Map<String, Object> customClains = claims.getCustomClaims();
        authorities.add(new SimpleGrantedAuthority(customClains.get("role").toString()));
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(
                principal, "", authorities);
    }

}


