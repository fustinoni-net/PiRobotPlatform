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

package net.fustinoni.pi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author efustinoni
 */
public class ShutDown {
    
    private static void shutDown(String delay){
    
            try {
                Process p = Runtime.getRuntime().exec("shutdown -h -P " + delay);
                p.waitFor();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine())!= null) {
                        System.out.println(line);
                    }
                    reader.close();
                }
                p.getInputStream().close();
            } catch (IOException | InterruptedException ex) {
                System.out.println(ex);
            }
    }

    public  static void shutDownNow(){
        shutDown("now");
    }

    public  static void timedShutDownNow(long delay){
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    shutDown("now");        
                }
            },
            delay
        );
    }
}
