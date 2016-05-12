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
import net.fustinoni.raspberryPi.robotRMI.listener.SwitchListenerRemoteImpl;
import net.fustinoni.raspberryPi.robot.listener.SwitchListener;
import net.fustinoni.raspberryPi.robot.sensor.Switch;

/**
 *
 * @author efustinoni
 */
public class SwitchLocal implements Switch{
    private final SwitchRemote s;

    public SwitchLocal(final SwitchRemote s) {
        this.s = s;
    }

    @Override
    public void addListener(SwitchListener listener) {
        try {
            s.addListener(new SwitchListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(SwitchLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public long getLastPressionMillisec() {
        try {
            return s.getLastPressionMillisec();
        } catch (RemoteException ex) {
            Logger.getLogger(SwitchLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public boolean isPushed() {
        try {
            return s.isPushed();
        } catch (RemoteException ex) {
            Logger.getLogger(SwitchLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void removeListener(SwitchListener listener) {
        try {
            s.removeListener(new SwitchListenerRemoteImpl(listener));
        } catch (RemoteException ex) {
            Logger.getLogger(SwitchLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
