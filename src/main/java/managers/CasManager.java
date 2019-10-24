package managers;

import beans.common.NavigationController;
import beans.common.SessionManagement;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
@Default
@SuppressWarnings("unchecked")
public class CasManager implements CasManagerInterface {

    @Inject
    private SessionManagement sessionManagement;
    @Inject
    private NavigationController navigationController;

    private String casUrl;
    private String applicationName;

    @PostConstruct
    public void init() {
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            casUrl = (String)env.lookup("cas-url");
            applicationName = (String)env.lookup("application-name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getUserInformation(String ticket) {
        try {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("ticket", ticket);
            final String url = casUrl + "api/consultation";
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(sendPost(url, jsonRequest.toJSONString()));
            if((long)jsonResponse.get("returnCode") == 0) {
                return jsonResponse;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isValidTicket(String ticket) {
        try {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("ticket", ticket);
            jsonRequest.put("service", applicationName);
            final String url = casUrl + "api/verification";
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(sendPost(url, jsonRequest.toJSONString()));
            return (long)jsonResponse.get("returnCode") == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return true;
        /*
        if(request.getSession().getAttribute("active") == null) {
            String ticketValue = getTicketInCookies(request.getCookies());
            if(ticketValue != null && this.isValidTicket(ticketValue)) {
                request.getSession().setAttribute("active", true);
                JSONObject userInformation = this.getUserInformation(ticketValue);
                String name = (String)userInformation.get("name");
                String services = ((String)userInformation.get("services"));
                sessionManagement.setName(name);
                sessionManagement.setAdmin(services.contains("CAS"));
                request.getSession(true);
                return true;
            }
            return false;
        }*/
    }

    @Override
    public String generateCasLoginUrl() {
        return casUrl + "login?service="+applicationName+"&redirect="+navigationController.getApplicationUrl();
    }

    private String getTicketInCookies(Cookie[] cookies) {
        for(Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if(cookieName.equals("ticket")) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private String sendPost(String url, String jsonRequest) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(jsonRequest);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

}
