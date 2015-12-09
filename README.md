# The Abdominable SnowBot Waiter OpenGL2 Graphics
A Java JOGL OpenGL2 replayable animation which shows a robot holding some drinks moving around an
empty restuarant room with some tables in the room.

# See it in Action for Yourself !

#How to Run
The only dependency for the project is JOGL. The project depends on jogl-all.jar and gluegen-rt.jar.

The download can be found at http://jogamp.org/

This project required to be runnable without any build tools(unfortunately) on the command line: 
Linux(Personally ran on Ubuntu 15.04)
```
javac -cp .:./src/:./lib/gluegen-rt.jar:./lib/jogl-all.jar: main.MainFrame (build from root directory)
java -cp .:./Robot-Waiter-Graphics/:./lib/gluegen-rt.jar:./lib/jogl-all.jar: main.MainFrame (run from out/production)
```
Windows
```
javac -cp .;./src/;./lib/gluegen-rt.jar;./lib/jogl-all.jar; main.MainFrame (build from root directory)
java -cp .;./Robot-Waiter-Graphics/;./lib/gluegen-rt.jar;./lib/jogl-all.jar; main.MainFrame (run from out/production)
```
Make sure to replace jars with appropriate location...I suggest setting up the project in your 
favourite IDE(like IntelliJ or Eclipse and setting up the module dependencies from there).

## Current Features
### 1. 2 Snowbot Waiter which includes (robot.Robot and peripherals):
  -	Rigid lower body that reaches the floor textured(no legs).
  -	Head:
    *	2 Eyes (deliberately slightly differently sized)
    * Nose textures with carrot
    * TopHat with spotlight shining out of them in the direction it’s travelling (Controls to switch on/off)
  -	2 arms:
    *	Upper arm
    * Joint
    *	Lower arm
    *	Claw-like hands
  -	Carrying a Tray with 6 simple champagne flumes on it.

### 2. Room(room.Room and peripherals):
  - U,V texture coordinate on planes are done separately to the number of slices(for lighting smoothening)
  - Room objects are all stored in initialized display lists to speed up rendering.
  -	Floor with marble texture.
  -	Four walls with christmasy texture (since the project was done December 2015)
  -	Ceiling with wooden texture
  -	2 spotlights on the ceiling (Controls to switch on/off).
  -	3 wooden rounded tables.

### 3. Animations (robot.RobotAnimationManager):
  -	Move the robot from table to table using keyframes(found in the main.SceneManager) and quadratic interpolation between
  keyframes.
  -	When moving from table to table, the robot and items lean to compensate for the movements based on speed
  (distance travelled in previous frame) and direction of travel(previous frame's rotation - current one averaged to smooth).
  You will see a bit of a jig between keyframes occasionally due to the quadratic interpolations method of looking at 
  2 keyframes back and forward.
  -	Buttons on the interface to start/stop/reset the animation

### 4. Camera points towards center of room, user is able to use the mouse to rotate(hold mouse left click) 
and zoom(hold mouse right click) the camera around the scene.
