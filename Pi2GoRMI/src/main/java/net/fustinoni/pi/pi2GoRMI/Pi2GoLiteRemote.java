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

package net.fustinoni.pi.pi2GoRMI;

import net.fustinoni.pi.robotRMI.BaseRobotRemote;
import net.fustinoni.pi.robotRMI.component.FrontalUltraSoundSensorRemote;
import net.fustinoni.pi.robotRMI.component.FrontLedsRemote;
import net.fustinoni.pi.robotRMI.component.GenericSwitchRemote;
import net.fustinoni.pi.robotRMI.component.LeftRightMotorsRemote;
import net.fustinoni.pi.robotRMI.component.LineFollowareTwoSensorRemote;
import net.fustinoni.pi.robotRMI.component.PanTiltServosRemote;
import net.fustinoni.pi.robotRMI.component.RearLedsRemote;
import net.fustinoni.pi.robotRMI.sensor.SideIRSensorsRemote;

/**
 *
 * @author efustinoni
 */
public interface Pi2GoLiteRemote extends  
        LeftRightMotorsRemote, SideIRSensorsRemote,
        GenericSwitchRemote, FrontLedsRemote, RearLedsRemote,
        BaseRobotRemote, LineFollowareTwoSensorRemote, PanTiltServosRemote,
        FrontalUltraSoundSensorRemote {
  
}
