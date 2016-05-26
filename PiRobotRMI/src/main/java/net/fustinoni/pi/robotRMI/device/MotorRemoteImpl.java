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

package net.fustinoni.pi.robotRMI.device;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import net.fustinoni.pi.robot.device.Motor;

/**
 *
 * @author efustinoni
 */
public class MotorRemoteImpl extends UnicastRemoteObject  implements MotorRemote{

    final Motor motor;
    
    public MotorRemoteImpl(final Motor motor) throws RemoteException {
        this.motor = motor;
    }

    
    @Override
    public void moveBackward(int speed) throws RemoteException {
        motor.moveBackward(speed);
    }

    @Override
    public void moveForward(int speed) throws RemoteException {
        motor.moveForward(speed);
    }

    @Override
    public void stop() throws RemoteException {
        motor.stop();
    }

    @Override
    public int getSpeed() throws RemoteException {
        return motor.getSpeed();
    }

    @Override
    public boolean isForward() throws RemoteException {
        return motor.isForward();
    }

    
}
