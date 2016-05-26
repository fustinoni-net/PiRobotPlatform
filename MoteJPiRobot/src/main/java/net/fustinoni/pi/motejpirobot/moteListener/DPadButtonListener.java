/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot.moteListener;

import java.util.HashSet;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadDownListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadLeftListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadRightListener;
import net.fustinoni.pi.motejpirobot.moteListener.dPadListener.ButtonDPadUpListener;

/**
 *
 * @author efustinoni
 */
public class DPadButtonListener implements CoreButtonListener {
    
    HashSet<ButtonDPadDownListener>  downListener = new HashSet<>();
    HashSet<ButtonDPadUpListener> upListener = new HashSet<>();
    HashSet<ButtonDPadLeftListener> leftListener = new HashSet<>();
    HashSet<ButtonDPadRightListener> rightListener = new HashSet<>();
    
    final long timeToValid;
    long previousFire;
    
    
    public DPadButtonListener(final long executeIfFiredAfterMillisendFromPreviousFire) {
        timeToValid = executeIfFiredAfterMillisendFromPreviousFire;
    }

    @Override
    public void buttonPressed(CoreButtonEvent evt) {
        
        if (!(evt.isDPadDownPressed()  ||
              evt.isDPadLeftPressed()  ||
              evt.isDPadRightPressed() ||
              evt.isDPadUpPressed() )
            )return;
        
        long now = System.currentTimeMillis();
        
        if ( now - previousFire < timeToValid) return;
        
        previousFire = now;
        if (evt.isDPadUpPressed())
            for(ButtonDPadUpListener listener : upListener)
                listener.buttonPressed(evt);
        
        if (evt.isDPadDownPressed())
            for(ButtonDPadDownListener listener : downListener)
                listener.buttonPressed(evt);
        
        if (evt.isDPadLeftPressed())
            for(ButtonDPadLeftListener listener : leftListener)
                listener.buttonPressed(evt);
        
        if (evt.isDPadRightPressed())
            for(ButtonDPadRightListener listener : rightListener)
                listener.buttonPressed(evt);

    }
    
    public void addButtonDPadDownListener(ButtonDPadDownListener listener){
        downListener.add(listener);
    }
    
    public void removeButtonDPadDownListener(ButtonDPadDownListener listener){
        downListener.remove(listener);
    }

    public void addButtonDPadUpListener(ButtonDPadUpListener listener){
        upListener.add(listener);
    }
    
    public void removeButtonDPadUpListener(ButtonDPadUpListener listener){
        upListener.remove(listener);
    }
    
    public void addButtonDPadLeftListener(ButtonDPadLeftListener listener){
        leftListener.add(listener);
    }
    
    public void removeButtonDPadLeftListener(ButtonDPadLeftListener listener){
        leftListener.remove(listener);
    }
    
    public void addButtonDPadRightListener(ButtonDPadRightListener listener){
        rightListener.add(listener);
    }
    
    public void removeButtonDPadRightListener(ButtonDPadRightListener listener){
        rightListener.remove(listener);
    }
}
