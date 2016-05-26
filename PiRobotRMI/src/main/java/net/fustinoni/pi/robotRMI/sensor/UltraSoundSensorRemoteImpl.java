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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.fustinoni.pi.robotRMI.listener.UltraSoundSensorListenerLocal;
import net.fustinoni.pi.robotRMI.listener.UltraSoundSensorListenerRemote;
import net.fustinoni.pi.robot.sensor.UltraSoundSensor;

/**
 *
 * @author efustinoni
 */
public class UltraSoundSensorRemoteImpl extends UnicastRemoteObject implements UltraSoundSensorRemote {
    
    private final UltraSoundSensor sensor;

    public UltraSoundSensorRemoteImpl(final UltraSoundSensor sensor) throws RemoteException {
        this.sensor = sensor;
    }

    @Override
    public void addListener(UltraSoundSensorListenerRemote listener) throws RemoteException {
        sensor.addListener(new UltraSoundSensorListenerLocal(listener));
    }

    @Override
    public long getDistance() throws RemoteException {
        return sensor.getDistance();
    }

    @Override
    public void removeListener(UltraSoundSensorListenerRemote listener) throws RemoteException {
        sensor.removeListener(new UltraSoundSensorListenerLocal(listener));
    }

    @Override
    public void startSensor(int intervallSeconds) throws RemoteException {
        sensor.startSensor(intervallSeconds);
    }

    @Override
    public void stopSensor() throws RemoteException {
        sensor.stopSensor();
    }
    
    
    
}
