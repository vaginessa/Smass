package id.co.technomotion.smass.controller.event;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 12/29/14.
 */
public class FollowCategorySucceded {
    public Category category;

    public FollowCategorySucceded(Category category) {
        this.category = category;
    }
}
