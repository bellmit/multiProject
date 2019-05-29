package service;

import dao.interfaces.UserDao;
import domain.User;
import org.json.JSONObject;
import rest.auth.JWTHelper;
import util.RoleConverter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AuthService {

    @Inject
    UserDao userDao;

    @Inject
    JWTHelper jwtHelper;

    public String login(String token) {
        JSONObject facebookResponse = new JSONObject(retrieveUserFromFacebook(token));
        String email = (String) facebookResponse.get("email");

        User foundUser = userDao.findByEmail(email);
        if (foundUser == null) {
            createNewUser(facebookResponse);
            foundUser = userDao.findByEmail(email);
        }

        List<String> userRoles = RoleConverter.roleArrayToStringArray(foundUser.getRoles());
        return jwtHelper.generatePrivateKey(foundUser.getId(), foundUser.getEmail(), userRoles);
    }

    private void createNewUser(JSONObject facebookResponse) {
        String email = (String) facebookResponse.get("email");
        String firstName = (String) facebookResponse.get("name");

        User newUser = new User();
        newUser.setEmail(email);
        if (!firstName.equals("")) {
            newUser.setFirstName(firstName);
        }
        userDao.create(newUser);
    }

    private String retrieveUserFromFacebook(String token) {
        StringBuilder content = null;
        try {
            URL url = new URL("https://graph.facebook.com/v3.2/me?access_token=" + token + "&debug=all&fields=id,name,email&format=json&method=get&pretty=0&suppress_http_code=1&transport=cors");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        if (content == null) {
            throw new NotFoundException("No user found");
        }
        return content.toString();
    }
}
