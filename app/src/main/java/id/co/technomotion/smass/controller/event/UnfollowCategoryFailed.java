package id.co.technomotion.smass.controller.event;

import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 1/1/15.
 */
public class UnfollowCategoryFailed {
    public Category category;

    public UnfollowCategoryFailed(Category category) {
        this.category = category;
    }
}
