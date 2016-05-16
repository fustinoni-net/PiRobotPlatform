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

package net.fustinoni.raspberryPi.robotRMI.component;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.fustinoni.raspberryPi.pi2Go.Pi2GoLite;
import net.fustinoni.raspberryPi.robotRMI.sensor.IRSensorRemote;
import net.fustinoni.raspberryPi.robotRMI.sensor.IRSensorRemoteImpl;
import net.fustinoni.raspberryPi.robot.component.SideIRSensors;


/**
 *
 * @author efustinoni
 */
public class SideIRSensorsRemoteImpl extends UnicastRemoteObject  implements SideIRSensorsRemote {

    final SideIRSensors sensor;
    
    public SideIRSensorsRemoteImpl(final SideIRSensors sideIRSensors) throws RemoteException {
        sensor = sideIRSensors;
    }

    @Override
    public IRSensorRemote getLeftIRSensor() throws RemoteException {
        return new IRSensorRemoteImpl(sensor.getLeftIRSensor()) ;
    }

    @Override
    public IRSensorRemote getRightIRSensor() throws RemoteException {
        return new IRSensorRemoteImpl(sensor.getRightIRSensor());
    }
    
    
}
