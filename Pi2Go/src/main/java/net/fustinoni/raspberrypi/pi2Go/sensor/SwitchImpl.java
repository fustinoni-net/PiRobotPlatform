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

import net.fustinoni.raspberryPi.robot.listener.SwitchListener;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.util.ArrayList;
import java.util.List;
import net.fustinoni.raspberryPi.robot.component.RobotGPIO;
import net.fustinoni.raspberryPi.robot.sensor.Switch;

/**
 *
 * @author efustinoni
 */
public class SwitchImpl implements Switch {

    private ArrayList<SwitchListener> listeners;
    private final GpioPinDigitalInput button;
    private long lastLow;
    private long lastHigh;
    

    public SwitchImpl (final RobotGPIO pi2goGPIO, Pin switchPin){

        button = pi2goGPIO.provisionDigitalInputPin(switchPin, PinPullResistance.PULL_UP);
        //button.setShutdownOptions(Boolean.TRUE, PinState.LOW, PinPullResistance.OFF);
//com.pi4j.io.gpio.exception.InvalidPinModeException: Invalid pin mode on pin [GPIO 14]; cannot setState() when pin mode is [input]
//at com.pi4j.io.gpio.GpioProviderBase.setState(GpioProviderBase.java:180)
//at com.pi4j.io.gpio.RaspiGpioProvider.setState(RaspiGpioProvider.java:150)
//	at com.pi4j.io.gpio.impl.GpioPinImpl.setState(GpioPinImpl.java:325)
//	at com.pi4j.io.gpio.impl.GpioControllerImpl.shutdown(GpioControllerImpl.java:939)
//	at com.pi4j.io.gpio.impl.GpioControllerImpl$ShutdownHook.run(GpioControllerImpl.java:888)

        // create and register gpio pin listeners
        button.addListener((GpioPinListenerDigital) (GpioPinDigitalStateChangeEvent event) -> {
            
            synchronized(this){
                if(event.getState().isLow()) lastLow = System.currentTimeMillis();
                if(event.getState().isHigh()) lastHigh = System.currentTimeMillis();
            }
            
            listeners.stream().forEach((listener) -> {
                listener.changedEvent(event.getState().isLow());
            });
        });
    }

    @Override
    public boolean isPushed(){
        return button.getState().isLow();
    }

    private List <SwitchListener> getListener(){
        if (listeners == null) listeners = new ArrayList<>();
        return listeners;
    }

    @Override
    public void addListener (final SwitchListener listener){
        getListener().add(listener);
    }

    @Override
    public void removeListener (final SwitchListener listener){
        getListener().remove(listener);
    }
    
    @Override
    public long getLastPressionMillisec(){
        if (lastLow == 0) return -1L;
        
        synchronized(this){
            return lastHigh - lastLow;
        }
    }
}
