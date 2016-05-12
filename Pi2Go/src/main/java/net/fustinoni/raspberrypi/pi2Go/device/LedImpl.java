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

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import net.fustinoni.raspberryPi.robot.component.RobotGPIO;

/**
 *
 * @author efustinoni
 */
public class LedImpl implements net.fustinoni.raspberryPi.robot.device.Led {

    final Pin ledPin;
    final GpioPinDigitalOutput led;
    final PinState on;
    final PinState off;
    
    //boolean isPinPWM;
    
    public LedImpl(final RobotGPIO pi2goGPIO, final Pin ledPin, boolean onIsHigh) {

        if (onIsHigh){
            this.on = PinState.HIGH;
            this.off = PinState.LOW;
        }else{
            this.on = PinState.LOW;
            this.off = PinState.HIGH;
        }
        
        this.ledPin = ledPin;
        this.led = pi2goGPIO.provisionDigitalOutputPin(ledPin, off);
        this.led.setShutdownOptions(true, off);
    }
    
    @Override
    public void turnOn(){
        led.setState(on);
    }

    @Override
    public void turnOff(){
        led.setState(off);
    }

    @Override
    public void toggle(){
        led.toggle();
    }

    @Override
    public void pulse(long duration){
        led.pulse(duration, on, false);
    }
    
    

}
