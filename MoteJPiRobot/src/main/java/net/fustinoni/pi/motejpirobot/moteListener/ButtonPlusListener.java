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
public class ButtonPlusListener extends CoreButtonListenerDecorator{

    
    public ButtonPlusListener(CoreButtonListener listener, final long executeIfFiredAfterMillisendFromPreviousFire) {
        super(listener, executeIfFiredAfterMillisendFromPreviousFire);
    }

    @Override
    public void buttonPressed(CoreButtonEvent evt) {
        if (!evt.isButtonPlusPressed()) return;
        super.buttonPressed(evt);
    }

}
