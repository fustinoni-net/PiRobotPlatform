/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import net.fustinoni.pi.pi2Go.Pi2GoLite;
import net.fustinoni.pi.pi2GoRMI.Pi2GoLiteLocal;
import net.fustinoni.pi.pi2GoRMI.Pi2GoLiteRemoteImpl;

/**
 *
 * @author efustinoni
 */
public class RMIExample {
 
    public static void main(String[] args) throws Exception {

        Runnable remoteRobot = () -> { 
            try {
                Pi2GoLiteRemoteImpl.main(args);
                System.out.println("Server pronto");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        };

        new Thread(remoteRobot).start();
        
        Thread.sleep(6000);
        
        Pi2GoLite piLocal = Pi2GoLiteLocal.getPi2GoLiteLocat("127.0.0.1");
        
        piLocal.getFrontLeds().turnOn();
        Thread.sleep(1000);
        piLocal.getFrontLeds().turnOff();

        System.exit(0);
    }
}
