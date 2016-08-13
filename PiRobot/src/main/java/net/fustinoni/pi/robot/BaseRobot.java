/**
 * 
 * **********************************************************************
 * This file is part of the PI2GO java library project. 
 *
 * More information about this project can be found here:  
 *   http://robots.fustinoni.net
 * **********************************************************************
 * 
 * Copyright (C) 2015 Enrico Fustinoni
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * 
 **/

package net.fustinoni.pi.robot;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinAnalog;
import com.pi4j.io.gpio.GpioPinAnalogInput;
import com.pi4j.io.gpio.GpioPinAnalogOutput;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinInput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.GpioPinShutdown;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import com.pi4j.wiringpi.Gpio;
import java.util.Collection;
import net.fustinoni.pi.robot.component.RobotGPIO;

/**
 *
 * @author efustinoni
 */
public abstract class BaseRobot implements PiRobot {
    // create gpio controller
    protected final GpioController gpio = GpioFactory.getInstance();

    public BaseRobot() {
        setSafeGPIOPinStart();
        
    }

    protected abstract void setSafeGPIOPinStart();

    protected final GpioController getGPIO() {
        return this.gpio;
    }
    
    
    protected final RobotGPIO getRobotGPIO(){
        return new RobotGpioImpl(gpio);
    }
    
    private class RobotGpioImpl implements RobotGPIO, GpioController {

        private final GpioController delegate;

        protected RobotGpioImpl(GpioController delegate) {
            this.delegate = delegate;
        }

        @Override
        public Collection<GpioPin> getProvisionedPins() {
            return delegate.getProvisionedPins();
        }

        @Override
        public GpioPin provisionPin(GpioProvider provider, Pin pin, PinMode mode) {
            return delegate.provisionPin(provider, pin, mode);
        }

        @Override
        public GpioPin provisionPin(GpioProvider provider, Pin pin, String name, PinMode mode) {
            return delegate.provisionPin(provider, pin, name, mode);
        }

        @Override
        public GpioPin provisionPin(GpioProvider provider, Pin pin, String name, PinMode mode, PinState defaultState) {
            return delegate.provisionPin(provider, pin, name, mode, defaultState);
        }

        @Override
        public GpioPin provisionPin(Pin pin, String name, PinMode mode) {
            return delegate.provisionPin(pin, name, mode);
        }

        @Override
        public GpioPin provisionPin(Pin pin, PinMode mode) {
            return delegate.provisionPin(pin, mode);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode) {
            return delegate.provisionDigitalMultipurposePin(provider, pin, name, mode);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode) {
            return delegate.provisionDigitalMultipurposePin(provider, pin, mode);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode) {
            return delegate.provisionDigitalMultipurposePin(pin, name, mode);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode) {
            return delegate.provisionDigitalMultipurposePin(pin, mode);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode, PinPullResistance resistance) {
            return delegate.provisionDigitalMultipurposePin(provider, pin, mode, resistance);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode, PinPullResistance resistance) {
            return delegate.provisionDigitalMultipurposePin(provider, pin, name, mode, resistance);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode, PinPullResistance resistance) {
            return delegate.provisionDigitalMultipurposePin(pin, name, mode, resistance);
        }

        @Override
        public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode, PinPullResistance resistance) {
            return delegate.provisionDigitalMultipurposePin(pin, mode, resistance);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name) {
            return delegate.provisionDigitalInputPin(provider, pin, name);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin) {
            return delegate.provisionDigitalInputPin(provider, pin);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name) {
            return delegate.provisionDigitalInputPin(pin, name);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(Pin pin) {
            return delegate.provisionDigitalInputPin(pin);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, PinPullResistance resistance) {
            return delegate.provisionDigitalInputPin(provider, pin, resistance);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name, PinPullResistance resistance) {
            return delegate.provisionDigitalInputPin(provider, pin, name, resistance);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name, PinPullResistance resistance) {
            return delegate.provisionDigitalInputPin(pin, name, resistance);
        }

        @Override
        public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, PinPullResistance resistance) {
            return delegate.provisionDigitalInputPin(pin, resistance);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name) {
            return delegate.provisionDigitalOutputPin(provider, pin, name);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin) {
            return delegate.provisionDigitalOutputPin(provider, pin);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name) {
            return delegate.provisionDigitalOutputPin(pin, name);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin) {
            return delegate.provisionDigitalOutputPin(pin);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, PinState defaultState) {
            return delegate.provisionDigitalOutputPin(provider, pin, defaultState);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name, PinState defaultState) {
            return delegate.provisionDigitalOutputPin(provider, pin, name, defaultState);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name, PinState defaultState) {
            return delegate.provisionDigitalOutputPin(pin, name, defaultState);
        }

        @Override
        public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, PinState defaultState) {
            return delegate.provisionDigitalOutputPin(pin, defaultState);
        }

        @Override
        public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin, String name) {
            return delegate.provisionAnalogInputPin(provider, pin, name);
        }

        @Override
        public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin) {
            return delegate.provisionAnalogInputPin(provider, pin);
        }

        @Override
        public GpioPinAnalogInput provisionAnalogInputPin(Pin pin, String name) {
            return delegate.provisionAnalogInputPin(pin, name);
        }

        @Override
        public GpioPinAnalogInput provisionAnalogInputPin(Pin pin) {
            return delegate.provisionAnalogInputPin(pin);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name) {
            return delegate.provisionAnalogOutputPin(provider, pin, name);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin) {
            return delegate.provisionAnalogOutputPin(provider, pin);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name) {
            return delegate.provisionAnalogOutputPin(pin, name);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin) {
            return delegate.provisionAnalogOutputPin(pin);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, double defaultValue) {
            return delegate.provisionAnalogOutputPin(provider, pin, defaultValue);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name, double defaultValue) {
            return delegate.provisionAnalogOutputPin(provider, pin, name, defaultValue);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name, double defaultValue) {
            return delegate.provisionAnalogOutputPin(pin, name, defaultValue);
        }

        @Override
        public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, double defaultValue) {
            return delegate.provisionAnalogOutputPin(pin, defaultValue);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name) {
            return delegate.provisionPwmOutputPin(provider, pin, name);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin) {
            return delegate.provisionPwmOutputPin(provider, pin);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name) {
            return delegate.provisionPwmOutputPin(pin, name);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(Pin pin) {
            return delegate.provisionPwmOutputPin(pin);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, int defaultValue) {
            return delegate.provisionPwmOutputPin(provider, pin, defaultValue);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name, int defaultValue) {
            return delegate.provisionPwmOutputPin(provider, pin, name, defaultValue);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name, int defaultValue) {
            return delegate.provisionPwmOutputPin(pin, name, defaultValue);
        }

        @Override
        public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, int defaultValue) {
            return delegate.provisionPwmOutputPin(pin, defaultValue);
        }

        @Override
        public void unprovisionPin(GpioPin... pin) {
            delegate.unprovisionPin(pin);
        }

        @Override
        public void setShutdownOptions(GpioPinShutdown options, GpioPin... pin) {
            delegate.setShutdownOptions(options, pin);
        }

        @Override
        public void setShutdownOptions(Boolean unexport, GpioPin... pin) {
            delegate.setShutdownOptions(unexport, pin);
        }

        @Override
        public void setShutdownOptions(Boolean unexport, PinState state, GpioPin... pin) {
            delegate.setShutdownOptions(unexport, state, pin);
        }

        @Override
        public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, GpioPin... pin) {
            delegate.setShutdownOptions(unexport, state, resistance, pin);
        }

        @Override
        public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, PinMode mode, GpioPin... pin) {
            delegate.setShutdownOptions(unexport, state, resistance, mode, pin);
        }

        /**
         * This method returns TRUE if the GPIO controller has been shutdown.
         *
         * @return shutdown state
         */
        @Override
        public boolean isShutdown() {
            return delegate.isShutdown();
        }

        /**
         * This method can be called to forcefully shutdown all GPIO controller
         * monitoring, listening, and task threads/executors.
         */
        @Override
        public synchronized void shutdown() {
            delegate.shutdown();
        }

        @Override
        public void export(PinMode mode, PinState defaultState, GpioPin... pin) {
            delegate.export(mode, defaultState, pin);
        }

        @Override
        public void export(PinMode mode, GpioPin... pin) {
            delegate.export(mode, pin);
        }

        @Override
        public boolean isExported(GpioPin... pin) {
            return delegate.isExported(pin);
        }

        @Override
        public void unexport(GpioPin... pin) {
            delegate.unexport(pin);
        }

        @Override
        public void unexportAll() {
            delegate.unexportAll();
        }

        @Override
        public void setMode(PinMode mode, GpioPin... pin) {
            delegate.setMode(mode, pin);
        }

        @Override
        public PinMode getMode(GpioPin pin) {
            return delegate.getMode(pin);
        }

        @Override
        public boolean isMode(PinMode mode, GpioPin... pin) {
            return delegate.isMode(mode, pin);
        }

        @Override
        public void setPullResistance(PinPullResistance resistance, GpioPin... pin) {
            delegate.setPullResistance(resistance, pin);
        }

        @Override
        public PinPullResistance getPullResistance(GpioPin pin) {
            return delegate.getPullResistance(pin);
        }

        @Override
        public boolean isPullResistance(PinPullResistance resistance, GpioPin... pin) {
            return delegate.isPullResistance(resistance, pin);
        }

        @Override
        public void high(GpioPinDigitalOutput... pin) {
            delegate.high(pin);
        }

        @Override
        public boolean isHigh(GpioPinDigital... pin) {
            return delegate.isHigh(pin);
        }

        @Override
        public void low(GpioPinDigitalOutput... pin) {
            delegate.low(pin);
        }

        @Override
        public boolean isLow(GpioPinDigital... pin) {
            return delegate.isLow(pin);
        }

        @Override
        public void setState(PinState state, GpioPinDigitalOutput... pin) {
            delegate.setState(state, pin);
        }

        @Override
        public void setState(boolean state, GpioPinDigitalOutput... pin) {
            delegate.setState(state, pin);
        }

        @Override
        public boolean isState(PinState state, GpioPinDigital... pin) {
            return delegate.isState(state, pin);
        }

        @Override
        public PinState getState(GpioPinDigital pin) {
            return delegate.getState(pin);
        }

        @Override
        public void toggle(GpioPinDigitalOutput... pin) {
            delegate.toggle(pin);
        }

        @Override
        public void pulse(long milliseconds, GpioPinDigitalOutput... pin) {
            delegate.pulse(milliseconds, pin);
        }

        @Override
        public void setValue(double value, GpioPinAnalogOutput... pin) {
            delegate.setValue(value, pin);
        }

        @Override
        public double getValue(GpioPinAnalog pin) {
            return delegate.getValue(pin);
        }

        @Override
        public void addListener(GpioPinListener listener, GpioPinInput... pin) {
            delegate.addListener(listener, pin);
        }

        @Override
        public void addListener(GpioPinListener[] listeners, GpioPinInput... pin) {
            delegate.addListener(listeners, pin);
        }

        @Override
        public void removeListener(GpioPinListener listener, GpioPinInput... pin) {
            delegate.removeListener(listener, pin);
        }

        @Override
        public void removeListener(GpioPinListener[] listeners, GpioPinInput... pin) {
            delegate.removeListener(listeners, pin);
        }

        @Override
        public void removeAllListeners() {
            delegate.removeAllListeners();
        }

        @Override
        public void addTrigger(GpioTrigger trigger, GpioPinInput... pin) {
            delegate.addTrigger(trigger, pin);
        }

        @Override
        public void addTrigger(GpioTrigger[] triggers, GpioPinInput... pin) {
            delegate.addTrigger(triggers, pin);
        }

        @Override
        public void removeTrigger(GpioTrigger trigger, GpioPinInput... pin) {
            delegate.removeTrigger(trigger, pin);
        }

        @Override
        public void removeTrigger(GpioTrigger[] triggers, GpioPinInput... pin) {
            delegate.removeTrigger(triggers, pin);
        }

        @Override
        public void removeAllTriggers() {
            delegate.removeAllTriggers();
        }

        @Override
        public boolean equals(Object o) {
            Object target = o;
            if (o instanceof RobotGpioImpl) {
                target = ((RobotGpioImpl) o).delegate;
            }
            return this.delegate.equals(target);
        }

        @Override
        public int hashCode() {
            return this.delegate.hashCode();
        }

        @Override
        public void unexport(Pin... pins) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider gp, Pin pin, String string, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider gp, Pin pin, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider gp, Pin pin, String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(GpioProvider gp, Pin pin) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, String string, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin, String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPinPwmOutput provisionSoftPwmOutputPin(Pin pin) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPin getProvisionedPin(Pin pin) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public GpioPin getProvisionedPin(String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    
}
