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

package net.fustinoni.pi.camjamek3;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import net.fustinoni.pi.robot.BaseRobot;
import net.fustinoni.pi.robot.device.Motor;
import net.fustinoni.pi.robot.sensor.IRSensor;
import net.fustinoni.pi.robot.sensor.UltraSoundSensor;
import net.fustinoni.pi.robotImpl.device.MotorImpl;
import net.fustinoni.pi.robotImpl.sensor.IRSensorImpl;

/**
 *
 * @author efustinoni
 */
public class CJEK3Impl extends BaseRobot implements CJEK3{

    // motor A Right, motor Left B, Motor at front
//    # Set variables for the GPIO motor pins
//    pinMotorAForwards = 10
//    pinMotorABackwards = 9
//    pinMotorBForwards = 8
//    pinMotorBBackwards = 7
    private final Pin FORWARD_RIGHT_MOTOR_PIN = RaspiPin.GPIO_12;
    private final Pin BACKWARD_RIGHT_MOTOR_PIN = RaspiPin.GPIO_13;
    private final Pin FORWARD_LEFT_MOTOR_PIN = RaspiPin.GPIO_10;
    private final Pin BACKWARD_LEFT_MOTOR_PIN = RaspiPin.GPIO_11;

    //pinLineFollower = 25
    private final Pin FRONTAL_IR_SENSOR_PIN = RaspiPin.GPIO_06;    

    //pinTrigger = 17
    //pinEcho = 18    
    private final Pin ULTRA_SOUND_SENSOR_ECHO_PIN = RaspiPin.GPIO_01;
    private final Pin ULTRA_SOUND_SENSOR_TRIGGER_PIN = RaspiPin.GPIO_00;

    private UltraSoundSensor ultraSoundSensor;
    private Motor rightMotor;
    private Motor leftMotor;
    private IRSensor lineFrontIRSensor;
    
    private static CJEK3Impl cJEK3Impl;

    private CJEK3Impl() {
        super();
    }
    
    public final synchronized static CJEK3Impl getCJEK3Impl() {
        if (cJEK3Impl == null) {
            cJEK3Impl = new CJEK3Impl();
        }
        return cJEK3Impl;
    }
    
    
//    @Override
//    public UltraSoundSensor getUltraSoundSensor() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public synchronized final Motor getRightMotor() {
        if (rightMotor == null) {
            rightMotor = new MotorImpl(getRobotGPIO(), FORWARD_RIGHT_MOTOR_PIN, BACKWARD_RIGHT_MOTOR_PIN);
        }
        return rightMotor;
    }

    @Override
    public synchronized final Motor getLeftMotor() {
        if (leftMotor == null) {
            leftMotor = new MotorImpl(getRobotGPIO(), FORWARD_LEFT_MOTOR_PIN, BACKWARD_LEFT_MOTOR_PIN);
        }
        return leftMotor;
    }

    @Override
    public IRSensor getLineFrontalIRSensor() {
        if (lineFrontIRSensor == null) {
            lineFrontIRSensor = new IRSensorImpl(getRobotGPIO(), FRONTAL_IR_SENSOR_PIN);
        }
        return lineFrontIRSensor;
    }

    @Override
    protected void setSafeGPIOPinStart() {
        
    }
    
}
