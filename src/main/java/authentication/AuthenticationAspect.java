package authentication;

import common.Logger;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
public class AuthenticationAspect extends Aspects {

    @Inject
    private CasClientInterface casClient;

    @Pointcut("@annotation(authentication.AuthenticationRequired)")
    private void authenticationRequiredPointcut() {}

    @Before("authentication.AuthenticationAspect.authenticationRequiredPointcut()")
    private void authenticationRequiredInterceptor(JoinPoint joinPoint) {
        Logger.log("aspect","test");
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[1];

        try {
            if(request.getSession().getAttribute("username") == null) {
                String ticket = request.getParameter("ticket");
                if (ticket != null) {
                    request.getSession().setAttribute("userName", casClient.getNameByTicket(ticket));
                    response.addCookie(new Cookie("ticket", ticket));
                    request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
                }
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("ticket")) {
                            ticket = cookie.getValue();
                        }
                    }
                }
                if (ticket != null && casClient.isValidTicket(ticket)) {
                    if (request.getSession().getAttribute("userName") == null) {
                        request.getSession().setAttribute("userName", casClient.getNameByTicket(ticket));
                        response.addCookie(new Cookie("ticket", ticket));
                    }
                } else {
                    casClient.redirectCas(response);
                }
            }

        } catch (Exception e) {
            Logger.log("authentication", e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
