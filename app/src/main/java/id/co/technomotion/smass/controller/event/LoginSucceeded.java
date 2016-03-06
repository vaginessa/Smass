package id.co.technomotion.smass.controller.event;

import id.co.technomotion.smass.model.User;

/**
 * Created by omayib on 12/28/14.
 */
public class LoginSucceeded {
    public String email,token;

    public LoginSucceeded(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
