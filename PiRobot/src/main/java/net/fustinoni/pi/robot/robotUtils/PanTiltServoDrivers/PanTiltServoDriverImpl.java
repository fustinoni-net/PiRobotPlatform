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

package net.fustinoni.pi.robot.robotUtils.PanTiltServoDrivers;

import net.fustinoni.pi.robot.component.PanTiltServos;
import net.fustinoni.pi.robot.device.Servo;

/**
 *
 * @author efustinoni
 */
public class PanTiltServoDriverImpl implements PanTiltServoDriver{

    protected final Servo panServo;
    protected final Servo tiltServo;
    private final int minimumPan;
    private final int maximumPan;
    private final int minimumTilt;
    private final int maximumTilt;
    
    public PanTiltServoDriverImpl(final PanTiltServos robot, int minimumPan, int minimumTilt, int maximumPan, int maximumTilt) {
        panServo = robot.getPanServo();
        tiltServo = robot.getTiltServo();
        this.minimumPan = minimumPan;
        this.maximumPan = maximumPan;
        this.minimumTilt = minimumTilt;
        this.maximumTilt = maximumTilt;
    }

    
    @Override
    public void setTiltCenter() {
        tiltServo.setDegree(0);
    }

    @Override
    public void setPanCenter() {
        panServo.setDegree(0);
    }

    @Override
    public void setPanDegree(int degree) {
        panServo.setDegree(normalizePanImput(degree));
    }

    @Override
    public void setTiltDegree(int degree) {
        tiltServo.setDegree(normalizetiltImput(degree));
    }

    private int normalizePanImput(int degree){
        if (degree < minimumPan) return minimumPan;
        if (degree > maximumPan) return maximumPan;
        return degree;
    }

    private int normalizetiltImput(int degree){
        if (degree < minimumTilt) return minimumTilt;
        if (degree > maximumTilt) return maximumTilt;
        return degree;
    }

}
