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
import net.fustinoni.raspberryPi.robotRMI.sensor.UltraSoundSensorLocal;
import net.fustinoni.raspberryPi.robot.component.FrontalUltraSoundSensor;
import net.fustinoni.raspberryPi.robot.sensor.UltraSoundSensor;

/**
 *
 * @author efustinoni
 */
public class FrontalUltraSoundSensorLocal implements FrontalUltraSoundSensor{
    private final FrontalUltraSoundSensorRemote sensor;

    public FrontalUltraSoundSensorLocal(FrontalUltraSoundSensorRemote sensor) {
        this.sensor = sensor;
    }

    @Override
    public UltraSoundSensor getUltraSoundSensor() {
        try {
            return  new UltraSoundSensorLocal(sensor.getUltraSoundSensor());
        } catch (RemoteException ex) {
            Logger.getLogger(FrontalUltraSoundSensorLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
