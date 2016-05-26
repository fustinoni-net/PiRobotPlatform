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

package net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter;

import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.AnalogJoystickValuesToMotorsPower;

/**
 *
 * @author efustinoni
 */
public class LinearConverter implements AnalogJoystickValuesToMotorsPower{

    @Override
    public int xGreaterThenCenter(int x, int xCenter, int xMax) {
        return (x-xCenter)*100/(xMax-xCenter);
    }

    @Override
    public int xLowerThenCenter(int x, int xCenter) {
        return 100 -(100*x/xCenter);
    }

    @Override
    public int yLowerThenCenter(int y, int yCenter) {
        return -( 100-(100*y/yCenter));
    }

    @Override
    public int yGreaterThenCenter(int y, int yCenter, int yMax) {
        return (int) ((y-yCenter)*100/(float)(yMax-yCenter));
    }

    @Override
    public int internalWheelSpeed(int x, int y) {
        return x * (100-y)/100;
    }

}
