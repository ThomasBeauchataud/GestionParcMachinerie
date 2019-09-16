package authentication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Authentication {

    @Around("@annotation(AuthenticationRequired)")
    public Object authenticationRequired(ProceedingJoinPoint joinPoint) throws Throwable {

        //todo check the authentication of the session, if there is no session, check ticket in cookies

        return joinPoint.proceed();
    }
}
