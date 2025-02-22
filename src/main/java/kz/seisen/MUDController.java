package kz.seisen;

import kz.seisen.entities.Room;
import kz.seisen.player.Player;

import java.util.HashMap;
import java.util.Scanner;

public class MUDController {
    private final Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the MUD game! Type 'help' for a list of commands.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            handleInput(input);
        }
        scanner.close();
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                if (argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Invalid command. Try 'pick up <itemName>'");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Exiting game. Goodbye!");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for available commands.");
        }
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        currentRoom.describe();
        System.out.println("Items here: " + currentRoom.getItems());
        System.out.println("NPCs here: " + currentRoom.getNPCs());
    }

    private void move(String direction) {
        if (direction.isEmpty()) {
            System.out.println("Specify a direction: north, south, east, or west.");
            return;
        }
        Room nextRoom = player.getCurrentRoom().getConnectedRoom(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You moved " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        String item = currentRoom.getItem(itemName);
        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }

    private void checkInventory() {
        System.out.println("You are carrying: ");
        HashMap<String, Integer> inventory = player.getInventory();
        for (String item : inventory.keySet()) {
            System.out.println(item + " x" + inventory.get(item));
        }
    }


    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describe the current room");
        System.out.println("move <direction> - Move in a specified direction (e.g., north, south, east, west)");
        System.out.println("pick up <itemName> - Pick up an item from the room");
        System.out.println("inventory - Show items you are carrying");
        System.out.println("help - Show available commands");
        System.out.println("quit/exit - End the game");
    }
}