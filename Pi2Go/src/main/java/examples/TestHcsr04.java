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

    private static final int ULTRA_SOUND_ECHO_PIN = 1;  //BCM 18
    private static final int ULTRA_SOUND_TRIGGER_PIN = 0; //BCM 17

    public static void main(String[] args) throws InterruptedException {

        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }

        Gpio.pinMode(ULTRA_SOUND_TRIGGER_PIN, Gpio.OUTPUT);
        Gpio.pinMode(ULTRA_SOUND_ECHO_PIN, Gpio.INPUT);
        Gpio.pullUpDnControl(ULTRA_SOUND_ECHO_PIN, Gpio.PUD_UP);

        Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, false);
        Gpio.delayMicroseconds(500);
        
        for (int i = 0; i < 50; ++i) {
            
            System.out.println("Ciclo numero: " + i);
            Thread.sleep(500l);
            long start = 0;
            long end = 0;

            
            Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, true);
            Gpio.delayMicroseconds(10);
            Gpio.digitalWrite(ULTRA_SOUND_TRIGGER_PIN, false);

            
            // Wait for the signal to return
            while (Gpio.digitalRead(ULTRA_SOUND_ECHO_PIN) == 0 ){
                if (Gpio.digitalRead(ULTRA_SOUND_ECHO_PIN) == 1) break;
            }

//        System.out.println("loopStart: " + loopStart);
//        System.out.println("Start: " + start);
            // There it is
            //loop2Start = Gpio.micros();
        start = Gpio.micros();
        while (Gpio.digitalRead(ULTRA_SOUND_ECHO_PIN) == 1 ){
            if (Gpio.digitalRead(ULTRA_SOUND_ECHO_PIN) == 0) break;
        }

        end = Gpio.micros();

//        System.out.println("loop1Start: " + loop1Start);    
//        System.out.println("start: " + start);
//        System.out.println("loop2Start: " + loop2Start);
//        System.out.println("end: " + end);
            //Considerare lo spostamento delle assegnazioni fuori dai cicli, per risparmiare tempo
        if (start == 00 || end == 0) {
            System.out.println(" ==>> " + start + " " + end);
           continue;
        }

        if (end-start > 30000) {
            System.out.println(" out of range ==>> " + (end-start));
           continue;
        }
        
            //long distanza = ((end - start) * 17190 / 100000);
            float distanza = ((end - start) /58f);
            System.out.println("distanza = " + distanza + " tempo " + (end - start)); //distanza in mm
        }
    }
}
