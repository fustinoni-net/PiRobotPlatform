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

package net.fustinoni.pi.motejpirobot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import motej.Extension;
import motej.Mote;
import motej.demos.common.SimpleMoteFinder;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import motej.request.ReportModeRequest;
import motejx.extensions.nunchuk.AnalogStickEvent;
import motejx.extensions.nunchuk.AnalogStickListener;
import motejx.extensions.nunchuk.Nunchuk;
import motejx.extensions.nunchuk.NunchukButtonEvent;
import motejx.extensions.nunchuk.NunchukButtonListener;
import net.fustinoni.pi.motejpirobot.moteLed.PlayerLeds;
import net.fustinoni.pi.motejpirobot.moteLed.PlayerLedsImpl;
import net.fustinoni.pi.motejpirobot.moteListener.ButtonAListener;
import net.fustinoni.pi.motejpirobot.moteListener.ButtonCListener;
import net.fustinoni.pi.motejpirobot.moteListener.ButtonHomeListener;
import net.fustinoni.pi.motejpirobot.moteListener.ButtonZListener;
import net.fustinoni.pi.motejpirobot.moteListener.DPadButtonListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadDownListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadLeftListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadRightListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadUpListener;
import net.fustinoni.pi.pi2Go.Pi2GoLite;
import net.fustinoni.pi.pi2Go.Pi2GoLiteImpl;
import net.fustinoni.pi.robot.BaseRobot;
import net.fustinoni.pi.robot.component.FrontLeds;
import net.fustinoni.pi.robot.component.FrontalUltraSoundSensor;
import net.fustinoni.pi.robot.component.GenericSwitch;
import net.fustinoni.pi.robot.component.LeftRightMotors;
import net.fustinoni.pi.robot.component.PanTiltServos;
import net.fustinoni.pi.robot.component.RearLeds;
import net.fustinoni.pi.robot.component.SideIRSensors;
import net.fustinoni.pi.robot.listener.IRSensorListener;
import net.fustinoni.pi.robot.listener.SwitchListener;
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

/**
 *
 * @author efustinoni
 */
public class WiimotePiRobot {

    static int centerX=123;
    static int centerY=130;
    static int minX=29;
    static int maxX=227;
    static int minY=38;
    static int maxY=227;
    
    static int oldX=0;
    static int oldY=0;
    static long lastMotorCommand = 0;
    static long lastButtonCPressed = 0;
    static long lastButtonZPressed = 0;
    
    static int minimumPan = -90;
    static int minimumTilt = -90;
    static int maximumPan = 70;
    static int maximumTilt = 70;
    
    static boolean exit = false;
    
    /**
     * @param args the command line arguments
     * @throws java.rmi.NotBoundException
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        
        //BaseRobot piLocal = Pi2GoLiteLocal.getPi2GoLiteLocat(\"raspi-pi2go.homenet.telecomitalia.it");
        BaseRobot piLocal = Pi2GoLiteImpl.getPi2GoLite();

        if (piLocal instanceof GenericSwitch)
            ((GenericSwitch)piLocal).getGenericSwitch().addListener((SwitchListener) (boolean isPressed) ->{

                //http://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
                //sleep 30s; shutdown -h now

                if (piLocal instanceof Pi2GoLiteImpl)
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    try {
                                        Process p = Runtime.getRuntime().exec("shutdown -h -P now");
                                        p.waitFor();

                                        BufferedReader reader =
                                                new BufferedReader(new InputStreamReader(p.getInputStream()));

                                        String line = "";
                                        while ((line = reader.readLine())!= null) {
                                            System.out.println(line);
                                        }
                                        reader.close();
                                        p.getInputStream().close();
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                }
                            },
                            2000L
                    );
                exit = true;
            });


        AnalogJoystickMotorsDriver chaufer = null;
        if (piLocal instanceof LeftRightMotors)
            chaufer = new AnalogJoystickMotorsDriverEnlargedCenterDecorator( 
                    new AnalogJoystickMotorsDriverImpl(
                            new MotorsDriverSetMinimumSpeedDecorator(new MotorsDriverImpl((LeftRightMotors)piLocal)),
                            new StepperDecorator(new LinearConverter())
                    )
            );

        PanTiltStepByStepDriver camera = null;
        if (piLocal instanceof PanTiltServos)
            camera = new PanTiltStepByStepDriverImpl(
                        new PanTiltServoDriverImpl((PanTiltServos)piLocal, minimumPan, minimumTilt, maximumPan, maximumTilt),1,1);


        Nunchuk nunchuk = null;

        SimpleMoteFinder simpleMoteFinder = new SimpleMoteFinder();
        
        if (piLocal instanceof FrontLeds) ((FrontLeds)piLocal).getFrontLeds().turnOn();
        if (piLocal instanceof RearLeds) ((RearLeds)piLocal).getRearLeds().turnOn();
        Mote mote = simpleMoteFinder.findMote();
        if (piLocal instanceof FrontLeds) ((FrontLeds)piLocal).getFrontLeds().turnOff();
        if (piLocal instanceof RearLeds) ((RearLeds)piLocal).getRearLeds().turnOff();

        
        PlayerLeds playerLeds = new PlayerLedsImpl(mote);

        playerLeds.player3LedOn();


        Extension ext =  mote.getExtension();

        while (ext==null){
            ext =  mote.getExtension();
            try {
                    Thread.sleep(50);
            } catch (InterruptedException ex) {
            }            
        }

        if (ext instanceof Nunchuk) {
        
            nunchuk = (Nunchuk) ext;

            //printCalibrationData(nunchuk);

            mote.setReportMode(ReportModeRequest.DATA_REPORT_0x32);

            if (chaufer != null) addAnalogStickListener(nunchuk, chaufer);
            
            addNunchukButtonListener(nunchuk, piLocal);

        }
        
        if (piLocal instanceof SideIRSensors){
            ((SideIRSensors)piLocal).getLeftIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
                System.out.println(" --> LeftIRSensor is: ".concat(isFired ? "on" : "off" ));
                if (isFired){
                    playerLeds.player1LedOn();
                    mote.rumble(200);
                }else{
                    playerLeds.player1LedOff();
                }
            });

            ((SideIRSensors)piLocal).getRightIRSensor().addListener((IRSensorListener) (boolean isFired) ->{
                System.out.println(" --> RightIRSensor is: ".concat(isFired ? "on" : "off" ));
                if (isFired){ 
                    playerLeds.player4LedOn();
                    mote.rumble(200);
                }else{
                    playerLeds.player4LedOff();
                }
            });
        }
        
        if (piLocal instanceof FrontalUltraSoundSensor){
            ((FrontalUltraSoundSensor)piLocal).getUltraSoundSensor().addListener((UltraSoundSensorListener) (long distance) ->{
                if (distance < 200){
                    playerLeds.player2LedOn();
                    mote.rumble(200);
                }else{
                    playerLeds.player2LedOff();
                }
                //mote.rumble(distance*10);
            });

            ((FrontalUltraSoundSensor)piLocal).getUltraSoundSensor().startSensor(1);
        }
        
        if (camera != null) addCoreButtonListener(mote, camera);
        
//*****************************        
        while (!exit) {
            try {
                Thread.sleep(500);
                //printCalibrationData(nunchuk);
            } catch (InterruptedException ex) {
                //			log.error(ex.getMessage(), ex);
            }
        }

//        chaufer.stopMotors();
        mote.disconnect();

            try {
                Thread.sleep(5000);
                //printCalibrationData(nunchuk);
            } catch (InterruptedException ex) {
                //			log.error(ex.getMessage(), ex);
            }

        System.exit(0);
    }

    private static void addAnalogStickListener(Nunchuk nunchuk, AnalogJoystickMotorsDriver chaufer) {
        nunchuk.addAnalogStickListener((AnalogStickListener) (AnalogStickEvent evt)->{
            
// un evento ogni tot millisecondi
//            long now = System.currentTimeMillis();
//            if ( now - lastMotorCommand  < 500) return;
//            
//            lastMotorCommand = now;
            
            int x =((int)evt.getPoint().getX()/10)*10;
            int y = ((int) evt.getPoint().getY()/10)*10;
            if (analogListenerTuner(x, y)) return;
            synchronized(chaufer){
                WiimotePiRobot.oldX = x;
                WiimotePiRobot.oldY = y;
            }
            
            //System.out.println( "Stic x:" + x + " y:" + y );
            chaufer.jostickImput(x, y,
                    WiimotePiRobot.minX, WiimotePiRobot.maxX,
                    WiimotePiRobot.minY, WiimotePiRobot.maxY,
                    WiimotePiRobot.centerX, WiimotePiRobot.centerY);

            
//            WiimotePiRobot.oldX=(int)evt.getPoint().getX();
//            WiimotePiRobot.oldY = (int) evt.getPoint().getY();
            
        });
    }

    private static boolean analogListenerTuner(int x, int y) {
        
        //System.out.println("Joistick: " + x + " " +y);
        //Con il controllo a step di 10 dovrebbe bastare questo controllo
        return WiimotePiRobot.oldX == x && WiimotePiRobot.oldY==y;
        
        //return (WiimotePiRobot.oldX < (x +5) && WiimotePiRobot.oldX > (x -5)) && (WiimotePiRobot.oldY < (y +5) && WiimotePiRobot.oldY > (y -5));
    }
    
    
    private static void addNunchukButtonListener(Nunchuk nunchuk, BaseRobot piLocal) {

        if (piLocal instanceof FrontLeds) 
            nunchuk.addNunchukButtonListener(new ButtonCListener(
                    (NunchukButtonListener) (NunchukButtonEvent evt) -> {
                        System.out.println("Buton C pressed");
                        ((FrontLeds)piLocal).getFrontLeds().toggle();}
                    , 1000));

        if (piLocal instanceof RearLeds) 
            nunchuk.addNunchukButtonListener(new ButtonZListener(
                    (NunchukButtonListener) (NunchukButtonEvent evt) -> {
                       System.out.println("Buton Z pressed");
                       ((RearLeds)piLocal).getRearLeds().toggle();}
                    , 1000));
        
//        nunchuk.addNunchukButtonListener((NunchukButtonListener) (NunchukButtonEvent evt) -> {
//            SwingUtilities.invokeLater(() -> {
//                if (evt.isButtonCPressed()) {
//                    // un evento ogni tot millisecondi
//                    long now = System.currentTimeMillis();
//                    if ( now - lastButtonCPressed  < 1000) return;
//                    lastButtonCPressed = now;
//
//                    System.out.println("Buton C pressed");
//                    piLocal.getFrontLeds().toggle();
//                }
//            });
//        });
//        nunchuk.addNunchukButtonListener((NunchukButtonListener) (NunchukButtonEvent evt) -> {
//            SwingUtilities.invokeLater(() -> {
//                if (evt.isButtonZPressed()) {
//                    // un evento ogni tot millisecondi
//                    long now = System.currentTimeMillis();
//                    if ( now - lastButtonZPressed  < 1000) return;
//                    lastButtonZPressed = now;
//
//                    System.out.println("Buton Z pressed");
//                    
//                   piLocal.getRearLeds().toggle();
//                }
//            });
//        });
    }

    private static void addCoreButtonListener(Mote mote, PanTiltStepByStepDriver camera) {

        mote.addCoreButtonListener(
                new ButtonAListener( (CoreButtonListener)
                (CoreButtonEvent evt) -> {
                    System.out.println("feedbackButtonA pressed");
                    camera.setPanTiltCenter();}
                , 1000));

        mote.addCoreButtonListener(new ButtonHomeListener(
                (final CoreButtonEvent evt) -> {
                    System.out.println("feedbackButtonHome pressed"); 
                    mote.rumble(500);}
                , 1000));

        DPadButtonListener dPAdListener = new DPadButtonListener(50);

        dPAdListener.addButtonDPadUpListener(new ButtonDPadUpListener(
                (final CoreButtonEvent evt) ->{
                    camera.moveUp();}
                ));

        dPAdListener.addButtonDPadDownListener(new ButtonDPadDownListener(
                (final CoreButtonEvent evt) ->{
                    camera.moveDown();}
                ));

        dPAdListener.addButtonDPadLeftListener(new ButtonDPadLeftListener(
                (final CoreButtonEvent evt) ->{
                    camera.moveLeft();}
                ));

        dPAdListener.addButtonDPadRightListener(new ButtonDPadRightListener(
                (final CoreButtonEvent evt) ->{
                    camera.moveRight();}
                ));

        mote.addCoreButtonListener(dPAdListener);



        //*****************************
        
//        mote.addCoreButtonListener((final CoreButtonEvent evt) -> {
//            
//            if (evt.isButtonAPressed()) {
//                //System.out.println("feedbackButtonA pressed"); 
//                camera.setPanTiltCenter();
//            } 
////            else {
////                System.out.println("feedbackButtonA not pressed"); 
////            }
//            
//            if (evt.isButtonBPressed())
//            {
//                System.out.println("feedbackButtonB pressed"); 
//            }
////            else {
////                System.out.println("feedbackButtonB not pressed"); 
////            }
//            
//            if (evt.isButtonHomePressed())
//            {
//                System.out.println("feedbackButtonHome pressed"); 
//                mote.rumble(500);
//            }
////            else {
////                System.out.println("feedbackButtonHome not pressed"); 
////            }
//            
//            if (evt.isButtonMinusPressed())
//            {
//                System.out.println("feedbackButtonMinus pressed"); 
//            }
////            else {
////                System.out.println("feedbackButtonMinus not pressed"); 
////            }
//            
//            if (evt.isButtonOnePressed())
//            {
//                System.out.println("feedbackButtonOne pressed"); 
//            }
////            else {
////                System.out.println("feedbackButtonOne not pressed"); 
////            }
//            
//            if (evt.isButtonPlusPressed())
//            {
//                System.out.println("feedbackButtonPlus pressed"); 
//            }
////            else {
////                System.out.println("feedbackButtonPlus not pressed"); 
////            }
//            
//            if (evt.isButtonTwoPressed())
//            {
//                System.out.println("feedbackButtonTwo pressed"); 
//            }
////            else {
////                System.out.println("feedbackButtonTwo not pressed"); 
////            }
//            
//            if (evt.isDPadDownPressed())
//            {
//                //System.out.println("feedbackButtonDPadDown pressed"); 
//                camera.moveDown();
//            }
////            else {
////                System.out.println("feedbackButtonDPadDown not pressed"); 
////            }
//            
//            if (evt.isDPadLeftPressed())
//            {                
//                //System.out.println("feedbackButtonDPadLeft pressed");
//                camera.moveLeft();
//                
//            }
////            else {
////                System.out.println("feedbackButtonDPadLeft not pressed"); 
////            }
//            
//            if (evt.isDPadRightPressed())
//            {
//                //System.out.println("feedbackButtonDPadRight pressed"); 
//                camera.moveRight();
//            }
////            else {
////                System.out.println("feedbackButtonDPadRight not pressed"); 
////            }
//            
//            if (evt.isDPadUpPressed())
//            {
//                //System.out.println("feedbackButtonDPadUp pressed"); 
//                camera.moveUp();
//            }
////            else {
////                System.out.println("feedbackButtonDPadUp not pressed"); 
////            }
//        });
    }


    private static void printCalibrationData(Nunchuk nunchuk) {
        System.out.println( "getMinimumAnalogPoint: " + nunchuk.getCalibrationData().getMinimumAnalogPoint());
        System.out.println( "getMaximumAnalogPoint: " + nunchuk.getCalibrationData().getMaximumAnalogPoint());
        System.out.println( "getCenterAnalogPoint: " + nunchuk.getCalibrationData().getCenterAnalogPoint());
//								minPoint.setText("cal (min) - x: " + min.x + " / y: " + min.y);
//								maxPoint.setText("cal (max) - x: " + max.x + " / y: " + max.y);
//								centerPoint.setText("cal (center) - x: " + center.x + " / y: " + center.y);
        
        System.out.println( "getZeroForceX: " + nunchuk.getCalibrationData().getZeroForceX());
        System.out.println( "getGravityForceX: " + nunchuk.getCalibrationData().getGravityForceX());
//								accelerometerPanel.setCalibrationDataX(zero, earth);
        
        System.out.println( "getZeroForceY: " + nunchuk.getCalibrationData().getZeroForceY());
        System.out.println( "getGravityForceY: " + nunchuk.getCalibrationData().getGravityForceY());
//								accelerometerPanel.setCalibrationDataY(zero, earth);
        
        System.out.println( "getZeroForceZ: " + nunchuk.getCalibrationData().getZeroForceZ());
        System.out.println( "getGravityForceZ: " + nunchuk.getCalibrationData().getGravityForceZ());
    }
    
}
