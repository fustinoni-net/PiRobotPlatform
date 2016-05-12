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

package net.fustinoni.raspberryPi.robotRMI.sensor;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fustinoni.raspberryPi.robotRMI.listener.UltraSoundSensorListenerRemoteImpl;
import net.fustinoni.raspberryPi.robot.listener.UltraSoundSensorListener;
import net.fustinoni.raspberryPi.robot.sensor.UltraSoundSensor;

/**
 *
 * @author efustinoni
 */
public class UltraSoundSensorLocal implements UltraSoundSensor {
    private final UltraSoundSensorRemote sensor;

    public UltraSoundSensorLocal(final UltraSoundSensorRemote sensor) {
        this.sensor = sensor;
    }

    @Override
    public void addListener(UltraSoundSensorListener listener) {
        try {
            sensor.addListener(new UltraSoundSensorListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public long getDistance() {
        try {
            return sensor.getDistance();
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void removeListener(UltraSoundSensorListener listener) {
        try {
            sensor.removeListener(new UltraSoundSensorListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void startSensor(int intervallSeconds) {
        try {
            sensor.startSensor(intervallSeconds);
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stopSensor() {
        try {
            sensor.stopSensor();
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
