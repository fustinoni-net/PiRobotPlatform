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

package net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter;

import net.fustinoni.raspberryPi.robot.robotUtils.MotorsDrivers.AnalogJoystick.AnalogJoystickValuesToMotorsPower;

/**
 *
 * @author efustinoni
 */
public class StepperDecorator implements AnalogJoystickValuesToMotorsPower{
    
    final private AnalogJoystickValuesToMotorsPower converter;

    public StepperDecorator(AnalogJoystickValuesToMotorsPower converter) {
        this.converter = converter;
    }

    @Override
    public int xGreaterThenCenter(int x, int xCenter, int xMax) {
        
        int value = converter.xGreaterThenCenter(x, xCenter, xMax);
        
        return steppedValue(value);
    }

    @Override
    public int xLowerThenCenter(int x, int xCenter) {
        int value = converter.xLowerThenCenter(x, xCenter);
        return steppedValue(value);
    }

    @Override
    public int yLowerThenCenter(int y, int yCenter) {
        int value = converter.yLowerThenCenter(y, yCenter);
        return steppedValue(value);
    }

    @Override
    public int yGreaterThenCenter(int y, int yCenter, int yMax) {
        int value = converter.yGreaterThenCenter(y, yCenter, yMax);
        return steppedValue(value);
    }

    @Override
    public int internalWheelSpeed(int x, int y) {
        return steppedValue(converter.internalWheelSpeed(x, y));
    }

    private int steppedValue(int value) {
        int sign =  (int) Math.signum(value);
        int abs = Math.abs(value);
        
        int out = 0;
        
        if (abs < 10) return 0;
        else if (abs <= 10) out = 0;
        else if (abs <= 20) out = 15;
        else if (abs <= 30) out = 20;
        else if (abs <= 40) out = 30;
        else if (abs <= 50) out = 40;
        else if (abs <= 60) out = 50;
        else if (abs <= 70) out = 60;
        else if (abs <= 80) out = 70;
        else if (abs <= 90) out = 80;
        else if (abs <= 100) out = 95;
        else if (abs > 100) out = 100;
        
        return sign * out;
    }
}
