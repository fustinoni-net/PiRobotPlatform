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

package net.fustinoni.raspberryPi.pi2Go.device;

import net.fustinoni.raspberryPi.robot.device.Servo;
import com.pi4j.component.servo.ServoDriver;
import com.pi4j.component.servo.ServoProvider;
import com.pi4j.component.servo.impl.RPIServoBlasterProvider;
import com.pi4j.io.gpio.Pin;
import java.io.IOException;
import net.fustinoni.raspberryPi.robot.component.RobotGPIO;

/**
 *
 * @author efustinoni
 */
public class ServoImpl implements Servo {

    private final ServoProvider servoProvider;
    private final ServoDriver servo;
    
    public ServoImpl(final RobotGPIO pi2goGPIO, final Pin servoPin) throws IOException {

        servoProvider = new RPIServoBlasterProvider();
        servo = servoProvider.getServoDriver(servoPin);
        
    }
    
    @Override
    public void setPulseWidth(int width){
        servo.setServoPulseWidth(width);
    }
    
    @Override
    public void setDegree(int degrees){
        servo.setServoPulseWidth(50+ ((90 - degrees) * 200 / 180));
    }
}
