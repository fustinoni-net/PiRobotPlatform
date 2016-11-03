# Pi2GoRMI

Java API to control a Pi2Go-Lite Robot Kit (http://pi2go.co.uk/) remotely via RMI.

## Build the project
Before building the subproject locally you have to build the parent one. Refer to the PiRobotPlatform readme file for instructions.
Then you can build and run the program with

     mvn install 

and 

    mvn antrun:run


Runnable examples in the examples package:
* RMIExample.java

You can run the example in the artifacts directory using the command:

    sudo java  -Djava.security.policy=/home/pi/artifacts/secure.policy -Djava.rmi.server.codebase=file://home/pi/artifacts/Pi2Go-0.0.1-SNAPSHOT-jar-with-dependencies.jar -jar /home/pi/artifacts/Pi2GoRMI-0.0.1-SNAPSHOT-jar-with-dependencies.jar


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


