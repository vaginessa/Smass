package id.co.technomotion.smass.controller.event;

/**
 * Created by omayib on 12/28/14.
 */
public class SignupSucceded {
    public String email,token;

    public SignupSucceded(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
