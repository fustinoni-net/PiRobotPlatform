/**
 * 
 * **********************************************************************
 * This file is part of the PiRobotPlatform java library project. 
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

package net.fustinoni.pi.robotWebControl;

import net.fustinoni.pi.robot.component.FrontLeds;
import net.fustinoni.pi.robot.component.RearLeds;
import net.fustinoni.pi.robot.component.SideIRSensors;
import static net.fustinoni.pi.robotWebControl.RobotDriver.chaufer;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 *
 * @author efustinoni
 */

@WebSocket
public class RobotWebSocketHandler {

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception 
    {
        System.out.println("Connected " + user.getRemoteAddress().getHostString() );
        if (RobotDriver.master == null) RobotDriver.master = user;
        RobotDriver.userSet.add(user);

        if (RobotDriver.robot instanceof SideIRSensors){
            RobotDriver.broadcastRightIRSensorMessage(((SideIRSensors)RobotDriver.robot).getRightIRSensor().isTriggered());
            RobotDriver.broadcastLeftIRSensorMessage(((SideIRSensors)RobotDriver.robot).getLeftIRSensor().isTriggered());
        }

    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        
        System.out.println("Closed " + user.getRemoteAddress().getHostString() + " statusCode " + statusCode + " reason " + reason );


        if (user.equals(RobotDriver.master)){
            //RobotDriver.exit();

            RobotDriver.turnAllOff();
            RobotDriver.master = null;
        }

        RobotDriver.userSet.remove(user);
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {

        if (RobotDriver.master != null && user.equals(RobotDriver.master)){
            if (!message.equals("pong")) System.out.println("User " + user.getRemoteAddress().getHostString() + " message " + message );
        }
        else{
            if (!message.equals("pong")) System.out.println("User " + user.getRemoteAddress().getHostString() + " Not authorized." );
            return;
        }

        if (message.startsWith("M")){
            //M:x=0,y=0            
            int x = Integer.valueOf(message.substring(4, message.indexOf(",")));
            int y = Integer.valueOf(message.substring(message.indexOf("y=")+2));

            if (chaufer != null) chaufer.jostickImput(-x, y, -100, 100, -100, 100, 0, 0);
        }else if (message.equals("pong")){
            RobotDriver.pong = true;
        }
        else if (message.startsWith("btn")){

            if (message.equals("btn:off"))    RobotDriver.shutDown();
            if (message.equals("btn:left"))   if (RobotDriver.camera!= null) RobotDriver.camera.moveLeft();
            if (message.equals("btn:right"))  if (RobotDriver.camera!= null) RobotDriver.camera.moveRight();
            if (message.equals("btn:up"))     if (RobotDriver.camera!= null) RobotDriver.camera.moveUp();
            if (message.equals("btn:down"))   if (RobotDriver.camera!= null) RobotDriver.camera.moveDown();
            if (message.equals("btn:center")) if (RobotDriver.camera!= null) RobotDriver.camera.setPanTiltCenter();
            if (message.equals("btn:a"))      if (RobotDriver.robot instanceof FrontLeds) ((FrontLeds) RobotDriver.robot).getFrontLeds().toggle();
            if (message.equals("btn:b"))      if (RobotDriver.robot instanceof RearLeds) ((RearLeds) RobotDriver.robot).getRearLeds().toggle();
            if (message.equals("btn:c")) ;
            if (message.equals("btn:d")) ;

        }
    }
}
