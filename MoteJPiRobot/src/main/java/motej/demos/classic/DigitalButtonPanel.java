/*
 * Copyright 2008 motej
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package motej.demos.classic;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import motejx.extensions.classic.ClassicController;
import motejx.extensions.classic.ClassicControllerButtonEvent;
import motejx.extensions.classic.ClassicControllerButtonListener;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class DigitalButtonPanel implements ClassicControllerButtonListener {

	private JPanel panel;
	
	private JTextField noButton = new JTextField("No Button");
	
	private JTextField buttonX = new JTextField("X");
	
	private JTextField buttonY = new JTextField("Y");
	
	private JTextField buttonA = new JTextField("A");
	
	private JTextField buttonB = new JTextField("B");
	
	private JTextField buttonZL = new JTextField("ZL");
	
	private JTextField buttonZR = new JTextField("ZR");
	
	private JTextField buttonMinus = new JTextField("- / Select");
	
	private JTextField buttonPlus = new JTextField("+ / Start");
	
	private JTextField buttonHome = new JTextField("Home");
	
	private JTextField triggerLeft = new JTextField("Trigger-L");
	
	private JTextField triggerRight = new JTextField("Trigger-R");
	
	private JTextField dPadLeft = new JTextField("DPad Left");
	
	private JTextField dPadRight = new JTextField("DPad Right");
	
	private JTextField dPadUp = new JTextField("DPad Up");
	
	private JTextField dPadDown = new JTextField("DPad Down");
	
	public DigitalButtonPanel(ClassicController cc) {
		cc.addClassicControllerButtonListener(this);
		
		panel = new JPanel(new GridLayout(8,2));
		panel.add(noButton);
		panel.add(buttonX);
		panel.add(buttonY);
		panel.add(buttonA);
		panel.add(buttonB);
		panel.add(buttonPlus);
		panel.add(buttonMinus);
		panel.add(buttonHome);
		panel.add(buttonZL);
		panel.add(buttonZR);
		panel.add(triggerLeft);
		panel.add(triggerRight);
		panel.add(dPadLeft);
		panel.add(dPadRight);
		panel.add(dPadUp);
		panel.add(dPadDown);
	}

	public void buttonPressed(final ClassicControllerButtonEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
				buttonX.setEnabled(evt.isButtonXPressed());
				buttonY.setEnabled(evt.isButtonYPressed());
				buttonA.setEnabled(evt.isButtonAPressed());
				buttonB.setEnabled(evt.isButtonBPressed());
				buttonZL.setEnabled(evt.isButtonZLPressed());
				buttonZR.setEnabled(evt.isButtonZRPressed());
				buttonPlus.setEnabled(evt.isButtonPlusPressed());
				buttonMinus.setEnabled(evt.isButtonMinusPressed());
				buttonHome.setEnabled(evt.isButtonHomePressed());
				triggerLeft.setEnabled(evt.isButtonLeftTriggerPressed());
				triggerRight.setEnabled(evt.isButtonRightTriggerPressed());
				dPadLeft.setEnabled(evt.isDPadLeftPressed());
				dPadRight.setEnabled(evt.isDPadRightPressed());
				dPadUp.setEnabled(evt.isDPadUpPressed());
				dPadDown.setEnabled(evt.isDPadDownPressed());
				noButton.setEnabled(evt.isNoButtonPressed());
			}
		});
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
