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
#include "net_fustinoni_pi_hcsr04_Hcsr04.h"
#include "net_fustinoni_pi_hcsr04_Hcsr04_.h"
#include <wiringPi.h>

JNIEXPORT jlong JNICALL Java_net_fustinoni_pi_hcsr04_Hcsr04_getDistanceNative__I (JNIEnv * env, jobject obj, jint pin)
{
    return getDist_i(pin);
}

JNIEXPORT jlong JNICALL Java_net_fustinoni_pi_hcsr04_Hcsr04_getDistanceNative__II (JNIEnv * env, jobject obj, jint echo, jint trigger)
{
    return getDist_ii(echo, trigger);
}



jlong getDist_i(int pin) {

    
    //https://groups.google.com/forum/#!msg/pi4j/XoZXD2VxG1M/IsJAJnu3BgAJ
    
    if (is_wiringPi_Setup==0){
        wiringPiSetup();
        is_wiringPi_Setup=1;
    }
    
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

jlong getDist_ii(int echo, int trigger) {

    
    //https://groups.google.com/forum/#!msg/pi4j/XoZXD2VxG1M/IsJAJnu3BgAJ
    
    if (is_wiringPi_Setup==0){
        wiringPiSetup();
        //pinMode (ECHOPIN, INPUT) ;
        pinMode(trigger, OUTPUT);
        pinMode(echo, INPUT);
        digitalWrite(trigger, 0); //LOW
        delayMicroseconds(2);

        is_wiringPi_Setup=1;
    }
    
    unsigned int start = 0;
    unsigned int end = 0;
    
    unsigned int loopStart = 0;


    digitalWrite(trigger, 1); //HIGH
    delayMicroseconds(10);
    digitalWrite(trigger, 0); //LOW

    // Wait for the signal to return
    while (digitalRead(echo) == 0);
        
    start = micros();

    loopStart = micros();
    while (digitalRead(echo) == 1){
        if ((micros() - loopStart) > 30000) return 0;
    }

    end = micros();

    unsigned int distanza;
    distanza = ((end - start) /58);

    return distanza;
}

