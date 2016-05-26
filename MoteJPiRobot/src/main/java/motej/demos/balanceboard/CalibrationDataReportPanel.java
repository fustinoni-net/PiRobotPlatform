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
package motej.demos.balanceboard;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import motej.CalibrationDataReport;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class CalibrationDataReportPanel {

	private JPanel panel;
	
	private JLabel gravityXLabel = new JLabel("Gravity X");
	private JLabel gravityYLabel = new JLabel("Gravity Y");
	private JLabel gravityZLabel = new JLabel("Gravity Z");
	private JLabel zeroXLabel = new JLabel("Zero X");
	private JLabel zeroYLabel = new JLabel("Zero Y");
	private JLabel zeroZLabel = new JLabel("Zero Z");
	
	private JTextField gravityXText = new JTextField();
	private JTextField gravityYText = new JTextField();
	private JTextField gravityZText = new JTextField();
	private JTextField zeroXText = new JTextField();
	private JTextField zeroYText = new JTextField();
	private JTextField zeroZText = new JTextField();
	
	public CalibrationDataReportPanel(CalibrationDataReport report) {
		gravityXText.setEnabled(false);
		gravityYText.setEnabled(false);
		gravityZText.setEnabled(false);
		zeroXText.setEnabled(false);
		zeroYText.setEnabled(false);
		zeroZText.setEnabled(false);
		
		gravityXText.setText(""+report.getGravityX());
		gravityYText.setText(""+report.getGravityY());
		gravityZText.setText(""+report.getGravityZ());
		zeroXText.setText(""+report.getZeroX());
		zeroYText.setText(""+report.getZeroY());
		zeroZText.setText(""+report.getZeroZ());

		panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		panel.add(gravityXLabel);
		panel.add(gravityXText);
		panel.add(gravityYLabel);
		panel.add(gravityYText);
		panel.add(gravityZLabel);
		panel.add(gravityZText);
		panel.add(zeroXLabel);
		panel.add(zeroXText);
		panel.add(zeroYLabel);
		panel.add(zeroYText);
		panel.add(zeroZLabel);
		panel.add(zeroZText);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
