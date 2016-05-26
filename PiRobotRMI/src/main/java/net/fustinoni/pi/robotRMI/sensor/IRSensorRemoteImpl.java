/* * Copyright (C) 2015 Enrico Fustinoni
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

package net.fustinoni.pi.robotRMI.sensor;

import net.fustinoni.pi.robotRMI.listener.IRSensorListenerLocal;
import net.fustinoni.pi.robotRMI.listener.IRSensorListenerRemote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.fustinoni.pi.robot.sensor.IRSensor;

/**
 *
 * @author efustinoni
 */
public class IRSensorRemoteImpl extends UnicastRemoteObject implements IRSensorRemote {
    
    private final IRSensor iRSensor;

    public IRSensorRemoteImpl(final IRSensor iRSensor) throws RemoteException {
        this.iRSensor = iRSensor;
    }

    @Override
    public boolean isTriggered() throws RemoteException {
        return iRSensor.isTriggered();
    }

    @Override
    public void addListener(IRSensorListenerRemote listener) throws RemoteException {
        iRSensor.addListener(new IRSensorListenerLocal(listener));
    }

    @Override
    public void removeListener(IRSensorListenerRemote listener) throws RemoteException {
        iRSensor.removeListener(new IRSensorListenerLocal(listener));
    }
}
