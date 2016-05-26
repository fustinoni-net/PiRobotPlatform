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
public class ButtonDPadUpListener implements CoreDPadButtonListener{

    CoreDPadButtonListener listener;
    
    public ButtonDPadUpListener(CoreDPadButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public void buttonPressed(CoreButtonEvent evt) {
        if (!evt.isDPadUpPressed()) return;
        listener.buttonPressed(evt);
    }

}
