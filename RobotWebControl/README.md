# RobotWebControl

Example of how to use the PiRobotPlatform library to command a Pi2Go-Lite or a CamJam EduKit #3 robot using a web page on a pc, tablet, smartphone.
A web application is started using Spark micro framework (http://sparkjava.com/) so that is possible to command and receive the sensor information through a web page.
The client server communication is base on a websocket.

## Build the project
Before building the subproject locally you have to build the parent one. Refer to the PiRobotPlatform readme file for instructions.
Then you can build the program with mvn install.

Before building and running the program adjust the pom properties:

    <pi.main.class>net.fustinoni.pi.robotWebControl.Pi2GoLiteDriver</pi.main.class>

according to your robot. The committed one is: Pi2GoLiteDriver.

Use mvn clean package assembly:single to create a zip file with all the dependencies to run the program on the Raspberry Pi and mvn antrun:run to run it.

Runnable class in the package:
* net.fustinoni.pi.robotWebControl.CamJamEK3Driver
* net.fustinoni.pi.robotWebControl.Pi2GoLiteDriver
* net.fustinoni.pi.robotWebControl.RobotDriver (No robot just web interface)

You can run the program in the artifacts directory using the command:

`sudo java  -jar /home/pi/artifacts/RobotWebControl-0.0.1-SNAPSHOT/RobotWebControl-0.0.1-SNAPSHOT.jar`

to drive the robot set in the pom, or

`sudo java  -cp /home/pi/artifacts/RobotWebControl-0.0.1-SNAPSHOT/RobotWebControl-0.0.1-SNAPSHOT.jar net.fustinoni.pi.robotWebControl.Pi2GoLiteDriver`

to drive a Pi2Go-Lite or

`sudo java  -cp /home/pi/artifacts/RobotWebControl-0.0.1-SNAPSHOT/RobotWebControl-0.0.1-SNAPSHOT.jar net.fustinoni.pi.robotWebControl.CamJamEK3Driver`


to drive a CamJam EduKit #3 robot.


**** Running the program will make your robot move with all the possible consequences. ****

## Program use

To run the program insert in a browser the your Raspberry Pi address followed by :4567 for example:

    http://raspi-pi2go.homenet.telecomitalia.it:4567/

A web page will start.


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

