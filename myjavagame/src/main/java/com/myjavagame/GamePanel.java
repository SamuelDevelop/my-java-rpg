package com.myjavagame;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
    final int tamanhoTilePadrao = 16;
    final int escala = 3;

    final int tamanhoTile = tamanhoTilePadrao * escala;
    final int maxColunas = 16;
    final int maxLinhas = 12;

    final int alturaTela = maxLinhas * tamanhoTile;
    final int larguraTela = maxColunas * tamanhoTile;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    //Jogador?
    int playerX = 10;
    int playerY = 10;
    int playerVelocidade = 3;

    int FPS = 60;


    public GamePanel(){
        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double nextDrawtime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            
            update();
            repaint();

            try {
                double remainingTime = nextDrawtime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawtime += drawInterval;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        
    }

    public void update(){

        if(keyH.upPressed){
            playerY -= playerVelocidade;
        }
        else if(keyH.downPressed){
            playerY += playerVelocidade;
        }

        if(keyH.leftPressed){
            playerX -= playerVelocidade;
        }
        else if(keyH.rightPressed){
            playerX += playerVelocidade;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.red);

        g2.fillRect(playerX, playerY, tamanhoTile, tamanhoTile);

        g2.dispose();
    }
}
