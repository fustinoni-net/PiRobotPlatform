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

import com.pi4j.wiringpi.Gpio;

/**
 *
 * @author efustinoni
 */
public class TestHcsr04 {

    private static final int ULTRA_SOUND_SENSOR_PIN = 1;  //BCM 18
    private static final int ULTRA_SOUND_TRIGGER_PIN = 0; //BCM 17

    public static void main(String[] args) {

        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }
        
        for (int i = 0; i < 10; ++i) {
            long start = 0;
            long end = 0;

            long loop1Start = 0;
            long loop2Start = 0;

            Gpio.pinMode(ULTRA_SOUND_TRIGGER_PIN, Gpio.OUTPUT);
            Gpio.pinMode(ULTRA_SOUND_SENSOR_PIN, Gpio.INPUT);

            
            Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, false);
            Gpio.delayMicroseconds(500);
            loop1Start = Gpio.micros();
            Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, true);
            Gpio.delayMicroseconds(10);
            Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, false);

             
            
            // Wait for the signal to return
            while (Gpio.digitalRead(ULTRA_SOUND_SENSOR_PIN) == 0 && (Gpio.micros() - loop1Start) < 500000) //mezzo secondo
            {
                start = Gpio.micros();
            }

//        System.out.println("loopStart: " + loopStart);
//        System.out.println("Start: " + start);
            // There it is
            loop2Start = Gpio.micros();
            while (Gpio.digitalRead(ULTRA_SOUND_SENSOR_PIN) == 1 && (Gpio.micros() - loop2Start) < 500000) {
                end = Gpio.micros();
            }

        System.out.println("loop1Start: " + loop1Start);    
        System.out.println("start: " + start);
        System.out.println("loop2Start: " + loop2Start);
        System.out.println("end: " + end);
            //Considerare lo spostamento delle assegnazioni fuori dai cicli, per risparmiare tempo
            if (start == 00 || end == 0) {
                System.out.println(" ==>> " + start + " " + end);
                return;
            }

            long distanza = ((end - start) * 17190 / 100000);
            System.out.println("distanza = " + distanza); //distanza in mm
        }
    }
}
