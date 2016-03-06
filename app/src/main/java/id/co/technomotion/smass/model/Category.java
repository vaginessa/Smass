package id.co.technomotion.smass.model;

/**
 * Created by omayib on 12/25/14.
 */
public class Category {
    private int id;
    private String name;
    private boolean followed;

    public Category(int id, String name,boolean isFollowed) {
        this.id = id;
        this.name = name;
        this.followed=isFollowed;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", followed=" + followed +
                '}';
    }
}
