package servlets;

import common.FunctionalException;
import common.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            handleGet(request, response);
        } catch (FunctionalException e) {
            catchFunctionalException(response, e);
        } catch (Exception e) {
            catchException(e);
        }
    }

    /**
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            handlePost(request, response);
        } catch (FunctionalException e) {
            catchFunctionalException(response, e);
        } catch (Exception e) {
            catchException(e);
        }
    }

    private void catchFunctionalException(HttpServletResponse response, FunctionalException e) {
        Logger.log("servlet", e.getMessage());
    }

    private void catchException(Exception e) {
        Logger.log("servlet", e.getMessage());
    }

    protected abstract void handleGet (HttpServletRequest request, HttpServletResponse response) throws Exception;

    protected abstract void handlePost (HttpServletRequest request, HttpServletResponse response) throws Exception;

}
