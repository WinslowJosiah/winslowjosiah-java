package winslow_assignment3;

import java.util.Arrays;
import java.util.List;
import static winslow_assignment3.Winslow_Assignment3.inputChoiceChar;

public class Challenge3_18 extends Challenge {
    public Challenge3_18() {
        super("Restaurant Selector");
    }
    
    /*
    You have a group of friends coming to visit for your high school reunion,
    and you want to take them out to eat at a local restaurant. You aren't sure
    if any of them have dietary restrictions, but your restaurant choices are as
    follows:
    
        Joe's Gourmet Burgers - Vegetarian: No, Vegan: No, Gluten-Free: No
        Main Street Pizza Company - Vegetarian: Yes, Vegan: No, Gluten-Free: Yes
        Corner Cafe - Vegetarian: Yes, Vegan: Yes, Gluten-Free: Yes
        Mama's Fine Italian - Vegetarian: Yes, Vegan: No, Gluten-Free: No
        The Chef's Kitchen - Vegetarian: Yes, Vegan: Yes, Gluten-Free: Yes
    
    Write a program that asks whether any members of your party are vegetarian,
    vegan, or gluten-free, and then displays only the restaurants you may take
    the group in. Here is an example of the program's output:
    
        Is anyone in your party a vegetarian?  yes [Enter]
        Is anyone in your party a vegan?  no [Enter]
        Is anyone in your party gluten-free?  yes [Enter]
        Here are your restaurant choices:
            Main Street Pizza Company
            Corner Cafe
            The Chef's Kithen
    
    Here is another example of the program's output:
    
        Is anyone in your party a vegetarian?  yes [Enter]
        Is anyone in your party a vegan?  yes [Enter]
        Is anyone in your party gluten-free?  yes [Enter]
        Here are your restaurant choices:
            Corner Cafe
            The Chef's Kithen
    */
    
    private final List<Restaurant> restaurants = Arrays.asList(
            new Restaurant("Joe's Gourmet Burgers", false, false, false),
            new Restaurant("Main Street Pizza Company", true, false, true),
            new Restaurant("Corner Cafe", true, true, true),
            new Restaurant("Mama's Fine Italian", true, false, false),
            new Restaurant("The Chef's Kitchen", true, true, true)
    );
    
    public void execute() {
        // We get a boolean for each choice by checking if the choice is 'y'
        boolean partyIsVegetarian = ('y' == inputChoiceChar(
                "yn",
                "Is anyone in your party a vegetarian?  ",
                "Enter Y or N."
        ));
        
        boolean partyIsVegan = ('y' == inputChoiceChar(
                "yn",
                "Is anyone in your party a vegan?  ",
                "Enter Y or N."
        ));
        
        boolean partyIsGlutenFree = ('y' == inputChoiceChar(
                "yn",
                "Is anyone in your party gluten-free?  ",
                "Enter Y or N."
        ));
        
        System.out.println("Here are your restaurant choices:");
        
        for (Restaurant r : restaurants) {
            // Here, we chain a lot of statements in the form (!p || r),
            // where p is one of the party's requirements,
            // and r is the restaurant's ability to fulfill that requirement.
            // (!p || r) is true if the party doesn't have this requirement,
            // or if this requirement can be satisfied by the restaurant.
            if ((!partyIsVegetarian || r.isVegetarian())
                    && (!partyIsVegan || r.isVegan())
                    && (!partyIsGlutenFree || r.isGlutenFree())) {
                // If this is a restaurant the party can eat at,
                // print its name
                System.out.printf("\t%s\n", r.getName());
            }
        }
    }
}
