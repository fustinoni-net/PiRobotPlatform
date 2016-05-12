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

package net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.CommandLine;

import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.MotorsDriver;

/**
 *
 * @author efustinoni
 */
public class CommandLineMotorDriverImpl implements CommandLineMotorDriver {

    final MotorsDriver motors;
    public CommandLineMotorDriverImpl(final MotorsDriver motors) {
        this.motors = motors;
    }


    @Override
    public void stopMotors() {
        motors.stopMotors();
    }

    @Override
    public void moveForward (int speed){
        motors.setMotorsSpeeds(speed, speed);
    }

    @Override
    public void moveBackward (int speed){
        motors.setMotorsSpeeds(-speed, -speed);
    }
    
    @Override
    public void spinRight (int speed){
        motors.setMotorsSpeeds(speed, -speed);
    }
    
    @Override
    public void spinLeft (int speed){
        motors.setMotorsSpeeds(-speed, speed);
    }
    
    @Override
    public void moveLeft (int speed, int angle){
        motors.setMotorsSpeeds(speed - speed * angle/100, speed);
    }

    @Override
    public void moveRight (int speed, int angle){
        motors.setMotorsSpeeds(speed, speed - speed * angle/100);
    }
    
}
