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
import static net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl.getPi2GoLite;


/**
 *
 * @author efustinoni
 */
public class IRSensorsExample {
    
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

        
        for (;;) {
            Thread.sleep(500);
        }
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller        
    }
    
    
}
