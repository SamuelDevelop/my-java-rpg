package com.myjavagame.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.myjavagame.GamePanel;
import com.myjavagame.KeyHandler;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;
    
    public Player(GamePanel gamePanel, KeyHandler keyH){
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    private void setDefaultValues(){
        this.x = 10.0;
        this.y = 10.0;
        this.velocidade = 100.0;
        this.direcao = "esquerda";
    }

    public void setDefaultValues(int x, int y, int velocidade){
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
    }

    public void getPlayerImage(){
        try {
            cima1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_up_1.png"));
            cima2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_up_2.png"));
            baixo1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_down_1.png"));
            baixo2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_down_2.png"));
            esquerda1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_left_1.png"));
            esquerda2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_left_2.png"));
            direita1 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_right_1.png"));
            direita2 = ImageIO.read(getClass().getResourceAsStream("/player_sprites/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(double deltaTime){

        if(keyH.upPressed){
            this.direcao = "cima";
            this.y -= this.velocidade * deltaTime;
        }
        else if(keyH.downPressed){
            this.direcao = "baixo";
            this.y += this.velocidade * deltaTime;
        }

        if(keyH.leftPressed){
            this.direcao = "esquerda";
            this.x -= this.velocidade * deltaTime;
        }
        else if(keyH.rightPressed){
            this.direcao = "direita";
            this.x += this.velocidade * deltaTime;
        }

        contadorSprites ++;
        if(contadorSprites > 10){
            if(numeroSprite == 1){
                numeroSprite = 2;
            }
            else if(numeroSprite == 2){
                numeroSprite = 1;
            }

            contadorSprites = 0;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direcao) {
            case "cima":
                if(numeroSprite == 1){
                    image = cima1;
                }
                if(numeroSprite == 2){
                    image = cima2;
                }                
                break;
        
            case "baixo":
                if(numeroSprite == 1){
                    image = baixo1;
                }
                if(numeroSprite == 2){
                    image = baixo2;
                } 
                break;

            case "direita":
                if(numeroSprite == 1){
                    image = direita1;
                }
                if(numeroSprite == 2){
                    image = direita2;
                } 
                break;

            case "esquerda":
                if(numeroSprite == 1){
                    image = esquerda1;
                }
                if(numeroSprite == 2){
                    image = esquerda2;
                }
                break;

            default:
                break;
        }

        g2.drawImage(image, (int) x, (int) y, gamePanel.getTamanhoTile(), gamePanel.getTamanhoTile(), null);
    }
}
