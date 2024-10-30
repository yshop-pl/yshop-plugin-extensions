package pl.yshop.plugin.extension.shopgui.requests.entities;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private boolean hidden;
    private List<Product> products;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public List<Product> getProducts() {
        return products;
    }
}
