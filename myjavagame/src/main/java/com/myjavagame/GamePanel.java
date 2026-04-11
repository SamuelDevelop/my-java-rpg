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

    int frames = 0;
    long lastCheck = System.currentTimeMillis();
    int currentFPS = 0;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    //Jogador?
    double playerX = 10;
    double playerY = 10;
    int playerVelocidade = 100;

    int FPS = 60;
    double deltaTime = 1.0 / FPS;


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

/*
    Primeira ideia de Game Loop:

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
*/
    @Override
    public void run(){

        double deltaTime;
        long lastTime = System.nanoTime();

        while (gameThread != null){

            long currentTime = System.nanoTime();
            deltaTime = (currentTime - lastTime) / 1000000000.0;
            lastTime = currentTime;

            update(deltaTime);
            repaint();

            frames++;

            if(System.currentTimeMillis() - lastCheck >= 1000){
                currentFPS = frames;
                frames = 0;
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + currentFPS);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(double deltaTime){

        if(keyH.upPressed){
            playerY -= playerVelocidade * deltaTime;
        }
        else if(keyH.downPressed){
            playerY += playerVelocidade * deltaTime;
        }

        if(keyH.leftPressed){
            playerX -= playerVelocidade * deltaTime;
        }
        else if(keyH.rightPressed){
            playerX += playerVelocidade * deltaTime;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.red);

        g2.fillRect((int) playerX, (int) playerY, tamanhoTile, tamanhoTile);
    }
}
