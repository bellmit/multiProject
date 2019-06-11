package service;

import dao.interfaces.UserDao;
import domain.User;
import org.json.JSONObject;
import qualifiers.CustomHash;
import rest.auth.JWTHelper;
import util.PasswordHash;
import util.RoleConverter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
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

    private static final String PROVIDER_GOOGLE = "Google";
    private static final String PROVIDER_FACEBOOK = "Facebook";

    @Inject
    UserDao userDao;

    @Inject
    JWTHelper jwtHelper;

    @Inject
    @CustomHash
    PasswordHash passwordHash;

    public String login(String email, String password) {
        User foundUser = userDao.findByEmail(email);
        if (foundUser == null) {
            throw new NotFoundException();
        }
        if (passwordHash.authenticate(password.toCharArray(), foundUser.getPassword())) {
            List<String> userRoles = RoleConverter.roleArrayToStringArray(foundUser.getRoles());
            return jwtHelper.generatePrivateKey(foundUser.getId(), foundUser.getEmail(), userRoles);
        } else {
            throw new BadRequestException("Wrong username password combination");
        }
    }

    public void addUser(User user) {
        if (user.getPassword() == null || user.getPassword().equals("") ||
                user.getEmail() == null || user.getEmail().equals("")) {
            throw new BadRequestException("User need to contain a password and email");
        } else {
            user.setPassword(passwordHash.hash(user.getPassword().toCharArray()));
            userDao.create(user);
        }
    }

    public String socialLogin(String token, String provider) {
        JSONObject response = null;
        if (provider.equals(PROVIDER_FACEBOOK)) {
            String url = "https://graph.facebook.com/v3.2/me?access_token=" + token + "&debug=all&fields=id,name,email,first_name,last_name&format=json&method=get&pretty=0&suppress_http_code=1&transport=cors";
            response = new JSONObject(retrieveUserFromProvider(url));
        } else if (provider.equals(PROVIDER_GOOGLE)) {
            String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
            response = new JSONObject(retrieveUserFromProvider(url));
        } else {
            throw new BadRequestException("Bad social media provider");
        }

        String email;
        email = (String) response.get("email");
        User foundUser = userDao.findByEmail(email);
        if (foundUser == null) {
            if (provider.equals(PROVIDER_FACEBOOK)) {
                createNewFacebookUser(response);
            } else {
                createNewGoogleUser(response);
            }
            foundUser = userDao.findByEmail(email);
        }

        List<String> userRoles = RoleConverter.roleArrayToStringArray(foundUser.getRoles());
        return jwtHelper.generatePrivateKey(foundUser.getId(), foundUser.getEmail(), userRoles);
    }

    private void createNewFacebookUser(JSONObject facebookResponse) {
        String email = (String) facebookResponse.get("email");
        String firstName = (String) facebookResponse.get("first_name");
        String lastName = (String) facebookResponse.get("last_name");

        User user = initUser(firstName, lastName);
        user.setEmail(email);
        userDao.create(user);
    }

    private void createNewGoogleUser(JSONObject googleResponse) {
        String email = (String) googleResponse.get("email");
        String firstName = (String) googleResponse.get("given_name");
        String lastName = (String) googleResponse.get("family_name");

        User user = initUser(firstName, lastName);
        user.setEmail(email);
        userDao.create(user);
    }

    private User initUser(String firstName, String lastName) {
        User newUser = new User();
        if (!firstName.equals("")) {
            newUser.setFirstName(firstName);
        }
        if (!lastName.equals("")) {
            newUser.setLastName(lastName);
        }
        return newUser;
    }

    private String retrieveUserFromProvider(String uri) {
        String content = null;
        try {
            URL url = new URL(uri);
            content = sendRequest(url);
        } catch (IOException e) {
            Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        if (content == null) {
            throw new NotFoundException("No user found");
        }
        return content;
    }

    private String sendRequest(URL url) throws IOException {
        StringBuilder content;
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return String.valueOf(content);
    }
}
