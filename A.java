import java.util.HashMap;
import java.util.Scanner;

/**
 * class for current stock we have in our inventory
 */
class Inventory {
    /**
     * all variables are given values separately that can also be changed if we want
     * different stock or add some items or
     * remove some items from the inventory.
     */
    private final int seats = 50;
    private final int frames = 80;
    private final int brake_sets = 25;
    private final int brake_paddles = 100;
    private final int brake_cables = 75;
    private final int levers = 60;
    private final int brake_shoes = 150;
    private final int handlebars = 100;
    private final int wheels = 60;
    private final int tires = 80;
    private final int chains = 100;
    private final int crank_set = 50;
    private final int paddles = 150;
    /**
     * HashMap is used to store key value pairs in which the key is item name and
     * value is its quantity.
     */
    private HashMap<String, Integer> inventory;

    /**
     * This is the class constructor. When an inventory will be created it will
     * automatically store the value of each item
     * in the HashMap at the time of object creation.
     */
    public Inventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
        inventory.put("seats", seats);
        inventory.put("frames", frames);
        inventory.put("brake_set", brake_sets);
        inventory.put("brake_paddles", brake_paddles);
        inventory.put("brake_cables", brake_cables);
        inventory.put("levers", levers);
        inventory.put("brake_shoes", brake_shoes);
        inventory.put("handlebars", handlebars);
        inventory.put("wheels", wheels);
        inventory.put("tires", tires);
        inventory.put("chains", chains);
        inventory.put("crank_set", crank_set);
        inventory.put("paddles", paddles);
    }

    /**
     * This method is used to view the inventory and see the current quantities of
     * each component in inventory.
     */
    void viewOnHandInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
        for (String item : inventory.keySet()) {
            String key = item.toString();
            String value = inventory.get(item).toString();
            System.out.println(key + ":" + value);
        }
    }
}

/**
 * This is a class for one bicycle. It tells about the components which are
 * required to manufacture one bicycle completely.
 */
final class for_one_bicycle {
    HashMap<String, Integer> one_cycle; // HashMap to store quantities of item required to form one bicycle
    HashMap<String, Integer> one_brake_set; // HashMap to store quantities of item required to form one brake_set

    /**
     * This is the class constructor. When an inventory will be created it will
     * automatically store the value of each item
     * in the HashMap at the time of object creation.
     */
    public for_one_bicycle(HashMap<String, Integer> one_cycle, HashMap<String, Integer> one_brake_set) {
        System.out.println("First level:- To assemble a Bicycle");
        this.one_cycle = one_cycle;
        one_cycle.put("seats", 1);
        one_cycle.put("frames", 1);
        one_cycle.put("brake_set", 2);
        one_cycle.put("handlebars", 1);
        one_cycle.put("wheels", 2);
        one_cycle.put("tires", 2);
        one_cycle.put("chains", 1);
        one_cycle.put("crank_set", 1);
        one_cycle.put("paddles", 2);
        for (String item : one_cycle.keySet()) {
            String key = item.toString();
            String value = one_cycle.get(item).toString();
            System.out.println(key + ":" + value);
        }
        System.out.println();
        System.out.println("Second level:- To assemble a Brake Set");
        this.one_brake_set = one_brake_set;
        one_brake_set.put("brake_paddles", 1);
        one_brake_set.put("brake_cables", 1);
        one_brake_set.put("levers", 1);
        one_brake_set.put("brake_shoes", 2);
        for (String name : one_brake_set.keySet()) {
            String key = name.toString();
            String value = one_brake_set.get(name).toString();
            System.out.println(key + ":" + value);
        }
    }
}

public class A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // object of Scanner class

        System.out.println("Enter number of cycles required");
        int number_of_cycles_required = input.nextInt(); // Here user input is taken that how many cycles are required

        HashMap<String, Integer> one_cycle = new HashMap<>(); // HashMap to store components required for making one
                                                              // cycle
        HashMap<String, Integer> one_brake_set = new HashMap<>();// HashMap to store components required for making one
                                                                 // brake set

        System.out.println("Bill of material:- the quantity of each component required to manufacture one cycle");
        for_one_bicycle cycle = new for_one_bicycle(one_cycle, one_brake_set);

        System.out.println();

        System.out.println(
                "Quantities of each component required to manufacture " + number_of_cycles_required + " cycles");
        quantity_of_components_for_meeting_requirement(one_cycle, one_brake_set, number_of_cycles_required);

        System.out.println();

        HashMap<String, Integer> initial_inventory = new HashMap<>();
        Inventory current_stock = new Inventory(initial_inventory);
        System.out.println("On Hand Inventory:- ");
        current_stock.viewOnHandInventory(initial_inventory);

        System.out.println();

        int max_cycles_manufacture = maximum_cycles(initial_inventory, one_cycle, one_brake_set,
                number_of_cycles_required);
        System.out.println(max_cycles_manufacture + " cycles can be manufactured with current stock");

        boolean requirements_met_or_not = requirements_met_or_not(number_of_cycles_required, max_cycles_manufacture);
        String response = requirements_met_or_not ? "Yes" : "No";

        System.out.println();

        System.out.println("Demand of user completed or not: " + response);

        System.out.println();

        // If requirements were not met then print how much more items are required to
        // fulfil the demand else do not print.
        if (!requirements_met_or_not) {
            System.out.println("Quantities of items required more to completely fulfil user demand:- ");
            inventory_net_off(initial_inventory, one_cycle, one_brake_set, number_of_cycles_required);
        } else {
            System.out
                    .println("Congratulations!! The user demand was able to completely fulfil with the current stock.");
        }

        System.out.println();
        // Print remaining stock after producing cycles.
        System.out.println("Remaining inventory after producing " + max_cycles_manufacture + " cycles:-");
        remaining_inventory(initial_inventory, one_cycle, one_brake_set, number_of_cycles_required);
    }

    /**
     * method to calculate hoe much of each component is needed more to complete the
     * user demand.
     */
    private static void inventory_net_off(HashMap<String, Integer> initial_inventory,
            HashMap<String, Integer> one_cycle,
            HashMap<String, Integer> one_brake_set,
            int number_of_cycles_required) {
        for (String item : one_cycle.keySet()) {
            String key = item.toString();
            Integer value = one_cycle.get(item) * number_of_cycles_required - initial_inventory.get(item);
            if (value > 0)
                System.out.println(key + ":" + value);
        }
        for (String item : one_brake_set.keySet()) {
            String key = item.toString();
            int value = one_brake_set.get(item) * number_of_cycles_required - initial_inventory.get(item);
            if (value > 0)
                System.out.println(key + ":" + value);
        }
    }

    /**
     * method to calculate number of each component required to manufacture
     */
    private static void quantity_of_components_for_meeting_requirement(HashMap<String, Integer> one_cycle,
            HashMap<String, Integer> one_brake_set,
            int number_of_cycles_required) {

        for (String item : one_cycle.keySet()) {
            String key = item.toString();
            int value = one_cycle.get(item);
            System.out.println(key + ":" + value * number_of_cycles_required);
        }
        for (String name : one_brake_set.keySet()) {
            String key = name.toString();
            int value = one_brake_set.get(name);
            System.out.println(key + ":" + value * number_of_cycles_required);
        }
    }

    /**
     * method to find whether the required number of bicycles can be manufactured
     * from current stock of components.
     */
    private static boolean requirements_met_or_not(int number_of_cycles_required,
            int max_cycles_manufacture) {
        boolean result;
        if (max_cycles_manufacture >= number_of_cycles_required) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * method to calculate maximum number of cycles that can be manufactured with
     * current stock of components.
     */
    private static int maximum_cycles(HashMap<String, Integer> current_inventory,
            HashMap<String, Integer> one_cycle,
            HashMap<String, Integer> one_brake_set,
            int cycles_required) {
        int result = cycles_required;
        for (String item : one_cycle.keySet()) {
            int value = one_cycle.get(item);
            int inventory_value = current_inventory.get(item);
            result = Math.min(result, inventory_value / value);
        }

        for (String item : one_brake_set.keySet()) {
            int value = one_brake_set.get(item);
            int inventory_value = current_inventory.get(item);
            result = Math.min(result, inventory_value / value);
        }
        return result;
    }

    /**
     * method t0 calculate quantities of items in inventory after manufacturing the
     * maximum number of cycles that could be
     * produced with the stock given.
     */
    private static void remaining_inventory(HashMap<String, Integer> current_inventory,
            HashMap<String, Integer> one_cycle,
            HashMap<String, Integer> one_brake_set,
            int cycles_required) {
        int max_cycles = maximum_cycles(current_inventory, one_cycle, one_brake_set, cycles_required);
        for (String item : one_cycle.keySet()) {
            String key = item.toString();
            int value = one_cycle.get(item);
            int out = current_inventory.get(item) - value * max_cycles;
            System.out.println(key + ":" + out);
        }
        for (String name : one_brake_set.keySet()) {
            String key = name.toString();
            int value = one_brake_set.get(name);
            int out = current_inventory.get(name) - value * max_cycles;
            System.out.println(key + ":" + out);
        }
    }
}
