/*
 * Copyright 2007-2008 Volker Fritzsch
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
package motej.demos.nunchuk;

import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
@SuppressWarnings("serial")
public class AccelerometerPanel extends JPanel {

	private JSlider sliderX = new JSlider(JSlider.VERTICAL, 0, 10, 5);
	
	private JSlider sliderY = new JSlider(JSlider.VERTICAL, 0, 10, 5);
	
	private JSlider sliderZ = new JSlider(JSlider.VERTICAL, 0, 10, 5);
	
	public AccelerometerPanel() {
		super();
		
		setLayout(new FlowLayout());
	}
	
	public void accelerometerValueChanged(int x, int y, int z) {
		sliderX.setValue(x);
		sliderY.setValue(y);
		sliderZ.setValue(z);
	}

	public void setCalibrationDataX(int zeroForce, int earthGravityForce) {
		sliderX.setMinimum(0);
		sliderX.setMaximum(earthGravityForce * 2);
		
		Dictionary<Integer, JComponent> labels = new Hashtable<Integer, JComponent>();
		labels.put(zeroForce, new JLabel("0 G"));
		labels.put(earthGravityForce, new JLabel("1 G"));
		sliderX.setLabelTable(labels);
		sliderX.setPaintTrack(true);
		sliderX.setPaintTicks(true);
		sliderX.setPaintLabels(true);
		
		sliderX.setBorder(BorderFactory.createTitledBorder("X"));
		add(sliderX);
		
		repaint();
	}

	public void setCalibrationDataY(int zeroForce, int earthGravityForce) {
		sliderY.setMinimum(0);
		sliderY.setMaximum(earthGravityForce * 2);

		Dictionary<Integer, JComponent> labels = new Hashtable<Integer, JComponent>();
		labels.put(zeroForce, new JLabel("0 G"));
		labels.put(earthGravityForce, new JLabel("1 G"));
		sliderY.setLabelTable(labels);
		sliderY.setPaintTrack(true);
		sliderY.setPaintTicks(true);
		sliderY.setPaintLabels(true);

		sliderY.setBorder(BorderFactory.createTitledBorder("Y"));
		add(sliderY);
		
		repaint();
	}

	public void setCalibrationDataZ(int zeroForce, int earthGravityForce) {
		sliderZ.setMinimum(0);
		sliderZ.setMaximum(earthGravityForce * 2);	

		Dictionary<Integer, JComponent> labels = new Hashtable<Integer, JComponent>();
		labels.put(zeroForce, new JLabel("0 G"));
		labels.put(earthGravityForce, new JLabel("1 G"));
		sliderZ.setLabelTable(labels);
		sliderZ.setPaintTrack(true);
		sliderZ.setPaintTicks(true);
		sliderZ.setPaintLabels(true);

		sliderZ.setBorder(BorderFactory.createTitledBorder("Z"));
		add(sliderZ);
		
		repaint();
	}
}
