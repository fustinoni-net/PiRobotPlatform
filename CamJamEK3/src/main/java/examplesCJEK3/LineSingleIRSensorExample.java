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

package examplesCJEK3;

import net.fustinoni.pi.camjamek3.CJEK3;
import static net.fustinoni.pi.camjamek3.CJEK3Impl.getCJEK3Impl;
import net.fustinoni.pi.robot.listener.IRSensorListener;



/**
 *
 * @author efustinoni
 */
public class LineSingleIRSensorExample {
    
    public static void main (String... args) throws InterruptedException{
        
        CJEK3 robot = getCJEK3Impl();

        robot.getLineFrontalIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
            System.out.println(" --> LineFrontalIRSensor is: ".concat(isFired ? "on" : "off" ));
        });

        
        for (;;) {
            Thread.sleep(500);
        }
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller        
    }
    
    
}
