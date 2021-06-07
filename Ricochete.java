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
  //Purpose: This program initializes the ball, computes the rate of movement, checks for ball collisions, and retrieves the coordinate locations.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
  //File name: Ricochete.java
  //Compile : javac Ricochete.java

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class Ricochete extends JPanel {
  private int widthofgraphicalpanel = 1900;
  private int heightofgraphicalpanel = 900;
  private final double ballradius = 14.0;
  private final double balldiameter = 2.0*ballradius;
  private double ball_center_x;
  private double ball_center_y;
  private double ball_upper_corner_x;
  private double ball_upper_corner_y;
  private int ball_upper_corner_integer_x;
  private int ball_upper_corner_integer_y;
  private double a1,b1;
  private double δx;
  private double δy;
  private boolean successfulmove = true;
  private boolean showline = false;
  private double temporary; 

  public Ricochete() { setVisible(true); } //End of constructor

  public void paintComponent(Graphics graphicarea) {
    super.paintComponent(graphicarea);
    setBackground(Color.BLACK);
    if(showline) {
      graphicarea.setColor(Color.WHITE);   
      graphicarea.fillOval(ball_upper_corner_integer_x,ball_upper_corner_integer_y,(int)Math.round(balldiameter),(int)Math.round(balldiameter));
    }        
  } //End of method paintComponent

  public void initializeObjectsInPanel(double startx, double starty, double Δx, double Δy) {
    a1 = startx;
    b1 = starty;
    δx = Δx;
    δy = Δy;
    showline = true;
    ball_center_x = a1; 
    ball_center_y = b1;
    ball_upper_corner_x = ball_center_x - ballradius;   
    ball_upper_corner_y = ball_center_y - ballradius;   
    ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x); 
    ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);  
  } //End of initializeobjectsinpanel
     

  public boolean moveball() {   
    successfulmove = true;
    ball_center_x += δx;
    ball_center_y += δy;

    if(ball_center_y - ballradius <= 0.0) { δy = -δy; } //Checks for collision with north wall 

    if(ball_center_y + ballradius >= 714) { δy = -δy; } //Checks for collision with south wall 

    if(ball_center_x + ballradius >= 1886) { δx = -δx; } //Checks for collision with east wall

    if(ball_center_x - ballradius <= 0.0) { δx = -δx; } //Checks for collision with west wall 
       
    ball_upper_corner_x = ball_center_x - ballradius; 
    ball_upper_corner_y = ball_center_y - ballradius;
    ball_upper_corner_integer_x = (int)Math.round(ball_upper_corner_x);
    ball_upper_corner_integer_y = (int)Math.round(ball_upper_corner_y);

    return successfulmove;
  }//End of moveball

  public double getxcenter_of_ball() {
    temporary = ball_center_x;
    return temporary;
  }

  public double getycenter_of_ball() {
    temporary = ball_center_y;
    return temporary;
  }//End of method getycenter_of_ball
}

