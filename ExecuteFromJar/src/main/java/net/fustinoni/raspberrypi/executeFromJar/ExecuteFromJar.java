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

package net.fustinoni.raspberryPi.executeFromJar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author efustinoni
 */
public class ExecuteFromJar {

    private ExecuteFromJar() {
    }
    
    public static String executeFromClasspath(String path, String args) throws IOException, InterruptedException {
        Path inputPath = Paths.get(path);

        if (!inputPath.isAbsolute()) {
                throw new IllegalArgumentException("The path has to be absolute, but found: " + inputPath);
        }

        String fileName = inputPath.getFileName().toString();

        Path target = Files.createTempFile(fileName, null);
        File targetFile = target.toFile();

        targetFile.deleteOnExit();

        try (InputStream source = ExecuteFromJar.class.getResourceAsStream(inputPath.toString())) {
            if (source == null) {
                    throw new FileNotFoundException("File " + inputPath + " was not found in classpath.");
            }
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }

        if (!targetFile.setExecutable(true, false)){
            System.out.println("Unable to set executable permission");
            System.exit(-1);
        }

        execute(target.toAbsolutePath().toString(), args)  ;

        return target.getFileName().toString();
    }
    
    public static int execute(String command, String args) throws IOException, InterruptedException {
        Process p;

        p = Runtime.getRuntime().exec(command + " " + args);
        p.waitFor();
        
        return p.exitValue();
    }
}
