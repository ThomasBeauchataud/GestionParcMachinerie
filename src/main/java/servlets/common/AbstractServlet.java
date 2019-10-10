package servlets.common;

import common.FunctionalException;
import common.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static servlets.common.ErrorServlet.createErrorUrl;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractServlet extends HttpServlet {

    private static final ArrayList<String> codes = new ArrayList<>(Arrays.asList("login", "inscription", "creation"));
    private static final String loggerName = "servlet";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            handleGet(request, response);
        } catch (Exception e) {
            catchException(request, response, e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            handlePost(request, response);
        } catch (Exception e) {
            catchException(request, response, e);
        }
    }

    private void catchException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Logger.log(loggerName, e.getMessage());
        try {
            if(e instanceof FunctionalException && codes.contains(((FunctionalException) e).getCode())) {
                final String redirectUrl = request.getRequestURL() + "?" + request.getQueryString() + "&message=" + e.getMessage();
                response.sendRedirect(redirectUrl);
            } else {
                response.sendRedirect(createErrorUrl(e.getMessage(), e.getCause().toString()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    protected abstract void handleGet (HttpServletRequest request, HttpServletResponse response) throws Exception;

    protected abstract void handlePost (HttpServletRequest request, HttpServletResponse response) throws Exception;

}
