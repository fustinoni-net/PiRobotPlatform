/**
 * 
 * **********************************************************************
 * This file is part of the PI2GO java library project. 
 *
 * More information about this project can be found here:  
 *   http://robots.fustinoni.net
 * **********************************************************************
 * 
 * Copyright (C) 2015 Enrico Fustinoni
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * 
 **/

package examples;

import static net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl.getPi2GoLite;
import net.fustinoni.raspberryPi.robot.device.Servo;
import net.fustinoni.raspberryPi.robot.component.PanTiltServos;

/**
 * https://unpocodejava.wordpress.com/2013/08/15/control-de-motores-con-java-pi4j-en-raspberry-pi/
 * 
 */
public class ServosExample {
    
    public static void main( String[] args ) throws InterruptedException {

        PanTiltServos pi2go = getPi2GoLite();
        
        Servo tiltServo = pi2go.getTiltServo();
        Servo panServo = pi2go.getPanServo();
        
        tiltServo.setDegree(0);
        panServo.setDegree(0);
        Thread.sleep( 2000 );
        

        tiltServo.setDegree(-90);
        panServo.setDegree(-90);
        Thread.sleep( 2000 );

        
        tiltServo.setDegree(70);
        panServo.setDegree(70);
        Thread.sleep( 2000 );

        tiltServo.setDegree(0);
        panServo.setDegree(0);
        Thread.sleep( 2000 );
        
        System.exit(0);
    }
}