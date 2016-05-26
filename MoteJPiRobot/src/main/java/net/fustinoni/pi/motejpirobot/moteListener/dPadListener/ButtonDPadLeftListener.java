/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot.moteListener.dPadListener;

import motej.event.CoreButtonEvent;
import net.fustinoni.pi.motejpirobot.moteListener.CoreDPadButtonListener;

/**
 *
 * @author efustinoni
 */
public class ButtonDPadLeftListener implements CoreDPadButtonListener{

    CoreDPadButtonListener listener;
    
    public ButtonDPadLeftListener(CoreDPadButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public void buttonPressed(CoreButtonEvent evt) {
        if (!evt.isDPadLeftPressed()) return;
        
        listener.buttonPressed(evt);
    }

}
