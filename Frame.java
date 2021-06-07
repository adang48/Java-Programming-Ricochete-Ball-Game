//****************************************************************************************************************************
//Program name: "Ricochete Ball".  This program shows how to produce an object moving at constant speed while letting the    *
//user determine its direction. The coordinates of the moving ball are displayed in real time. There are 3 active buttons.   *
// The user must press the clear button before repeating the process with a new direction and speed.                         *
//Copyright (C) 2021 Albert Dang                                                                                             * 
//This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License  *
//version 3 as published by the Free Software Foundation.                                                                    *
//This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
//warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.     *
//A copy of the GNU General Public License v3 is available here:  <https://www.gnu.org/licenses/>.                           *
//****************************************************************************************************************************

//Ruler:=1=========2=========3=========4=========5=========6=========7=========8=========9=========0=========1=========2=========3**

//Author information:
  //Author: Albert Dang
  //Mail: dangqalbert@csu.fullerton.edu

//Program information:
  //Program name: Ricochete Ball, 1.0
  //Programming language: Java
  //Files: Main.java, Frame.java, Ricochete.java, Algorithm.java, Run.sh
  //Date project began: 2021-March-15.
  //Date of last update: 2021-March-27.
  //Status: Finished; testing completed.
  //Purpose: This program demonstrates the design of a simple UI (user interface) where the implemented functions process user
  //inputs for directions and computes a unique path for the moving object. The functions defaults to 0.0 for any textfield if the field is left empty. 
  //The variables including "spacer" are used to organize the button panel so that buttons and labels are not so close together as they would 
  //normally be in a GridLayout. 
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
  //File name: Frame.java
  //Compile : javac Frame.java

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.text.DecimalFormat;
import java.awt.Dimension;

public class Frame extends JFrame {
    private final int UI_width = 1900;
    private final int UI_height = 1000;
    
    private JPanel titlePanel;
    private final int titlepanel_width = UI_width;
    private final int titlepanel_height = 100;
    private JLabel titleLabel;

    private JPanel buttonPanel;
    private final int buttonpanel_width = UI_width;
    private final int buttonpanel_height = 200;
    private JButton clearButton;
    private JButton startButton;
    private JButton quitButton;
    private JTextField refreshRate;
    private String refreshString;
    private Double refreshDouble;
    private JTextField speedRate;
    private String speedString;
    private Double speedDouble;
    private JTextField direction;
    private String directionString;
    private Double directionDouble;
    private JLabel refreshLabel;
    private JLabel speedLabel;
    private JLabel directionLabel;
    private JLabel locationLabel;
    private double u, v;
    private boolean active = false;
    private boolean firstinput = true;
    private JLabel xLabel;
    private JLabel yLabel;
    private JTextField locationX;
    private JTextField locationY;
    private DecimalFormat hundreths;

    private JLabel spacer;
    private JLabel spacer1;
    private JLabel spacer2;
    private JLabel spacer3;
    private JLabel spacer4;
    private JLabel spacer5;
    private JLabel spacer6;
    private JLabel spacer7;
    private JLabel spacer8;
    private JLabel spacer9;
    private JLabel spacer10;
    private JLabel spacer11;
    private JLabel spacer12;

    GridBagConstraints gbc; 

    private Ricochete ricochetePanel;
    private final int ricochete_width = 1900;
    private final int ricochete_height = 700;

    private ButtonhandlerClass bh;

    private final double millisecondpersecond = 1000.0;
    private final double motion_clock_rate = 120.0;
    private double refreshClockRateDouble;
    private int refreshClockInterval;
    private int motionClockInterval;
    private double deltaX;
    private double deltaY;
    private Clockhandlerclass ch;
    private Timer refreshClock;
    private Timer motionClock;  
    
    private double x1 = 950.0;
    private double y1 = 350.0;
    private double ball_speed_per_tic;

public Frame() {
    super("Assignment 3");
    this.setTitle("Ricochete Ball");
    this.setLayout(new BorderLayout());
    this.setSize(UI_width,UI_height);

    setLocationRelativeTo(null);

    titlePanel = new JPanel();
    titleLabel = new JLabel("Ricochete Ball by Albert Dang");
    titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
    titleLabel.setVerticalAlignment(JLabel.CENTER);
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    titleLabel.setForeground(Color.BLACK);
    titlePanel.setBackground(Color.GRAY);
    titlePanel.add(titleLabel);
    titlePanel.setPreferredSize(new Dimension(1900,50));
    add(titlePanel, BorderLayout.NORTH);

    ricochetePanel = new Ricochete();
    ricochetePanel.setBackground(new Color(0,0,0));
    ricochetePanel.setPreferredSize(new Dimension(1900,700));
    this.add(ricochetePanel, BorderLayout.CENTER);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridBagLayout());
    buttonPanel.setBackground(new Color(204,204,204));
    buttonPanel.setPreferredSize(new Dimension(1900, 200));
    this.add(buttonPanel, BorderLayout.SOUTH);

    gbc = new GridBagConstraints();

    clearButton = new JButton("Clear");
    gbc.gridx = 0;
    gbc.gridy = 0;
    buttonPanel.add(clearButton, gbc);

    spacer = new JLabel("      "); //Add space between two buttons
    gbc.gridx = 1; //X coordianate of grid
    gbc.gridy = 0;//Y coordinate of grid
    buttonPanel.add(spacer, gbc);

    startButton = new JButton ("Start");
    gbc.gridx = 2;
    gbc.gridy = 0;
    buttonPanel.add(startButton, gbc);

    spacer1 = new JLabel("      ");
    gbc.gridx = 3;
    gbc.gridy = 0;
    buttonPanel.add(spacer1, gbc);

    quitButton = new JButton("Quit");
    gbc.gridx = 4;
    gbc.gridy = 0;
    buttonPanel.add(quitButton, gbc);

    spacer2 = new JLabel("      ");
    gbc.gridx = 5;
    gbc.gridy = 0;
    buttonPanel.add(spacer2, gbc);

    locationLabel = new JLabel("Ball Location:  ");
    gbc.gridx = 7;
    gbc.gridy = 0;
    buttonPanel.add(locationLabel, gbc);

    //Add space to whole second row of button panel
    spacer3 = new JLabel("      ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    buttonPanel.add(spacer3, gbc);
    spacer4 = new JLabel("      ");
    gbc.gridx = 1;
    gbc.gridy = 1;
    buttonPanel.add(spacer4, gbc);
    spacer5 = new JLabel("      ");
    gbc.gridx = 2;
    gbc.gridy = 1;
    buttonPanel.add(spacer5, gbc);
    spacer6 = new JLabel("      ");
    gbc.gridx = 3;
    gbc.gridy = 1;
    buttonPanel.add(spacer6, gbc);
    spacer7 = new JLabel("      ");
    gbc.gridx = 4;
    gbc.gridy = 1;
    buttonPanel.add(spacer7, gbc);
    spacer8 = new JLabel("      ");
    gbc.gridx = 5;
    gbc.gridy = 1;
    buttonPanel.add(spacer8, gbc);
    spacer9 = new JLabel("      ");
    gbc.gridx = 6;
    gbc.gridy = 1;
    buttonPanel.add(spacer9, gbc);

    refreshLabel = new JLabel("Refresh Rate (Hz)");
    gbc.gridx = 0;
    gbc.gridy = 2;
    buttonPanel.add(refreshLabel, gbc);

    spacer10 = new JLabel("      ");
    gbc.gridx = 1;
    gbc.gridy = 2;
    buttonPanel.add(spacer10, gbc);

    speedLabel = new JLabel("Speed (pix/sec)");
    gbc.gridx = 2;
    gbc.gridy = 2;
    buttonPanel.add(speedLabel, gbc);

    spacer11 = new JLabel("      ");
    gbc.gridx = 3;
    gbc.gridy = 2;
    buttonPanel.add(spacer11, gbc);

    directionLabel = new JLabel("Direction");
    gbc.gridx = 4;
    gbc.gridy = 2;
    buttonPanel.add(directionLabel, gbc);

    spacer12 = new JLabel("   ");
    gbc.gridx = 5;
    gbc.gridy = 2;
    buttonPanel.add(spacer12, gbc);

    
    xLabel = new JLabel("X = ");
    locationX = new JTextField(10);
    locationX.setBackground(Color.WHITE);
    gbc.gridx = 6;
    gbc.gridy = 2;
    buttonPanel.add(xLabel,gbc);
    gbc.gridx = 7;
    gbc.gridy = 2;
    buttonPanel.add(locationX,gbc);


    refreshRate = new JTextField(5);
    gbc.gridx = 0;
    gbc.gridy = 4;
    buttonPanel.add(refreshRate, gbc);

    speedRate = new JTextField(5);
    gbc.gridx = 2;
    gbc.gridy = 4;
    buttonPanel.add(speedRate, gbc);

    direction = new JTextField(5);
    gbc.gridx = 4;
    gbc.gridy = 4;
    buttonPanel.add(direction, gbc);

    yLabel = new JLabel("Y = ");
    locationY = new JTextField(10);
    locationY.setBackground(Color.WHITE);
    gbc.gridx = 6;
    gbc.gridy = 4;
    buttonPanel.add(yLabel,gbc);
    gbc.gridx = 7;
    gbc.gridy = 4;
    buttonPanel.add(locationY,gbc);
    hundreths = new DecimalFormat("00.00");

    bh = new ButtonhandlerClass();
    clearButton.addActionListener(bh);
    startButton.addActionListener(bh);
    quitButton.addActionListener(bh);

    ch = new Clockhandlerclass();

    motionClockInterval = (int)Math.round(millisecondpersecond/motion_clock_rate);
    motionClock = new Timer(motionClockInterval,ch);

    setVisible(true);
}//End of Frame constructor

    private class ButtonhandlerClass implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Algorithm alg = new Algorithm();
            if (e.getSource() == clearButton) { 
                System.out.println("The Clear button was clicked");
                refreshRate.setText("");
                speedRate.setText("");
                direction.setText("");
                startButton.setText("Start");
                //Clear button resets all textfields and resets start button
                x1 = 950.0;
                y1 = 350.0;
                deltaX = 0;
                deltaY = 0;
                ricochetePanel.initializeObjectsInPanel(x1, y1, deltaX,deltaY);
                u = ricochetePanel.getxcenter_of_ball();
                v = ricochetePanel.getycenter_of_ball();
                locationX.setText(hundreths.format(u));
                locationY.setText(hundreths.format(v));
                ricochetePanel.repaint();
                firstinput = true;
            }

            else if (e.getSource() == startButton) {
                if(firstinput) {
                    System.out.println("The Start button was clicked");
                    //Inputs and intializes all values 
                    //This branch prevents user from reinitializing after pressing start once
                    refreshString = refreshRate.getText();
                    if(refreshString.length() > 0) { refreshClockRateDouble = Double.parseDouble(refreshRate.getText()); }
                    else { refreshDouble = 0.0; }

                    speedString = speedRate.getText();
                    if(speedString.length() > 0) { speedDouble = Double.parseDouble(speedRate.getText()); }
                    else { speedDouble = 0.0; }

                    directionString = direction.getText();
                    if(directionString.length() > 0) { directionDouble = Double.parseDouble(direction.getText()); }
                    else { directionDouble = 0.0; }
                    
                    refreshClockInterval = (int)Math.round(millisecondpersecond/refreshClockRateDouble);
                    refreshClock = new Timer(refreshClockInterval,ch);

                    ball_speed_per_tic = speedDouble/motion_clock_rate;
                    deltaX = alg.computeDeltaX(ball_speed_per_tic, directionDouble);
                    deltaY = alg.computeDeltaY(ball_speed_per_tic, directionDouble);
                    ricochetePanel.initializeObjectsInPanel(x1, y1, deltaX,deltaY);
                }
                
                if(active) {
                    refreshClock.stop();
                    motionClock.stop();
                    startButton.setText("Resume");
                    active = false;
                    firstinput = false;
                }
                else {
                    refreshClock.start();
                    motionClock.start();
                    startButton.setText("Pause");
                    active = true;
                    firstinput = false;
                    
                }
            }

            else if(e.getSource() == quitButton) {
                System.exit(0);
            }

            else { System.out.println("Unkown error has occurred."); }
        }
    }//End of buttonhandler

    private class Clockhandlerclass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean animation_continues = false;
            if(e.getSource() == refreshClock) {
                ricochetePanel.repaint();
            }
            else if(e.getSource() == motionClock) {
                animation_continues = ricochetePanel.moveball();
                u = ricochetePanel.getxcenter_of_ball();
                v = ricochetePanel.getycenter_of_ball();
                locationX.setText(hundreths.format(u));
                locationY.setText(hundreths.format(v));
                if(!animation_continues) {
                    motionClock.stop();
                    refreshClock.stop();
                }
            } 
        } 
    }//End of clockhandler
}

