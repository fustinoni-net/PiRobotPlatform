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
To use this library you must have one of the supported robots based on the Raspberry Pi board or extend the library to support your own one.
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

you will have to install it.

The most convenient way to do it on your Pi is to follow the guide at https://www.xianic.net/post/installing-maven-on-the-raspberry-pi/

I summerized the procedure for you:

    wget http://www.mirrorservice.org/sites/ftp.apache.org/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
    cd /opt
    sudo tar -xzvf /home/pi/apache-maven-3.3.9-bin.tar.gz
    cd /etc/profile.d/
    sudo nano maven.sh
    
    export M2_HOME=/opt/apache-maven-3.3.9
    export PATH=$PATH:$M2_HOME/bin

then you have to close and reopen the shell, or logout and log back in.
 
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
To install Git on your Pi:

    sudo apt-get update && sudo apt-get upgrade
    sudo apt-get install git

On other systems refer to https://github.com or https://git-scm.com/ 



## Installation guide
To install the library on your Pi, and get something to run and test, follows this step:

* execute:

    git clone https://github.com/fustinoni-net/PiRobotPlatform.git

This command will create the directory  PiRobotPlatform and download inside it the main project and all sub-projects.

* move into the directory: 

    cd PiRobotPlatform

* edit the pom.xml file (use your favorite editor or nano): 

    nano pom.xml 

* In the pom.xml customize the following part with your own one. Also if you run the build locally you have to set the right parameter here or the project will not build.

        <!-- DEFAULT RASPBERRY PI PROPERTIES -->
        <pi.host>raspi-pi2go.homenet.telecomitalia.it</pi.host>
        <pi.port>22</pi.port>
        <pi.user>pi</pi.user>
        <pi.password>raspberry</pi.password>

* save and exit from the editor :-)

* execute Maven:

    mvn install

Running Maven for the first time will take a while. Maven will download, in a .m2 directory in your user home, all the necessary dependencies for itself and for the projects too. Don't be scared, let it run and build all the projects.

After some minutes the process will stop and you will get something like this (plus many more line before):

```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO]
[INFO] PiRobotPlatform .................................... SUCCESS [  6.929 s]
[INFO] ExecuteFromJar ..................................... SUCCESS [ 36.223 s]
[INFO] PiRobot ............................................ SUCCESS [ 31.716 s]
[INFO] HCSR04 ............................................. SUCCESS [01:57 min]
[INFO] PiRobotImpl ........................................ SUCCESS [ 33.358 s]
[INFO] Pi2Go .............................................. SUCCESS [ 40.817 s]
[INFO] PiRobotRMI ......................................... SUCCESS [  7.725 s]
[INFO] Pi2GoRMI ........................................... SUCCESS [ 22.335 s]
[INFO] MoteJPiRobot ....................................... SUCCESS [ 50.163 s]
[INFO] CamJamEK3 .......................................... SUCCESS [ 17.780 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 06:10 min
[INFO] Finished at: 2016-06-02T06:53:20+00:00
[INFO] Final Memory: 17M/46M
[INFO] ------------------------------------------------------------------------
```

If all success you are done. Otherwise sorry :-( 


## OK, and now????
The library is composed by some different subproject that logically, at least for me, divide the sources for scopes. 

* PiRobotPlatform: the aggregator for all other modules.
* ExecuteFromJar: utility to run a program from inside a jar file. Used to run the ServoBlaster.
* PiRobot: all the interfaces use to describe the robots components.
* PiRobotImplementation: the implementation of all common component
* HCSR04: build the native library (Jni) for driving the HCSR04 ultrasound sensor.
* Pi2Go: class implementation of the Pi2Go-Lite robot, plus some examples on how to use the library.
* PiRobotRMI: interfaces and local object to use the library and command the robot remotely via RMI (https://docs.oracle.com/javase/tutorial/rmi/)
* Pi2GoRMI: the Pi2Go-Lite implementation of the PiRobotRMI library plus an example on how to use it.
* CamJamEK3: class to command the CamJam EduKit #3 robot, plus examples
* MoteJPiRobot: an example of how to use the library to command the Pi2Go-Lite, but also other robots, using a Nintendo Wii Remote and Nunchuk (https://en.wikipedia.org/wiki/Wii_Remote). Enjoy :-)
* RobotWebControl: an example of how to use the PiRobotPlatform library to command a Pi2Go-Lite or a CamJam EduKit #3 robot using a web page on a pc, tablet, smartphone.


The projects that have an executable part (examples or main class) are:
* Pi2Go
* Pi2GoRMI
* CamJamEK3
* MoteJPiRobot
* RobotWebControl

Refer to the projects readme for more specific information and remember: running the examples will make your robot move with all the possible consequences.

To run the projects from their jar files, with all the dependencies inside, go to the subproject dir and execute the commnad: 

    mvn antrun:run

You can execute this Maven plug-in on the Pi itself, but also from a remote computer. In this case the project will be executed on the Pi via SSH.
After having run the project the first time from Maven, you will find a new directory on the Pi named "artifacts" containing the executable jar file.
You can run those jar file on the Pi with the command:
    
    sudo java -jar file.jar

In the pom.xml file of each subprograms,  you will find the default executable class declared like

    <pi.main.class>examples.LedsExample</pi.main.class> (from Pi2Go project).

You can change it and and then rebuild the subproject with
    
    mvn install 

and then execute it with 

    mvn antrun:run 

or just execute the class from the jars in the "artifacts" directory with the command

    sudo java -cp file.jar class

To debug the projects use the command

    mvn antrun:run -P debug-server

that will use the debug-server maven profile. Then connect the remote debug of your IDE via dt_socket to your Raspeberry Pi on the port 8765.


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
