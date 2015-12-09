# Robot-Waiter-Graphics
A Java OpenGL replayable animation which shows a robot holding some drinks moving around an empty restuarant room with some tables in the room.

## Requirements
### 1.	Create a Robot Waiter which includes:
  -	Rigid body that reaches the floor with wheels (no legs).
  -	Head:
    *	Eye(s) with spotlights shining out of them in the direction it’s travelling (should be able to turn the off and on)
  -	2 arms:
    *	Upper arm
    *	Lower arm
    *	Claw-like hands
  -	Carrying a Tray with some simple items on it.
  -	Texture map the parts

### 2.	RoomScene with themed texture mapping:
  -	Floor
  -	Four walls
  -	Ceiling
  -	2 Dimmable lights from interface.
  -	3 tables
### 3.	Animations:
  -	Move the robot from table to table in straight lines or curves…
  -	When moving from table to table, the robot and items should lean to compensate for the movements.
  -	Set down items onto table…
  -	Buttons to start/stop/reset the animation

### 4.	notmine.Camera should point towards center of room, user should be able to use the mouse to rotate the camera around the scene.

### 5.	Add the ability to switch to the view of the robot.

### 6.	Add multiple robots to the room.

Commands:

Run - 
linux: java -cp .:./Robot-Waiter-Graphics/:./lib/gluegen-rt.jar:./lib/jogl-all.jar: main.MainFrame
windows: java -cp .;./Robot-Waiter-Graphics/;./lib/gluegen-rt.jar;./lib/jogl-all.jar; main.MainFrame

Build - 
linux: javac -cp ./src:./lib/gluegen-rt.jar:./lib/jogl-all.jar: -d out/production/Robot-Waiter-Graphics/ src/main/MainFrame.java
windows: javac -cp ./src;./lib/gluegen-rt.jar;./lib/jogl-all.jar; -d out/production/Robot-Waiter-Graphics/ src/main/MainFrame.java
