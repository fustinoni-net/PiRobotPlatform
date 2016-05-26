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
public class ButtonCListener extends NunchukButtonListenerDecorator{

    
    public ButtonCListener(NunchukButtonListener listener, final long executeIfFiredAfterMillisendFromPreviousFire) {
        super(listener, executeIfFiredAfterMillisendFromPreviousFire);
    }

    @Override
    public void buttonPressed(NunchukButtonEvent evt) {
        if (!evt.isButtonCPressed()) return;
        super.buttonPressed(evt);
    }
    
}
