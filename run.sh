#!/bin/bash

#Program name: Ricochete Ball
#Author: Albert Dang
#Email: dangqalbert@csu.fullerton.edu
#File name:  run.sh

echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Algorithm.java
javac Algorithm.java

echo Compile Ricochete.java
javac Ricochete.java

echo Compile Frame.java
javac Frame.java

echo Execute the Ricochete Ball program
java Main.java

echo End of execution. Have a nice day.