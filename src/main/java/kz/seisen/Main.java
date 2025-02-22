package kz.seisen;

import kz.seisen.player.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Player", "Player");
        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}