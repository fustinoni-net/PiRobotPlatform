/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import net.fustinoni.pi.camjamek3.CJEK3Impl;
import static net.fustinoni.pi.motejpirobot.WiimotePiRobot.startWiimotePiRobot;
import net.fustinoni.pi.robot.BaseRobot;

/**
 *
 * @author efustinoni
 */
public class WiimoteCamJamEK3Robot {
    
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        BaseRobot piRobot = CJEK3Impl.getCJEK3Impl();
        startWiimotePiRobot(piRobot);
    }
}
