package winslow_assignment3;

public class Restaurant {
    private final String name;
    private final boolean vegetarian;
    private final boolean vegan;
    private final boolean glutenFree;
    
    public Restaurant(
            String name, boolean vegetarian, boolean vegan, boolean glutenFree
    ) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isVegetarian() {
        return vegetarian;
    }
    
    public boolean isVegan() {
        return vegan;
    }
    
    public boolean isGlutenFree() {
        return glutenFree;
    }
}
