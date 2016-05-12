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

import net.fustinoni.raspberryPi.robot.listener.UltraSoundSensorListener;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.util.NativeLibraryLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import net.fustinoni.raspberryPi.robot.component.RobotGPIO;
import net.fustinoni.raspberryPi.robot.sensor.UltraSoundSensor;

/**
 * "C:\Program Files\Java\jdk1.8.0_45\bin\javah" -o net_fustinoni_raspberryPi_pi2Go_sensor_Hcsr04.h -classpath build\classes net.fustinoni.raspberryPi.pi2Go.sensor.Hcsr04
 * @author efustinoni
 */
public class Hcsr04 extends TimerTask  implements UltraSoundSensor {
    
    static {
        //System.load("/home/pi/.netbeans/remote/192.168.1.186/efustinoni7-Windows-x86_64/C/Users/efustinoni/Dropbox/raspberryPi/robot/pi2goLiteJava/HCSR04/dist/hcsr04.so");

        NativeLibraryLoader.load("hcsr04.so");
       }
    
    final int sensorPin;
    private ArrayList<UltraSoundSensorListener> listeners;     
    private Timer timer;
    
    public Hcsr04 (final RobotGPIO pi2goGPIO, final Pin pin){
        sensorPin = pin.getAddress();
        pi2goGPIO.export(PinMode.DIGITAL_INPUT,pi2goGPIO.provisionDigitalInputPin(pin));
//        pi2goGPIO.setShutdownOptions(Boolean.TRUE, pi2goGPIO.provisionDigitalInputPin(pin));
    }

    
    @Override
    public long getDistance(){
        return getDistanceNative(sensorPin);
    }
    
    private native long getDistanceNative(int pin);

    @Override
    public void run() {
        
        long distance = getDistanceNative(sensorPin);

        listeners.stream().forEach((listener) -> {
            listener.tick(distance);
        });
    }

    private List <UltraSoundSensorListener> getListener(){
        if (listeners == null) listeners = new ArrayList<>();
        return listeners;
    }
    
    @Override
    public void addListener (final UltraSoundSensorListener listener){
        getListener().add(listener);
    }
    
    @Override
    public void removeListener (final UltraSoundSensorListener listener){
        getListener().remove(listener);
    }
    
    /**
     *  
     * @param intervallSeconds
     **/
    @Override
    public void startSensor(int intervallSeconds){
    
        if (this.timer == null) this.timer = new Timer();
        
        timer.scheduleAtFixedRate(this, 0, intervallSeconds * 1000);
    }
    
    @Override
    public void stopSensor(){
        if (this.timer != null){
            this.timer.cancel();
            this.timer.purge();
        }
    }
}
