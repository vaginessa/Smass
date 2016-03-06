package id.co.technomotion.smass.model;

/**
 * Created by omayib on 12/25/14.
 */
public class ItemMenu {
    private int id;
    private String name;

    public ItemMenu(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemMenu)) return false;

        ItemMenu itemMenu = (ItemMenu) o;

        if (id != itemMenu.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
