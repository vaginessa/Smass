package id.co.technomotion.smass.controller.event;

import java.util.ArrayList;
import java.util.List;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 12/29/14.
 */
public class LoadFollowedCategoriesSucceded {
    public List<Category> categoryList;

    public LoadFollowedCategoriesSucceded(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
