/**
 * 
 * **********************************************************************
 * This file is part of the PiRobotPlatform java library project. 
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

package net.fustinoni.pi.robotWebControl;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import net.fustinoni.pi.robot.PiRobot;
import net.fustinoni.pi.robot.component.FrontLeds;
import net.fustinoni.pi.robot.component.FrontalUltraSoundSensor;
import net.fustinoni.pi.robot.component.LeftRightMotors;
import net.fustinoni.pi.robot.component.PanTiltServos;
import net.fustinoni.pi.robot.component.RearLeds;
import net.fustinoni.pi.robot.component.SideIRSensors;
import net.fustinoni.pi.robot.listener.IRSensorListener;
import net.fustinoni.pi.robot.listener.UltraSoundSensorListener;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.AnalogJoystickMotorsDriver;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.AnalogJoystickMotorsDriverEnlargedCenterDecorator;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.AnalogJoystickMotorsDriverImpl;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter.LinearConverter;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter.StepperDecorator;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.MotorsDriverImpl;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.MotorsDriverSetMinimumSpeedDecorator;
import net.fustinoni.pi.robot.robotUtils.PanTiltServoDrivers.PanTiltServoDriverImpl;
import net.fustinoni.pi.robot.robotUtils.PanTiltServoDrivers.StepByStep.PanTiltStepByStepDriver;
import net.fustinoni.pi.robot.robotUtils.PanTiltServoDrivers.StepByStep.PanTiltStepByStepDriverImpl;
import net.fustinoni.pi.utils.ShutDown;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONException;
import org.json.JSONObject;
import static spark.Spark.*;

/**
 *
 * @author efustinoni
 */
public class RobotDriver {
    
    static HashSet<Session> userSet = new HashSet<>();
    static Session master ; //The session with the robot controll
    
    static PiRobot robot ;
    static AnalogJoystickMotorsDriver chaufer ;
    static PanTiltStepByStepDriver camera ;
    
    static int minimumPan = -90;
    static int minimumTilt = -90;
    static int maximumPan = 70;
    static int maximumTilt = 70;


    
//    private static Timer timer;
//    private static boolean sensorStatus = false;
    
    
    public static void startPiRobotWebInterface(final PiRobot piRobot) {
        staticFileLocation("/public");
        
        webSocket("/robot", RobotWebSocketHandler.class);
        
        init();

//        if (timer == null){
//        
//            timer = new Timer();
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    sensorStatus = !sensorStatus;
//                    System.out.println("messaaggio inviato");
//                    broadcastFrontalUltraSoundSensorMessage(System.currentTimeMillis());
//                    broadcastLeftIRSensorMessage(sensorStatus);
//                    broadcastRightIRSensorMessage(!sensorStatus);
//                }
//            }, 0, 1 * 500); 
//        }
        
        robot = piRobot;

//        if (robot instanceof LeftRightMotors)
//            chaufer = new AnalogJoystickMotorsDriverEnlargedCenterDecorator( 
//                    new AnalogJoystickMotorsDriverImpl(
//                            new MotorsDriverSetMinimumSpeedDecorator(new MotorsDriverImpl((LeftRightMotors)robot)),
//                            new StepperDecorator(new LinearConverter())
//                    )
//            );

        if (robot instanceof LeftRightMotors)
            chaufer = new AnalogJoystickMotorsDriverEnlargedCenterDecorator(
                      new AnalogJoystickMotorsDriverImpl(
                            new MotorsDriverImpl((LeftRightMotors)robot),
                            new StepperDecorator(new LinearConverter())
                      )
            );


        if (robot instanceof PanTiltServos){
            camera = new PanTiltStepByStepDriverImpl(
                    new PanTiltServoDriverImpl((PanTiltServos)robot, minimumPan, minimumTilt, maximumPan, maximumTilt),1,1);
            camera.setPanTiltCenter();
        }

        
        if (robot instanceof SideIRSensors){
            ((SideIRSensors)robot).getLeftIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
                System.out.println(" --> LeftIRSensor is: ".concat(isFired ? "on" : "off" ));
                broadcastLeftIRSensorMessage(isFired);
            });

            ((SideIRSensors)robot).getRightIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
                System.out.println(" --> RightIRSensor is: ".concat(isFired ? "on" : "off" ));
                broadcastRightIRSensorMessage(isFired);
            });
        }
        
        if (robot instanceof FrontalUltraSoundSensor){
            ((FrontalUltraSoundSensor)robot).getUltraSoundSensor().addListener((UltraSoundSensorListener) (long distance) ->{
                broadcastFrontalUltraSoundSensorMessage(distance);
            });

            ((FrontalUltraSoundSensor)robot).getUltraSoundSensor().startSensor(1);
        }
    }

    public static void main(String[] args) {
        startPiRobotWebInterface(null);
    }

    public static void broadcastFrontalUltraSoundSensorMessage( long distance) {
        broadcastMessage("distanceFront", String.valueOf(distance));
    }

    public static void broadcastLeftIRSensorMessage( boolean on) {
        broadcastMessage("leftIRSensor", String.valueOf(on));
    }

    public static void broadcastRightIRSensorMessage( boolean on) {
        broadcastMessage("rightIRSensor", String.valueOf(on));
    }

    //Sends a message to all users
    public static synchronized void broadcastMessage(String variable, String value) {
        userSet.stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put(variable, value)
                ));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    static void turnAllOff(){
        if (chaufer != null) chaufer.stopMotors();
        if (camera != null) camera.setPanTiltCenter();
        if (robot instanceof FrontLeds) ((FrontLeds) robot).getFrontLeds().turnOff();
        if (robot instanceof RearLeds) ((RearLeds) robot).getRearLeds().turnOff();
    }
    static void exit(){
        turnAllOff();
        System.exit(0);
    }
    
    static void shutDown() {
        turnAllOff();
        if (robot != null) ShutDown.shutDownNow();
        System.exit(0);
    }
}