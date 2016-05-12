/* 
 * File:   main.c
 * Author: efustinoni
 *
 * Created on September 19, 2015, 9:50 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <wiringPi.h>


#define	IR_SENSOR	15

/*
 * HC-SR04 Features

    Power Supply :+5V DC
    Quiescent Current : <2mA
    Working Current: 15mA
    Effectual Angle: <15�
    Ranging Distance : 2cm ? 400 cm/1? ? 13ft
    Resolution : 0.3 cm
    Measuring Angle: 30 degree
    Trigger Input Pulse width: 10uS
    Dimension: 45mm x 20mm x 15mm
 * 
 * velocit�Suono = 34380 cm/s
 * durata pin alto per 2 centimetri 1,1634671320535194880744618964514e-4 s = 0,12 ms = 120 microSecondi
 *                     400 cm    0,02326934264107038976148923792903 s  = 23,27 ms
 * 
 *  t= 2*d/velocit�Suono 
 * 
 *  d= velocit�Suono*t/2
 * 
 */
int main(int argc, char** argv) {


    printf("RasPi Distance with HC-SR04 \n");

    if (wiringPiSetup() == -1)
        exit(1);
    unsigned int start = 0;
    unsigned int end = 0;

    unsigned int loopStart = 0;

    int i = 0;

    for (i = 0; i < 50; ++i) {
        start = 0;
        end = 0;

        //pinMode (ECHOPIN, INPUT) ;
        pinMode(IR_SENSOR, OUTPUT);

        digitalWrite(IR_SENSOR, 0); //LOW
        delayMicroseconds(2);
        digitalWrite(IR_SENSOR, 1); //HIGH
        delayMicroseconds(10);
        digitalWrite(IR_SENSOR, 0); //LOW

        pinMode(IR_SENSOR, INPUT);

        loopStart = micros();
        // Wait for the signal to return
        while (digitalRead(IR_SENSOR) == 0 && (micros() - loopStart) < 50000) //mezzo secondo
            start = micros();

        // There it is
        loopStart = micros();
        while (digitalRead(IR_SENSOR) == 1 && (micros() - loopStart) < 50000)
            end = micros();

        
        //Considerare lo spstamento delle assegnazioni fuori dai cicli, per risparmiare tempo
        
        printf("Start: %u \n", start);
        printf("End: %u \n", end);

        if (start == 00 || end == 0) {
            printf("Non c'è misura \n");
            continue;
        }
       
        unsigned int distanza;
        distanza = (end-start) *  17190; //distanza in cm
        printf(" %u \n", distanza);
        delay(1000);
    }
    return 0;
}

/*
Start: 47220921
End: 47221280
 6171210
Start: 48222810
End: 48223138
 5638320
Start: 49224776
End: 49225105
 5655510
Start: 50226684
End: 50227005
 5517990
*/