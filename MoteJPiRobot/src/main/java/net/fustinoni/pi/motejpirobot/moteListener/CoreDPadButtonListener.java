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
public interface CoreDPadButtonListener extends CoreButtonListener{
    
    @Override
    public void buttonPressed(CoreButtonEvent evt);
}
