package id.co.technomotion.smass.utils;

/**
 * Created by omayib on 12/28/14.
 */
public class Api {
    private static String BASE_URL="";
    private static String STAGING_URL="https://smass.herokuapp.com/api/";
    private static String VERSION="v1/mobile/";
    private static String END_POINT=STAGING_URL+VERSION;

    public static String LOGIN(){
        return END_POINT+"signin";
    }
    public static String SIGN_OUT(String token){
        return END_POINT+"signout?token="+token;
    }
    public static String SIGN_UP(){
        return END_POINT+"signup";
    }
    public static String CATEGORIES_ALL(String token){
        return END_POINT+"categories?token="+token;
    }

    public static String CATEGORIES_FOLLOWED(String token){
        return END_POINT+"categories/follow?token="+token;
    }
    public static String FOLLOW_CATEGORY(String token,int categoryId){
        return END_POINT+"categories/"+categoryId+"/follow?token="+token;
    }
    public static String ANNOUNCEMENTS(String token,int categoryId){
        return END_POINT+"announcement/"+categoryId+"/all?token="+token;
    }
    public static String ANNOUNCEMENT_DETAIL(String token,int announcementId){
        return END_POINT+"announcement/"+announcementId+"/detail/?token="+token;
    }
    public static String UN_FOLLOW(String token,int categoryId){
        return END_POINT+"categories/"+categoryId+"/unfollow?token="+token;
    }
}
