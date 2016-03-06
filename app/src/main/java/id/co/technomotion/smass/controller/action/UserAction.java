package id.co.technomotion.smass.controller.action;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 12/26/14.
 */
public interface UserAction {

    void signout(String token);
    void getCategories(String token);
    void getFollowedCategories(String token);
    void getAnnouncements(String token,int categoryId);
    void getAnnouncementDetail(String token,int announcementId);
    void follow(String token,Category category);
    void unFollow(String token,Category category);

}
