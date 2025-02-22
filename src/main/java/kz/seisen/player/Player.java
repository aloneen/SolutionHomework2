package kz.seisen.player;

import kz.seisen.entities.Room;

import java.util.HashMap;

public class Player {


    private String name;
    private String description;
    private Room currentRoom;
    private HashMap<String, Integer> inventory;

    public void describe() {
        System.out.println("Name: " + name + " - Description: " + description);
    }




    public Player(String name, String description) {
        this.name = name;
        this.description = description;
        this.currentRoom = new Room("Main Hall", "All begins there");
        inventory = new HashMap<>();
    }











    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }
    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }









    public void addItem(String item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    public void removeItem(String item) {
        if (inventory.containsKey(item)) {
            if (inventory.get(item) > 0) {
                inventory.put(item, inventory.get(item) - 1);
            }else {
                inventory.remove(item);
            }
        }
    }
}
