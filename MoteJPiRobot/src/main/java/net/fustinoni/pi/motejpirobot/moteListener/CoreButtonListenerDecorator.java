/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot.moteListener;

import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;

/**
 *
 * @author efustinoni
 */
public abstract class CoreButtonListenerDecorator implements CoreButtonListener{
    
    CoreButtonListener listener;
    final long timeToValid;
    
    long previousFire;
    
    protected CoreButtonListenerDecorator(final long executeIfFiredAfterMillisendFromPreviousFire) {
        timeToValid = executeIfFiredAfterMillisendFromPreviousFire;
    }
    
    
    public CoreButtonListenerDecorator(final CoreButtonListener listener, final long executeIfFiredAfterMillisendFromPreviousFire) {
        this.listener = listener;
        this.timeToValid = executeIfFiredAfterMillisendFromPreviousFire;
    }

    @Override
    public void buttonPressed(CoreButtonEvent evt) {
        
        long now = System.currentTimeMillis();
        
        if ( now - previousFire < timeToValid) return;
        
        previousFire = now;
        
        listener.buttonPressed(evt);
    }

}
