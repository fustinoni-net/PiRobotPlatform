/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.robotRMI.listener;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fustinoni.pi.robot.listener.UltraSoundSensorListener;

/**
 *
 * @author efustinoni
 */
public class UltraSoundSensorListenerLocal implements UltraSoundSensorListener{

    private final UltraSoundSensorListenerRemote listener;

    public UltraSoundSensorListenerLocal(UltraSoundSensorListenerRemote listener) {
        this.listener = listener;
    }

    @Override
    public void tick(long distance) {
        try {
            listener.tick(distance);
        } catch (RemoteException ex) {
            Logger.getLogger(UltraSoundSensorListenerLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
