package id.co.technomotion.smass.controller.event;

import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 12/28/14.
 */
public class LoadCategoriesSucceded {

    public List<Category> categoryList=new ArrayList<>();

    public LoadCategoriesSucceded(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
