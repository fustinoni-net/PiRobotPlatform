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

#include <jni.h>
#include <stdio.h>
#include "net_fustinoni_raspberryPi_pi2Go_sensor_Hcsr04.h"
#include "net_fustinoni_raspberryPi_pi2Go_sensor_Hcsr_04.h"
#include <wiringPi.h>

JNIEXPORT jlong JNICALL Java_net_fustinoni_raspberryPi_pi2Go_sensor_Hcsr04_getDistanceNative (JNIEnv * env, jobject obj, jint pin)
{
    return getDist(pin);
}

jlong getDist(int pin) {

    wiringPiSetup();
    
    unsigned int start = 0;
    unsigned int end = 0;

    unsigned int loopStart = 0;


    //pinMode (ECHOPIN, INPUT) ;
    pinMode(pin, OUTPUT);

    digitalWrite(pin, 0); //LOW
    delayMicroseconds(2);
    digitalWrite(pin, 1); //HIGH
    delayMicroseconds(10);
    digitalWrite(pin, 0); //LOW

    pinMode(pin, INPUT);

    loopStart = micros();
    // Wait for the signal to return
    while (digitalRead(pin) == 0 && (micros() - loopStart) < 500000) //mezzo secondo
        start = micros();

    // There it is
    loopStart = micros();
    while (digitalRead(pin) == 1 && (micros() - loopStart) < 500000)
        end = micros();


    //Considerare lo spostamento delle assegnazioni fuori dai cicli, per risparmiare tempo


    if (start == 00 || end == 0) {
        return 0;
    }

    unsigned int distanza;
    distanza = ((end - start) * 17190/100000); //distanza in mm

    return distanza;
}