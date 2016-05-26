/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot.moteListener;

import motejx.extensions.nunchuk.NunchukButtonEvent;
import motejx.extensions.nunchuk.NunchukButtonListener;

/**
 *
 * @author efustinoni
 */
public abstract class NunchukButtonListenerDecorator implements NunchukButtonListener{
    
    NunchukButtonListener listener;
    final long timeToValid;
    
    long previousFire;
    
    public NunchukButtonListenerDecorator(final NunchukButtonListener listener, final long executeIfFiredAfterMillisendFromPreviousFire) {
        this.listener = listener;
        this.timeToValid = executeIfFiredAfterMillisendFromPreviousFire;
    }

    @Override
    public void buttonPressed(NunchukButtonEvent evt) {
        
        long now = System.currentTimeMillis();
        
        if ( now - previousFire < timeToValid) return;
        
        previousFire = now;
        
        listener.buttonPressed(evt);
    }

}
