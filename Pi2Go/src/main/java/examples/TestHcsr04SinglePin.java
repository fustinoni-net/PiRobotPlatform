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

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

/**
 *
 * @author efustinoni
 */
public class TestHcsr04SinglePin {
    
    private static final int ULTRA_SOUND_SENSOR_PIN = 15;
    
    public static void main(String[] args) {
        
        long start = 0;
        long end = 0;

        long loopStart = 0;

        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }
        
        Gpio.pinMode(ULTRA_SOUND_SENSOR_PIN, Gpio.OUTPUT);
        Gpio.digitalWrite(ULTRA_SOUND_SENSOR_PIN, false);
        Gpio.delayMicroseconds(2);
        Gpio.digitalWrite(ULTRA_SOUND_SENSOR_PIN, true);
        Gpio.delayMicroseconds(10);
        Gpio.digitalWrite(ULTRA_SOUND_SENSOR_PIN, false);
        Gpio.delayMicroseconds(2);
        Gpio.pinMode(ULTRA_SOUND_SENSOR_PIN, Gpio.INPUT);
        
        
        
        
        
        loopStart = Gpio.micros();
        // Wait for the signal to return
        while ( Gpio.digitalRead(ULTRA_SOUND_SENSOR_PIN) == 0 && (Gpio.micros() - loopStart) < 500000) //mezzo secondo
            start = Gpio.micros();

//        System.out.println("loopStart: " + loopStart);
//        System.out.println("Start: " + start);
        
        // There it is
        loopStart = Gpio.micros();
        while (Gpio.digitalRead(ULTRA_SOUND_SENSOR_PIN) == 1 && (Gpio.micros() - loopStart) < 500000)
            end = Gpio.micros();

//        System.out.println("loopStart: " + loopStart);
//        System.out.println("end: " + end);

        //Considerare lo spostamento delle assegnazioni fuori dai cicli, per risparmiare tempo


        if (start == 00 || end == 0) {
            System.out.println(" ==>> " + start + " " + end);
            return ;
        }

        long distanza;
        System.out.println(distanza = ((end - start) * 17190/100000)); //distanza in mm
        
    }
}
