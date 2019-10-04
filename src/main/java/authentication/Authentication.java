package authentication;

import common.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
public class Authentication {

    private CasClientInterface casClient;

    @Inject
    public Authentication(CasClientInterface casClient) {
        this.casClient = casClient;
    }

    @Around("@annotation(AuthenticationRequired)")
    public Object authenticationRequired(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpServletResponse response = (HttpServletResponse) joinPoint.getArgs()[1];

        try {
            String ticket = request.getParameter("ticket");
            if(ticket != null) {
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

            return joinPoint.proceed();

        } catch (Exception e) {
            Logger.log("authentication", e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}
