# MoteJPiRobot

Example of how to use the library to command the Pi2Go-Lite, but also other robots, using a Nintendo Wii Remote and Nunchuk (https://en.wikipedia.org/wiki/Wii_Remote).

## Build the project
Before building the subproject locally you have to build the parent one. Refer to the PiRobotPlatform readme file for instructions.
Then you can build and run the program with mvn install and mvn antrun:run

Runnable class in the package:
* net.fustinoni.pi.motejpirobot.WiimotePiRobot

You can run the program in the artifacts directory using the command:
sudo java  -cp /home/pi/artifacts/lib/motej-extras-0.9.jar:/home/pi/artifacts/lib/motej-library-0.9.jar:/home/pi/artifacts/lib/bluecove-2.1.1-SNAPSHOT.jar:/home/pi/artifacts/lib/bluecove-emu-2.1.1-SNAPSHOT.jar:/home/pi/artifacts/lib/bluecove-gpl-2.1.1-SNAPSHOT.jar:/home/pi/artifacts/MoteJPiRobot-0.0.1-SNAPSHOT-jar-with-dependencies.jar net.fustinoni.pi.motejpirobot.WiimotePiRobot

**** Running the program will make your robot move with all the possible consequences. ****

## Dependencies
This project have two dependencies:
* Bluecove library for Bluetooth JSR-82 implemetnation
    * https://code.google.com/archive/p/bluecove/  
    * http://lukealderton.com/blog/posts/2015/january/raspberry-pi-bluetooth-using-bluecove-on-raspbian.aspx
* MoteJ for the Wii Remote
    * http://motej.sourceforge.net/

The jar of both library are provided in the jarDependencies directory, because, unfortunately, they are not Maven project.
Let me know if you know a better way to manage this dependencies. I will integrate that way in the project or you just can do it by yourself. The good of the open source :-)

## Program use

### Controls:
    * Nunchuk joystick: move the robot in all direction.
    * Nunchuk buttons: turn the front and rear led on and off.
    * Remote arrows: move the cam servos
    * Remote A button: center the servos.
    * Remote Home make the remote rumble
    * Pi2Go-Lite switch alt the program and shutdown the Pi.

### Feedback:
    * When the left and right IR sensor catches an obstacle the remote rumble and the 1 and 4 player led  on the remote tourn on.
    * When the ultrasound get an obstacle closer then 2 cm the remote rumble and 2 player led tourn on.

### Pairing the Wii Remote.
Run the program. When both the front led and rear led turn on press the 1 and 2 remote button together.
After a while the remote and the robot will pair, the led will turn off and the player 3 led on the remote will turn on.

Note that the pairing procedure is critical so press the 2 button immediately after the led turn on otherwise the pairing process will fail.
Feel free to improve the procedure :-)



## Terms and conditions:

This software is provided as is. No responsibility of any kind is taken by the author.
Although the author has tried all the softwares on his computers he does not guarantee that they work on your environment and that they are safe for you to use.

*** Use at your own risk!! ***

All the trademarks are properties of their owner.
This author is not related in any way to any entity (corporation, person, brand, trademark, …) mentioned in this page.

## Many thanks for their work to:
All the people that developed the projects quoted in this page. I learned a lot from their work and also get many part and idea.
Expecially for the Maven pom files I have to thank the people of the Pi4J projects.
I hope I have respected all the licenses terms and conditions. Otherwise, let me know, and I will comply with their requirements.

