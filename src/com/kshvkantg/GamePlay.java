package com.kshvkantg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    //key Listener is to check the response of the keys we press
    //action Listener is to respond against the ball

    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private MapGenerator map;
    public GamePlay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
//        g.setColor(Color.RED);
//        g.fillRect(0,0,100,100);
        map.draw((Graphics2D)(g));
        //border
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,697);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("" + score,590,30);



        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,200,8);

        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX,ballPosY,20,20);

        if(ballPosY > 570 || score >= 210){
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over",210,270);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Score : " + score ,200,310);

        }
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,200,8))){
                ballYDir = -ballYDir;
            }
            ballPosX += ballXDir;
            ballPosY += ballYDir;
            if(ballPosX < 0){
                ballXDir = -ballXDir;
            }

            if(ballPosY < 0){
                ballYDir = -ballYDir;
            }

            if(ballPosX > 670){
                ballXDir = -ballXDir;
            }

        }

        A: for (int i = 0 ; i < map.map.length ; i++){
            for (int j = 0 ; j < map.map[0].length ; j++){
                if(map.map[i][j] > 0){
                    int brickX = j*map.brickWidth + 80;
                    int brickY = i*map.brickHeight + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;

                    Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
                    Rectangle brickRect = rect;

                    if(ballRect.intersects(brickRect)){
                        map.setBrickValue(0,i,j);
                        totalBricks--;
                        score += 10;

                        if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                            ballXDir = -ballXDir;
                        }
                        else {
                            ballYDir = -ballYDir;
                        }
                        break A;
                    }

                }
            }
        }

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }
            else {
                System.out.println("Execute" + playerX);
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10){
               playerX = 10;
            }
            else {
                moveLeft();
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }


}
