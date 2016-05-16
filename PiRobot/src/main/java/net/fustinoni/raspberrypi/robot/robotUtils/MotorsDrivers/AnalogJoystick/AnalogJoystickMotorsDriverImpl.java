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

package net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.AnalogJoystick;

import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.MotorsDriver;

/**
 *
 * @author efustinoni
 */
public class AnalogJoystickMotorsDriverImpl implements AnalogJoystickMotorsDriver {

    final MotorsDriver motors;
    final AnalogJoystickValuesToMotorsPower converter;
    
    public AnalogJoystickMotorsDriverImpl(final MotorsDriver motors, final AnalogJoystickValuesToMotorsPower converter) {
        this.motors = motors;
        this.converter = converter;
    }

    @Override
    public void stopMotors() {
        motors.stopMotors();
    }
    
    @Override
    public void jostickImput (int x, int y, int xMin, int xMax, int yMin, int yMax, int xCenter, int yCenter){

        System.out.println("Ingresso: " +  x+","+y + " ");
        x -= xMin;
        xCenter -= xMin;
        xMax -= xMin;
        y -= yMin;
        yCenter -= yMin;
        yMax -= yMin;
        System.out.println("Rettificati: " + x+","+y + " ");
        
        if (x==xCenter && y==yCenter){
            stopMotors();
            System.out.println("0,0");
        }
        else if (x==xCenter && y>yCenter){
            int fs = yGreaterThenCenter(y, yCenter, yMax);
            motors.setMotorsSpeeds(fs, fs);
            System.out.println(fs+","+fs);
        }
        else if (x==xCenter && y<yCenter){
            int fs = yLowerThenCenter(y, yCenter);
            motors.setMotorsSpeeds(fs, fs);
            System.out.println(fs+","+fs);
        }
        else if(x<xCenter && y==yCenter){
            int r = xLowerThenCenter(x, xCenter);
            motors.setMotorsSpeeds(-r, r);
            System.out.println("0," + r);
        }
        else if(x>xCenter && y==yCenter){
            int l = xGreaterThenCenter(x, xCenter, xMax);
            motors.setMotorsSpeeds( l, -l);
            System.out.println(l+",0");
        }
        else if(x<xCenter && y>yCenter){
            int fs = yGreaterThenCenter(y, yCenter, yMax);
            int tp = xLowerThenCenter(x, xCenter);
            
            int l = internalWheelSpeed(fs, tp);
            int r = fs;

            //int l = (y-yCenter)*100/(yMax-yCenter);
            //int d = l - (xCenter-x)*100/(xCenter-xMin);
            motors.setMotorsSpeeds(l, r);
            System.out.println(l+","+r + " tp:" + tp);
        }
        else if(x>xCenter && y>yCenter){
            int fs = yGreaterThenCenter(y, yCenter, yMax);
            int tp = xGreaterThenCenter(x, xCenter, xMax);
            int l = fs;
            int r = internalWheelSpeed(fs, tp);

            //int l = (y-yCenter)*100/(yMax-yCenter);
            //int d = l - (xCenter-x)*100/(xCenter-xMin);
            motors.setMotorsSpeeds(l, r);
            System.out.println(l+","+r + " tp:" + tp);
        }
        else if(x<xCenter && y<yCenter){
            int fs =  yLowerThenCenter(y, yCenter);
            int tp = xLowerThenCenter(x, xCenter);
            int l = internalWheelSpeed(fs, tp);
            int r = fs;

            motors.setMotorsSpeeds(l, r);
            System.out.println(l+","+r + " tp:" + tp);
        }
        else if(x>xCenter && y<yCenter){
            int fs = yLowerThenCenter(y, yCenter);
            int tp = xGreaterThenCenter(x, xCenter, xMax);
            int l = fs;
            int r = internalWheelSpeed(fs, tp);

            motors.setMotorsSpeeds(l, r);
            System.out.println(l+","+r + " tp:" + tp);
        }
        else{
            stopMotors();
            System.out.println("stop " + x + ", " + y);
        }
    }

    private int internalWheelSpeed(int fs, int tp) {
        return converter.internalWheelSpeed(fs, tp);
    }

    private int xGreaterThenCenter(int x, int xCenter, int xMax) {
        return converter.xGreaterThenCenter(x, xCenter, xMax);
    }

    private int xLowerThenCenter(int x, int xCenter) {
        return converter.xLowerThenCenter(x, xCenter);
    }

    private int yLowerThenCenter(int y, int yCenter) {
        return converter.yLowerThenCenter(y, yCenter);
    }

    private int yGreaterThenCenter(int y, int yCenter, int yMax) {
        return converter.yGreaterThenCenter(y, yCenter, yMax);
    }

}
