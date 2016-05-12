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

package net.fustinoni.raspberryPi.pi2GoRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fustinoni.raspberryPi.pi2Go.Pi2GoLite;
import net.fustinoni.raspberryPi.robotRMI.device.LedLocal;
import net.fustinoni.raspberryPi.robotRMI.device.MotorLocal;
import net.fustinoni.raspberryPi.robotRMI.device.ServoLocal;
import net.fustinoni.raspberryPi.robotRMI.sensor.IRSensorLocal;
import net.fustinoni.raspberryPi.robotRMI.sensor.SwitchLocal;
import net.fustinoni.raspberryPi.robotRMI.sensor.UltraSoundSensorLocal;
import net.fustinoni.raspberryPi.robot.device.Led;
import net.fustinoni.raspberryPi.robot.device.Motor;
import net.fustinoni.raspberryPi.robot.device.Servo;
import net.fustinoni.raspberryPi.robot.sensor.IRSensor;
import net.fustinoni.raspberryPi.robot.sensor.Switch;
import net.fustinoni.raspberryPi.robot.sensor.UltraSoundSensor;

/**
 *
 * @author efustinoni
 */
public class Pi2GoLiteLocal implements Pi2GoLite{

    private final Pi2GoLiteRemote pi2GoRemote;

    public Pi2GoLiteLocal(final Pi2GoLiteRemote pi2Go) {
        this.pi2GoRemote = pi2Go;
    }
    
    
    public final synchronized static Pi2GoLite getPi2GoLiteLocat(String rmiServerUrl) 
            throws NotBoundException, 
                   MalformedURLException, 
                   RemoteException 
    {
        
	// Assign security manager
        if (System.getSecurityManager() == null)
        {
                System.setSecurityManager
                (new SecurityManager());
        }

        // Call registry for PowerService
        Pi2GoLiteRemote piRemote = (Pi2GoLiteRemote) Naming.lookup
                ("rmi://" + rmiServerUrl + "/Pi2GoLiteRemoteImpl");
                //("rmi://" +"raspi-pi2go.homenet.telecomitalia.it" + "/Pi2GoLiteRemote");

        Pi2GoLite piLocal = new Pi2GoLiteLocal (piRemote);
        
        return piLocal;
    }
    
    
    
    @Override
    public Switch getGenericSwitch(){
        try {
            return new SwitchLocal(pi2GoRemote.getGenericSwitch());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Motor getLeftMotor() {
        try {
            return new MotorLocal(pi2GoRemote.getLeftMotor()) ;
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Motor getRightMotor() {
        try {
            return  new MotorLocal(pi2GoRemote.getRightMotor()) ;
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public Led getFrontLeds() {
        try {
            return new LedLocal(pi2GoRemote.getFrontLeds());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Led getRearLeds() {
        try {
            return new LedLocal(pi2GoRemote.getRearLeds());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public IRSensor getLeftIRSensor() {
        try {
            return new IRSensorLocal(pi2GoRemote.getLeftIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IRSensor getLineLeftIRSensor() {
        try {
            return new IRSensorLocal(pi2GoRemote.getLineLeftIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IRSensor getLineRightIRSensor() {
        try {
            return new IRSensorLocal(pi2GoRemote.getLineRightIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Servo getPanServo() {
        try {
            return new ServoLocal(pi2GoRemote.getPanServo()) ;
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IRSensor getRightIRSensor() {
        try {
            return new IRSensorLocal(pi2GoRemote.getRightIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public Servo getTiltServo() {
        try {
            return new ServoLocal(pi2GoRemote.getTiltServo()) ;
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UltraSoundSensor getUltraSoundSensor() {
        try {
            return new UltraSoundSensorLocal(pi2GoRemote.getUltraSoundSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(Pi2GoLiteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
