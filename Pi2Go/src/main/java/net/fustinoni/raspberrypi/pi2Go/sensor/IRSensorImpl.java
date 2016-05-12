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

package net.fustinoni.raspberryPi.pi2Go.sensor;

import net.fustinoni.raspberryPi.robot.listener.IRSensorListener;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.util.ArrayList;
import java.util.List;
import net.fustinoni.raspberryPi.robot.component.RobotGPIO;
import net.fustinoni.raspberryPi.robot.sensor.IRSensor;

/**
 *
 * @author efustinoni
 */
public class IRSensorImpl implements IRSensor {
        
    private ArrayList<IRSensorListener> listeners;
    final GpioPinDigitalInput iRSensor;
    
    public IRSensorImpl (final RobotGPIO pi2goGPIO, final Pin IRPin){

        iRSensor = pi2goGPIO.provisionDigitalInputPin(IRPin, PinPullResistance.PULL_DOWN);
        iRSensor.setShutdownOptions(Boolean.TRUE);
        
        // create and register gpio pin listeners
        iRSensor.addListener((GpioPinListenerDigital) (GpioPinDigitalStateChangeEvent event) -> {
            listeners.stream().forEach((listener) -> {
                listener.triggered(event.getState().isLow());
            });
        });
    }



    private List <IRSensorListener> getListener(){
        if (listeners == null) listeners = new ArrayList<>();
        return listeners;
    }
    
    @Override
    public boolean isTriggered() {
        return iRSensor.isHigh();
    }

    @Override
    public void addListener(final IRSensorListener listener) {
        getListener().add(listener);
    }

    @Override
    public void removeListener(final IRSensorListener listener) {
        getListener().remove(listener);
    }
    
}
