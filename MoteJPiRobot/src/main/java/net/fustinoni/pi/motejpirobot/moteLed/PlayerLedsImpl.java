/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fustinoni.pi.motejpirobot.moteLed;

import motej.Mote;

/**
 *
 * @author efustinoni
 */
public class PlayerLedsImpl implements PlayerLeds{
    
    private final Mote mote;
    
    private final boolean [] ledsStatus = new boolean[] {false, false, false, false};

    public PlayerLedsImpl(final Mote mote) {
        this.mote = mote;
        setLed();
    }

    @Override
    public void player1LedOn() {
        ledsStatus[0] = true;
        setLed();
    }

    @Override
    public void player1LedOff() {
        ledsStatus[0] = false;
        setLed();
    }

    @Override
    public void player2LedOn() {
        ledsStatus[1] = true;
        setLed();
    }

    @Override
    public void player2LedOff() {
        ledsStatus[1] = false;
        setLed();
    }

    @Override
    public void player3LedOn() {
        ledsStatus[2] = true;
        setLed();
    }

    @Override
    public void player3LedOff() {
        ledsStatus[2] = false;
        setLed();
    }

    @Override
    public void player4LedOn() {
        ledsStatus[3] = true;
        setLed();
    }

    @Override
    public void player4LedOff() {
        ledsStatus[3] = false;
        setLed();
    }

    private void setLed() {
        mote.setPlayerLeds(ledsStatus);
    }


}
