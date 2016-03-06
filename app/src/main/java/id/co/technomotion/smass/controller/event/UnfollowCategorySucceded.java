package id.co.technomotion.smass.controller.event;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 1/1/15.
 */
public class UnfollowCategorySucceded {
    public Category category;

    public UnfollowCategorySucceded(Category category) {
        this.category = category;
    }
}
