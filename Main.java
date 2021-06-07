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
  //inputs for directions and computes a unique path for the moving object.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
  //File name: Main.java
  //Compile : javac Main.java
  
import javax.swing.JFrame;
public class Main {
    
    public static void main(String[] args) {
        Frame myFrame = new Frame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1900,1000);
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }
}