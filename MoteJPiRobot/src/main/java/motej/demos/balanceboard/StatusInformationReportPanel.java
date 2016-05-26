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

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import motej.Mote;
import motej.StatusInformationReport;
import motej.event.StatusInformationListener;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class StatusInformationReportPanel implements StatusInformationListener {

	private JLabel batteryLevelLabel = new JLabel("Battery Level");

	private JLabel led0Label = new JLabel("LED 0");

	private JLabel led1Label = new JLabel("LED 1");

	private JLabel led2Label = new JLabel("LED 2");

	private JLabel led3Label = new JLabel("LED 3");

	private JLabel continuousReportsLabel = new JLabel("Continuous Reports");

	private JLabel extensionLabel = new JLabel("Extension Connected");

	private JLabel speakerLabel = new JLabel("Speaker Enabled");

	private JCheckBox speakerCheckBox = new JCheckBox();

	private JCheckBox extensionCheckBox = new JCheckBox();

	private JCheckBox continuousReportsCheckBox = new JCheckBox();

	private JCheckBox led0CheckBox = new JCheckBox();

	private JCheckBox led1CheckBox = new JCheckBox();

	private JCheckBox led2CheckBox = new JCheckBox();

	private JCheckBox led3CheckBox = new JCheckBox();

	private JProgressBar batteryLevelProgressBar = new JProgressBar(0, 200);

	private JPanel panel;

	public StatusInformationReportPanel(Mote mote) {
		led0CheckBox.setEnabled(false);
		led1CheckBox.setEnabled(false);
		led2CheckBox.setEnabled(false);
		led3CheckBox.setEnabled(false);
		continuousReportsCheckBox.setEnabled(false);
		extensionCheckBox.setEnabled(false);
		speakerCheckBox.setEnabled(false);
		panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2));
		panel.add(batteryLevelLabel);
		panel.add(batteryLevelProgressBar);
		panel.add(led0Label);
		panel.add(led0CheckBox);
		panel.add(led1Label);
		panel.add(led1CheckBox);
		panel.add(led2Label);
		panel.add(led2CheckBox);
		panel.add(led3Label);
		panel.add(led3CheckBox);
		panel.add(continuousReportsLabel);
		panel.add(continuousReportsCheckBox);
		panel.add(extensionLabel);
		panel.add(extensionCheckBox);
		panel.add(speakerLabel);
		panel.add(speakerCheckBox);

		statusInformationReceived(mote.getStatusInformationReport());
		mote.addStatusInformationListener(this);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void statusInformationReceived(final StatusInformationReport report) {
		if (report != null) {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					led0CheckBox.setSelected(report.getLedEnabled()[0]);
					led1CheckBox.setSelected(report.getLedEnabled()[1]);
					led2CheckBox.setSelected(report.getLedEnabled()[2]);
					led3CheckBox.setSelected(report.getLedEnabled()[3]);
					continuousReportsCheckBox.setSelected(report
							.isContinuousReportingEnabled());
					extensionCheckBox.setSelected(report
							.isExtensionControllerConnected());
					speakerCheckBox.setSelected(report.isSpeakerEnabled());
					batteryLevelProgressBar.setValue(report.getBatteryLevel() & 0xff);
				}
			});
		}
	}

}
