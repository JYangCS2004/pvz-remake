package ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PVZ Clone");

        GamePanel gamePanel = new GamePanel();
        // gamePanel.add(new JButton("Let's Rock"));
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();



    }

    // NEED (Plans for now):
    // Detection System that ensuring a Projectile and a Zombie can only collide ONCE
    // Improve Efficiency for Collision Detection by storing the Row# of each instance
    // Logic that prevents stacking plants on a single tile
    //
}
