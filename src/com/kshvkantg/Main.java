package com.kshvkantg;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame obj = new JFrame();//class to make gui
        GamePlay gamePlay = new GamePlay();

        obj.setBounds(10,10,700,600); // its used to set boundaries of window
        obj.setTitle("Break The Bricks");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
