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

package net.fustinoni.pi.hcsr04;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import net.fustinoni.pi.robot.component.RobotGPIO;

/**
 * "C:\Program Files\Java\jdk1.8.0_45\bin\javah" -o net_fustinoni_pi_hcsr04_Hcsr04SinglePin.h -classpath target\classes;..\PiRobot\target\classes net.fustinoni.pi.hcsr04.Hcsr04SinglePin
 * @author efustinoni
 */
public class Hcsr04SinglePin extends Hcsr04 {
    
    final int sensorPin;
    
    public Hcsr04SinglePin (final RobotGPIO pi2goGPIO, final Pin pin){
        sensorPin = pin.getAddress();
        pi2goGPIO.export(PinMode.DIGITAL_INPUT,pi2goGPIO.provisionDigitalInputPin(pin));
//        pi2goGPIO.setShutdownOptions(Boolean.TRUE, pi2goGPIO.provisionDigitalInputPin(pin));
    }

    @Override
    public long getDistance(){
        return getDistanceNative(sensorPin);
    }
}