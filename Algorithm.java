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
  //Purpose: This program computes the direction of the ball.
  //Base test system: Linux system with Bash shell and openjdk-14-jdk

  //This module
  //File name: Algorithm.java
  //Compile : javac Algorithm.java

import java.lang.*;

public class Algorithm {
    public double computeDeltaX(double ballspeed, double direction) {
        
        direction = Math.toRadians(direction); //degree = d*(pi/180)
        direction = ballspeed * Math.cos(direction); //include speed with direction
        

        return direction;
    }

    public double computeDeltaY(double ballspeed, double direction) {
        
        direction = Math.toRadians(direction);
        direction = ballspeed * Math.sin(direction);

        return direction;
    }
}
