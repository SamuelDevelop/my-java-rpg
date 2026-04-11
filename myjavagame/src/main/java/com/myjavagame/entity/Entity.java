package com.myjavagame.entity;

import java.awt.image.BufferedImage;


public class Entity {
    public double x, y;
    public double velocidade;

    public BufferedImage cima1, cima2, baixo1, baixo2, direita1, direita2, esquerda1, esquerda2;
    public String direcao;

    public int contadorSprites = 0;
    public int numeroSprite = 1;
}
