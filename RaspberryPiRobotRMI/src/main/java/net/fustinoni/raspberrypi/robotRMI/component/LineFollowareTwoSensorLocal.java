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
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fustinoni.raspberryPi.robotRMI.sensor.IRSensorLocal;
import net.fustinoni.raspberryPi.robot.component.LineFollowareTwoSensor;
import net.fustinoni.raspberryPi.robot.sensor.IRSensor;

/**
 *
 * @author efustinoni
 */
public class LineFollowareTwoSensorLocal implements LineFollowareTwoSensor{
    private final LineFollowareTwoSensorRemote sensors;

    public LineFollowareTwoSensorLocal(final LineFollowareTwoSensorRemote sensors) {
        this.sensors = sensors;
    }

    @Override
    public IRSensor getLineLeftIRSensor() {
        try {
            return new IRSensorLocal(sensors.getLineLeftIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(LineFollowareTwoSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public IRSensor getLineRightIRSensor() {
        try {
            return new IRSensorLocal(sensors.getLineRightIRSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(LineFollowareTwoSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
