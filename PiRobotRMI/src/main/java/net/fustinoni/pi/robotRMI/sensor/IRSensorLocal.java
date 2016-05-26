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
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fustinoni.pi.robotRMI.listener.IRSensorListenerRemoteImpl;
import net.fustinoni.pi.robot.listener.IRSensorListener;
import net.fustinoni.pi.robot.sensor.IRSensor;

/**
 *
 * @author efustinoni
 */
public class IRSensorLocal implements IRSensor {
    
    private final IRSensorRemote iRSensor;

    public IRSensorLocal(final IRSensorRemote iRSensor) {
        this.iRSensor = iRSensor;
    }

    @Override
    public boolean isTriggered(){
        try {
            return iRSensor.isTriggered();
        } catch (RemoteException ex) {
            Logger.getLogger(IRSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void addListener(IRSensorListener listener) {
        try {
            iRSensor.addListener(new IRSensorListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(IRSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeListener(IRSensorListener listener) {
        try {
            iRSensor.removeListener(new IRSensorListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(IRSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
