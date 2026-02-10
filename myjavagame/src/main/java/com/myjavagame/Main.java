package com.myjavagame;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Java RGP");

        GamePanel gamePanel = new GamePanel();
        janela.add(gamePanel);
        janela.pack();

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        gamePanel.startGameThread();
    }
}