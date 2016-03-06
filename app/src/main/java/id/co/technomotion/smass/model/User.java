package id.co.technomotion.smass.model;

import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.controller.action.UserAction;

/**
 * Created by omayib on 12/25/14.
 */
public class User {
    private String email;
    private String token;
    private String name;
    private List<Category> listCategory;
    private UserAction userAction;

    /***
     * create a valid user
     * @param email
     * @param token
     * @param userAction
     */
    public User(String email,String token,UserAction userAction) {
        this.email = email;
        this.token=token;
        this.userAction=userAction;
        this.listCategory=new ArrayList<>();
    }
    public void getCategories(){
        userAction.getCategories(token);
    }
    public void getFollowedCategories(){
        userAction.getFollowedCategories(token);
    }
    public void follow(Category category){
//        if(!listCategory.contains(category))
//            this.listCategory.add(category);
        userAction.follow(token,category);
    }
    public void unfollow(Category category){
        userAction.unFollow(token,category);
    }
    public List<Category> following(){
        return listCategory;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getAnnouncements(int idCategory){
        userAction.getAnnouncements(token,idCategory);
    }

    public void getAnnouncementDetail(int idAnnouncement){
        userAction.getAnnouncementDetail(token,idAnnouncement);
    }
    public void signout(){
        userAction.signout(token);
    }
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", listCategory=" + listCategory +
                '}';
    }
}
