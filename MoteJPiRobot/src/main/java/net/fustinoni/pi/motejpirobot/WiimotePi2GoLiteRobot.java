/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import static net.fustinoni.pi.motejpirobot.WiimotePiRobot.startWiimotePiRobot;
import net.fustinoni.pi.pi2Go.Pi2GoLiteImpl;
import net.fustinoni.pi.robot.BaseRobot;

/**
 *
 * @author efustinoni
 */
public class WiimotePi2GoLiteRobot {


    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {

        BaseRobot piRobot = Pi2GoLiteImpl.getPi2GoLite();
        startWiimotePiRobot(piRobot);
    }
    
}
