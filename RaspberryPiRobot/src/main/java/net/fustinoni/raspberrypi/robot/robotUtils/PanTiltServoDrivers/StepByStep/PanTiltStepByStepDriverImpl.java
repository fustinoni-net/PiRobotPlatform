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

package net.fustinoni.raspberryPi.robot.robotUtils.PanTiltServoDrivers.StepByStep;

import net.fustinoni.raspberryPi.robot.robotUtils.PanTiltServoDrivers.PanTiltServoDriver;

/**
 *
 * @author efustinoni
 */
public class PanTiltStepByStepDriverImpl implements PanTiltStepByStepDriver{

    private final PanTiltServoDriver servos;
    private final int stepPam;
    private final int stepTilt;
    private int pamDegree;
    private int tiltDegree;

    public PanTiltStepByStepDriverImpl(PanTiltServoDriver servos, int stepPam, int stepTilt) {
        this.servos = servos;
        this.stepPam = stepPam;
        this.stepTilt = stepTilt;
    }
    
    
    
    @Override
    public void setTiltCenter() {
        this.tiltDegree = 0;
        servos.setTiltCenter();
    }

    @Override
    public void setPanCenter() {
        this.pamDegree = 0;
        servos.setPanCenter();
    }

    @Override
    public void setPanTiltCenter() {
        setPanCenter();
        setTiltCenter();
    }

    @Override
    public void moveLeft() {
        this.pamDegree -= this.stepPam;
        servos.setPanDegree(pamDegree);
    }

    @Override
    public void moveRight() {
        this.pamDegree += this.stepPam;
        servos.setPanDegree(pamDegree);
    }

    @Override
    public void moveUp() {
        this.tiltDegree += this.stepTilt;
        servos.setTiltDegree(tiltDegree);
    }

    @Override
    public void moveDown() {
        this.tiltDegree -= this.stepTilt;
        servos.setTiltDegree(tiltDegree);
    }
    
}
