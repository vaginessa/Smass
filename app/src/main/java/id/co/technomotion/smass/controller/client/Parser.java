package id.co.technomotion.smass.controller.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.controller.action.ParsingResponse;
import id.co.technomotion.smass.model.Announcement;
import id.co.technomotion.smass.model.Category;
import id.co.technomotion.smass.model.User;

/**
 * Created by omayib on 12/28/14.
 */
public class Parser {

    public static void LOGIN(String data,ParsingResponse<String> callback){
        System.out.println("from server "+data);
        String result="";
        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")) {
                callback.onFailure("gagal server");
                return;
            }
            result=ob.getString("token");
            callback.onSuccess(result);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onSuccess("fail exception");
        }
    };

    public static void SIGNUP(String data,ParsingResponse<String> callback){
        System.out.println("from server "+data);
        String result="";
        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")) {
                callback.onFailure("gagal server");
                return;
            }
            result=ob.getString("token");
            callback.onSuccess(result);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("gagal server");
        }
    };
    public static void SIGN_OUT(String data,ParsingResponse<Void> callback){
        System.out.println("from server "+data);
        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")) {
                callback.onFailure("gagal server");
                return;
            }
            callback.onSuccess(null);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("gagal server");
        }
    };
    public static void CATEGORIES_FOLLOWED(String data,ParsingResponse<List<Category>> callback){
        System.out.println("from server "+data);
        List<Category> categoryList=new ArrayList<>();

        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")){
                callback.onFailure("failed");
                return;
            }
            JSONArray arrCategories=ob.getJSONArray("data");
            for (int i = 0; i <arrCategories.length() ; i++) {
                JSONObject obCat=arrCategories.getJSONObject(i);
                categoryList.add(new Category(obCat.getInt("id"),obCat.getString("name")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        callback.onSuccess(categoryList);
    }
    public static void CATEGORIES(String data,ParsingResponse<List<Category>> callback){
        System.out.println("from server "+data);
        List<Category> categoryList=new ArrayList<>();

        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")){
                callback.onFailure("failed");
                return;
            }
            JSONArray arrCategories=ob.getJSONArray("data");
            for (int i = 0; i <arrCategories.length() ; i++) {
                JSONObject obCat=arrCategories.getJSONObject(i);
                categoryList.add(new Category(obCat.getInt("id"),obCat.getString("name"),obCat.getBoolean("followed")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        callback.onSuccess(categoryList);
    }
    public static void FOLLOW_CATEGORY(String data, ParsingResponse<Void> callback){
        System.out.println("server respnse "+data);
        try {
            JSONObject ob = new JSONObject(data);
            if(!ob.getBoolean("success")){
                callback.onFailure("failed");
                return;
            }
            JSONObject obData=ob.getJSONObject("data");
            callback.onSuccess(null);

        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("error exception");
        }
    }

    public static void UNFOLLOW_CATEGORY(String data, ParsingResponse<Void> callback){
        System.out.println("server respnse "+data);
        try {
            JSONObject ob = new JSONObject(data);
            if(!ob.getBoolean("success")){
                callback.onFailure("failed");
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("error exception");
        }
        callback.onSuccess(null);
    }

    public static void ANNOUNCEMENTS(String data,ParsingResponse<List<Announcement>> callback) {
        System.out.println("response " + data);
        List<Announcement> announcementList = new ArrayList<>();
        try {
            JSONObject ob = new JSONObject(data);
            if (!ob.getBoolean("success")) {
                callback.onFailure("failed response");
                return;
            }
            JSONArray arr = ob.getJSONArray("data");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject ann = arr.getJSONObject(i);
                Announcement announcment = new Announcement.Builder()
                        .id(ann.getInt("id"))
                        .title(ann.getString("title"))
                        .date(ann.getString("date")).build();
                announcementList.add(announcment);
            }
            callback.onSuccess(announcementList);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("failed response");
        }
    }

    public static void ANNOUNCEMENT_DETAIL(String data,ParsingResponse<Announcement> callback){
        System.out.println("server "+data);
        try {
            JSONObject ob=new JSONObject(data);
            if(!ob.getBoolean("success")){
                callback.onFailure("failed response");
                return;
            }

            JSONObject obAnn=ob.getJSONObject("data");
            Announcement ann=new Announcement.Builder()
                    .content(obAnn.getString("content"))
                    .date(obAnn.getString("date"))
                    .title(obAnn.getString("title"))
                    .id(obAnn.getInt("id")).build();
            callback.onSuccess(ann);

        } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure("failed exception");
        }

    }
}
