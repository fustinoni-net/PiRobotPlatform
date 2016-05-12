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

import net.fustinoni.raspberryPi.robot.component.LeftRightMotors;
import static net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl.getPi2GoLite;
import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.CommandLine.CommandLineMotorDriver;
import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.CommandLine.CommandLineMotorDriverImpl;
import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.MotorsDriverImpl;

/**
 *
 * @author efustinoni
 */
public class CommandLineMotorDriverExample {

    public static void main(String[] args) throws InterruptedException {
        
        LeftRightMotors pi2go = getPi2GoLite();
        CommandLineMotorDriver pi = new CommandLineMotorDriverImpl(new MotorsDriverImpl(pi2go));

        pi.moveForward(20);
        Thread.sleep( 2000 );
        
        pi.moveForward(60);
        Thread.sleep( 2000 );

        pi.moveLeft(60, 80);
        Thread.sleep( 4000 );
        

        pi.moveRight(60, 20);
        Thread.sleep( 4000 );
       
        pi.moveBackward(60);
        Thread.sleep( 4000 );
        
        
        pi.stopMotors();
        
        System.exit(0);
        
    }
    
}
