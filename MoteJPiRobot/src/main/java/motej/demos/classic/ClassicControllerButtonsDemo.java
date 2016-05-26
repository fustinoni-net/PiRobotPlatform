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

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;
import motej.request.ReportModeRequest;
import motejx.extensions.classic.ClassicController;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class ClassicControllerButtonsDemo {

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout());
		
		MoteFinder.getMoteFinder().addMoteFinderListener(new MoteFinderListener() {
		
			public void moteFound(Mote mote) {
				ClassicController cc = null;
				while(cc == null) {
					cc = mote.<ClassicController>getExtension();
				}
				
				final DigitalButtonPanel p = new DigitalButtonPanel(cc);
				SwingUtilities.invokeLater(new Runnable() {
				
					public void run() {
						frame.getContentPane().add(p.getPanel(), BorderLayout.CENTER);
						frame.pack();
					}
				});
				
				mote.setPlayerLeds(new boolean[] { true, false, false, false });
				mote.setReportMode(ReportModeRequest.DATA_REPORT_0x32);
			}
		});
		MoteFinder.getMoteFinder().startDiscovery();
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
