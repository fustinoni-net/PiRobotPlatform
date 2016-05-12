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

//import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import net.fustinoni.raspberryPi.pi2Go.Pi2GoLite;
import net.fustinoni.raspberryPi.pi2Go.Pi2GoLiteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.FrontLedsRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.FrontalUltraSoundSensorRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.GenericSwitchRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.LineFollowareTwoSensorRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.RearLedsRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.component.SideIRSensorsRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.device.LedRemote;
import net.fustinoni.raspberryPi.robotRMI.device.MotorRemote;
import net.fustinoni.raspberryPi.robotRMI.device.MotorRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.device.ServoRemote;
import net.fustinoni.raspberryPi.robotRMI.device.ServoRemoteImpl;
import net.fustinoni.raspberryPi.robotRMI.sensor.IRSensorRemote;
import net.fustinoni.raspberryPi.robotRMI.sensor.SwitchRemote;
import net.fustinoni.raspberryPi.robotRMI.sensor.UltraSoundSensorRemote;

/**
 *
 * @author efustinoni
 */
public class Pi2GoLiteRemoteImpl extends UnicastRemoteObject implements Pi2GoLiteRemote  {
    private static final long serialVersionUID = 1L;

    public static Registry registry;

    Pi2GoLite pi2Go;
    
    public Pi2GoLiteRemoteImpl() throws RemoteException {
        pi2Go = Pi2GoLiteImpl.getPi2GoLite();
    }

    @Override
    public MotorRemote getLeftMotor() throws RemoteException {
        return new MotorRemoteImpl(pi2Go.getLeftMotor());
    }

    @Override
    public MotorRemote getRightMotor() throws RemoteException {
        return new MotorRemoteImpl(pi2Go.getRightMotor());
    }


    public static void main ( String args[] ) throws Exception
    {
        // Assign a security manager, in the event that dynamic
	// classes are loaded
        if (System.getSecurityManager() == null)
            System.setSecurityManager ( new SecurityManager() );

        // Create an instance of our power service server ...
        Pi2GoLiteRemoteImpl robj = new Pi2GoLiteRemoteImpl();
        //Pi2GoLiteRemote stub = (Pi2GoLiteRemote) UnicastRemoteObject.exportObject(robj, 0);

        registry = LocateRegistry.createRegistry(1099);
        // ... and bind it with the RMI Registry
        registry.bind ("Pi2GoLiteRemoteImpl", robj);

        System.out.println ("Service bound....");
    }    


    @Override
    public LedRemote getFrontLeds() throws RemoteException {
        return new FrontLedsRemoteImpl(pi2Go).getFrontLeds();
    }

    @Override
    public LedRemote getRearLeds() throws RemoteException {
        return new RearLedsRemoteImpl(pi2Go).getRearLeds();
    }

    @Override
    public SwitchRemote getGenericSwitch() throws RemoteException {
        try{
        return (new GenericSwitchRemoteImpl(pi2Go)).getGenericSwitch() ;
        }catch (Throwable t){
            System.out.println(t);
        }
        return null;
    }

    @Override
    public IRSensorRemote getLeftIRSensor() throws RemoteException {
        return new SideIRSensorsRemoteImpl(pi2Go).getLeftIRSensor();
    }

    @Override
    public IRSensorRemote getRightIRSensor() throws RemoteException {
        return new SideIRSensorsRemoteImpl(pi2Go).getRightIRSensor();
    }

    @Override
    public IRSensorRemote getLineLeftIRSensor() throws RemoteException {
        return new LineFollowareTwoSensorRemoteImpl(pi2Go).getLineLeftIRSensor();
    }

    @Override
    public IRSensorRemote getLineRightIRSensor() throws RemoteException {
        return new LineFollowareTwoSensorRemoteImpl(pi2Go).getLineRightIRSensor();
    }

    @Override
    public ServoRemote getPanServo() throws RemoteException {
        return new ServoRemoteImpl(pi2Go.getPanServo());
    }

    @Override
    public ServoRemote getTiltServo() throws RemoteException {
        return new ServoRemoteImpl(pi2Go.getTiltServo());
    }

    @Override
    public UltraSoundSensorRemote getUltraSoundSensor() throws RemoteException {
        return new  FrontalUltraSoundSensorRemoteImpl(pi2Go).getUltraSoundSensor();
    }
}
