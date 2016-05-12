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

import net.fustinoni.raspberryPi.pi2Go.Pi2GoLite;
import net.fustinoni.raspberryPi.robot.listener.IRSensorListener;
import net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl;
import static net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl.getPi2GoLite;
import net.fustinoni.raspberryPi.robot.device.Led;
import net.fustinoni.raspberryPi.robot.listener.SwitchListener;
import net.fustinoni.raspberryPi.robot.listener.UltraSoundSensorListener;

/**
 *
 * @author efustinoni
 */
public class AllExample {
    
    static boolean exit = false;
    
    public static void main (String... args) throws InterruptedException{
        
        
        
        Pi2GoLite pi2go = getPi2GoLite();

        pi2go.getLeftIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
            System.out.println(" --> LeftIRSensor is: ".concat(isFired ? "on" : "off" ));
        });

        pi2go.getRightIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
            System.out.println(" --> RightIRSensor is: ".concat(isFired ? "on" : "off" ));
        });

        pi2go.getLineLeftIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
            System.out.println(" --> LineLeftIRSensor is: ".concat(isFired ? "on" : "off" ));
        });
        
        pi2go.getLineRightIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
            System.out.println(" --> LineRightIRSensor is: ".concat(isFired ? "on" : "off" ));
        });

        pi2go.getGenericSwitch().addListener((SwitchListener) (boolean isPressed) ->{
            System.out.println(" --> Button is: ".concat(isPressed ? "pressed" : "release" ));
            exit = true;
        });
        
        pi2go.getUltraSoundSensor().addListener((UltraSoundSensorListener) (long distance) -> {
            System.out.println("--> Distance: " + distance);
        });
        
        pi2go.getUltraSoundSensor().startSensor(2);
        
        Led ledFront =  pi2go.getFrontLeds();
        Led ledRear = pi2go.getRearLeds();
        
        ledFront.turnOn();
        ledRear.turnOn();
        
        Thread.sleep(2000);
        
        ledRear.turnOff();
        
        
        
        for (int i = 0; i < 4000; ++i){
            ledFront.toggle();
            ledRear.toggle();
            
            Thread.sleep(500);
            if (exit) break;
        }
        
        ledFront.turnOff();
        ledRear.turnOff();
        pi2go.getUltraSoundSensor().stopSensor();
        
        
        
        CommandLineMotorDriverExample.main(args);
        
        
        
        System.exit(0);
    }
    
    
}
