# PiRobotPlatform

Java API to control generic robots based on the Raspberry Pi (https://www.raspberrypi.org/).

The aim of this library is purely educational. Don't use it for any other purpose.

This library is based on:
* Pi4J library (http://pi4j.com/)
* Wiring Pi library ( http://wiringpi.com/ )
* ServoBlaster driver (https://github.com/richardghirst/PiBits/tree/master/ServoBlaster )

The projects are managed with Maven (https://maven.apache.org/), so it's possible to open them in many different IDEs and build them via the Maven command line program.

## Please be careful. 
Running the library on the wrong hardware can brake your pi and your robot. Carefully check the sources and read the instruction.
Running the examples will make your robot move with all the possible consequences.

####  ***** Use at your own risk. *****

#### All the trademarks are properties of theirs owners.


## Prerequisites

### Robots
To use this library you mast have one of the supported robots based on the Raspberry Pi board or extend the library to support your own one.
Supported robot:
* Pi2Go-Lite (http://www.pi2go.co.uk/) with a Raspberry Pi B+ (Should work with every Raspberry Pi models)
* CamJam EduKit 3 – Robotics (http://camjam.me/?page_id=1035)

### Operating system
The library was built and tested using Raspbian Jessie version May 2016, but should also works in previous version.

### Java
The library is based on Java 8. Previous versions are not supported.
To test the java version you can use the command:

    java -version

that will prompt something like:

    java version "1.8.0_65"
    Java(TM) SE Runtime Environment (build 1.8.0_65-b17)
    Java HotSpot(TM) Client VM (build 25.65-b01, mixed mode)

version 1.8.xxx or greater is ok.

If your Java version is a previous one, or you get 

    -bash: java: command not found

you can install Java 8 with this procedure:

First upgrade your OS:

    sudo apt-get update && sudo apt-get upgrade

Then install JDK8 for ARM:

    sudo apt-get install oracle-java8-jdk



### Maven
To build the library you need Maven. To test if is installed on your pi, execute the command:

    mvn -version

if you get a response like the following one Maven is installed.

    Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
    Maven home: /opt/apache-maven-3.3.9
    Java version: 1.8.0_65, vendor: Oracle Corporation
    Java home: /usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/jre
    Default locale: en_GB, platform encoding: UTF-8
    OS name: "linux", version: "4.4.9+", arch: "arm", family: "unix"

Otherwise if you get:

    -bash: mvn: command not found

you have to install it.

The most convenient way to do it on your Pi is to follow the guide at https://www.xianic.net/post/installing-maven-on-the-raspberry-pi/

I summerized the procedure for you:

    wget http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
    cd /opt
    sudo tar -xzvf /home/pi/apache-maven-3.3.9-bin.tar.gz
    cd /etc/profile.d/
    sudo nano maven.sh
    
    export M2_HOME=/opt/apache-maven-3.3.9
    export PATH=$PATH:$M2_HOME/bin

then you have to close and reopen the shell, or logout and login back.

Check the installation with:
    mvn -version

you will get:
    Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T17:41:47+01:00)
    Maven home: /opt/apache-maven-3.3.9
    Java version: 1.8.0, vendor: Oracle Corporation
    Java home: /usr/lib/jvm/jdk-8-oracle-arm-vfp-hflt/jre
    Default locale: en_GB, platform encoding: UTF-8
    OS name: "linux", version: "4.1.19+", arch: "arm", family: "unix"


For others platform refer to the Maven project web site.


### Git
To install git on your Pi:
    sudo apt-get update && sudo apt-get upgrade
    sudo apt-get install git

On other systems refer to https://github.com or https://git-scm.com/ 



## Installation guide
To install the library on your Pi, and get something to run and test, follows this step:

1. execute:

    git clone https://github.com/fustinoni-net/PiRobotPlatform.git

This command will create the directory  PiRobotPlatform and download inside it the main project and all sub-projects.

2. move into the directory: 

    cd PiRobotPlatform

3. edit the pom.xml file (use your favorit editor or nano): 

    nano pom.xml 

4. In the pom.xml customize the following part with your own one. Also if you run the build locally you have to set the right parameter here or the project will not build.

        <!-- DEFAULT RASPBERRY PI PROPERTIES -->
        <pi.host>raspi-pi2go.homenet.telecomitalia.it</pi.host>
        <pi.port>22</pi.port>
        <pi.user>pi</pi.user>
        <pi.password>raspberry</pi.password>

5. save and exit from the editor :-)

6. execute Maven:

    mvn install

Running Maven for the first time will take a while. Maven will download, in a .m2 directory in your user home, all the necessary dependencies for itself and for the project too. Don't be scare, let it run and build all the projects.

After some minutes the process will stop and you will get something like this (plus many more line before):

```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] PiRobotPlatform .................................... SUCCESS [  7.847 s]
[INFO] ExecuteFromJar ..................................... SUCCESS [ 21.767 s]
[INFO] PiRobot ............................................ SUCCESS [ 17.922 s]
[INFO] HCSR04 ............................................. SUCCESS [ 43.005 s]
[INFO] Pi2Go .............................................. SUCCESS [ 56.633 s]
[INFO] PiRobotRMI ......................................... SUCCESS [  1.579 s]
[INFO] Pi2GoRMI ........................................... SUCCESS [ 18.336 s]
[INFO] MoteJPiRobot ....................................... SUCCESS [ 25.597 s]
[INFO] PiRobotImplementation .............................. SUCCESS [  0.897 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 03:18 min
[INFO] Finished at: 2016-05-26T21:32:36+00:00
[INFO] Final Memory: 12M/33M
[INFO] ------------------------------------------------------------------------
```

If all success you are done. Otherwise sorry :-( 


## OK, and now????
The library is composed by some different subproject that logically, at least for me, divide the sources for scopes. 

* PiRobotPlatform: the aggregator for all other modules.

* ExecuteFromJar: utility to run the ServoBlaster user space daemon from the jar file.
* PiRobot: all the interfaces use to describe the robots components.
* HCSR04: build the native library (Jni) for driving the HCSR04 ultrasound sensor.
* Pi2Go: class implementation of the Pi2Go-Lite robot, plus some examples on how to use the library.
* PiRobotRMI: interfaces and local object to use the library and command the robot remotely via RMI (https://docs.oracle.com/javase/tutorial/rmi/)
* Pi2GoRMI: the Pi2Go-Lite implementation of the PiRobotRMI library plus an example on how to use it.
* MoteJPiRobot: an example of how to use the library to command the Pi2Go-Lite, but also other robots, using a Nintendo Wii Remote and Nunchuk (https://en.wikipedia.org/wiki/Wii_Remote). Enjoy :-)
* PiRobotImplementation: future use. Committed by mistake :-)

The projects that have an executable part (examples or main class) are:
* Pi2Go
* Pi2GoRMI
* MoteJPiRobot
Refer to the projects readme for more specific information and remember: running the examples will make your robot move with all the possible consequences.

To run the projects from their jar files, with all the dependencies inside, go to the subproject dir and execute the commnad: mvn antrun:run
That Maven task will run on the Pi itself but also from a remote computer. In this case the program will be executed on the Pi via SSH.
After have run the project the first time from Maven, you will find a new directory on the Pi named "artifacts" containing the executable jar file.
You can run those jar file on the Pi wiht the command: sudo java -jar file.jar
In the pom.xml file of each subprograms,  you will find the default executable class declared like <pi.main.class>examples.LedsExample</pi.main.class> (from Pi2Go project).
You can change it and and then rebuild the subproject with mvn install and then execute it with mvn antrun:run or just execute the class from the jars in the "artifacts" directory with the command
sudo java -cp file.jar class


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
