package kz.seisen.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {

    private String name;
    private String description;
    private List<String> items;
    private List<String> npcs;
    private HashMap<String, Room> connectedRooms;




    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
        this.connectedRooms = new HashMap<>();
    }


    public void describe() {
        System.out.println("Room: " + name + " - " + description);
    }







    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }

    public List<String> getNPCs() {
        return npcs;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public void addNPC(String npc) {
        npcs.add(npc);
    }

    public void removeNPC(String npc) {
        npcs.remove(npc);
    }

    public Room getConnectedRoom(String direction) {
        return connectedRooms.get(direction);
    }

    public void connectRoom(String direction, Room room) {
        connectedRooms.put(direction, room);
    }










    public String getItem(String itemName) {
        for (String item : items) {
            if (item.equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public String getNPC(String npcName) {
        for (String npc : npcs) {
            if (npc.equals(npcName)) {
                return npc;
            }
        }
        return null;
    }
}
