/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.hcsr04;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import net.fustinoni.pi.robot.component.RobotGPIO;

/**
 *
 * @author efustinoni
 */
public class Hcsr04Impl extends Hcsr04{
    
    final int echoPin;
    final int triggerPin;
    
    public Hcsr04Impl (final RobotGPIO pi2goGPIO, final Pin echo, final Pin trigger){
        
        echoPin = echo.getAddress();
        triggerPin = trigger.getAddress();
        pi2goGPIO.export(PinMode.DIGITAL_INPUT,pi2goGPIO.provisionDigitalInputPin(echo));
        pi2goGPIO.export(PinMode.DIGITAL_OUTPUT,pi2goGPIO.provisionDigitalOutputPin(trigger));
    }
    
    @Override
    public long getDistance(){
        return getDistanceNative(echoPin, triggerPin);
    }
}
