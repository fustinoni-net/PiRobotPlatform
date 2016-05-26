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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import motej.request.ReportModeRequest;
import motejx.extensions.balanceboard.BalanceBoard;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class BalanceBoardGUI {

	private JFrame frame;
	
	private Mote mote;
	
	private MoteFinderListener moteFinderListener = new MoteFinderListener() {
	
		public void moteFound(final Mote m) {
			System.out.println("Found mote!");
			mote = m;
			mote.setPlayerLeds(new boolean[] { true, false, false, false });
			mote.addCoreButtonListener(new CoreButtonListener() {
			
				public void buttonPressed(CoreButtonEvent evt) {
					System.out.println("Button pressed: " + evt.getButton());
				}
			});
		}
	};
	
	private Action findBalanceBoardAction = new AbstractAction("Find Balance Board") {
	
		public void actionPerformed(ActionEvent e) {
			MoteFinder finder = MoteFinder.getMoteFinder();
			finder.addMoteFinderListener(moteFinderListener);
			finder.startDiscovery();
		}
	};
	
	private Action statusInformationAction = new AbstractAction("Status Information") {
		
		public void actionPerformed(ActionEvent e) {
			final StatusInformationReportPanel statusInformationReportPanel = new StatusInformationReportPanel(mote);
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					frame.getContentPane().add(statusInformationReportPanel.getPanel(), BorderLayout.WEST);	
					frame.pack();
				}
			});
		}
	};
	
	private Action moteCalibrationAction = new AbstractAction("Mote Calibration") {
		
		public void actionPerformed(ActionEvent e) {
			final CalibrationDataReportPanel p = new CalibrationDataReportPanel(mote.getCalibrationDataReport());
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					frame.getContentPane().add(p.getPanel(), BorderLayout.EAST);
					frame.pack();
				}
			});
		}
	};
	
	private Action bbCalibrationAction = new AbstractAction("Balance Board Calibration") {
		
		public void actionPerformed(ActionEvent e) {
			final BalanceBoardCalibrationDataPanel p = new BalanceBoardCalibrationDataPanel(mote.<BalanceBoard>getExtension().getCalibrationData());
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					frame.getContentPane().add(p.getPanel(), BorderLayout.SOUTH);
					frame.pack();
				}
			});
		}
	};
	
	private Action bbListenerAction = new AbstractAction("Balance Board Listener") {
		
		public void actionPerformed(ActionEvent e) {
			final BalanceBoardListenerPanel p = new BalanceBoardListenerPanel(mote.<BalanceBoard>getExtension());
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					frame.getContentPane().add(p.getPanel(), BorderLayout.CENTER);
					frame.pack();
				}
			});
		}
	};
	
	protected Action report0x32Action = new AbstractAction("Report: 0x32") {
		
		public void actionPerformed(ActionEvent arg0) {
			mote.setReportMode(ReportModeRequest.DATA_REPORT_0x32);
		};
		
	};
	
	protected Action graphicalPanelAction = new AbstractAction("Graphical Panel") {
		
		public void actionPerformed(ActionEvent e) {
			final BalanceBoardGraphicalPanel p1 = new BalanceBoardGraphicalPanel(mote.<BalanceBoard>getExtension());
			final BalanceBoardListenerPanel p2 = new BalanceBoardListenerPanel(mote.<BalanceBoard>getExtension());
			SwingUtilities.invokeLater(new Runnable() {
			
				public void run() {
					JPanel tmp = new JPanel(new GridLayout(2,1));
					tmp.add(p1.getPanel());
					tmp.add(p2.getPanel());
					frame.getContentPane().add(tmp, BorderLayout.CENTER);
					frame.pack();
				}
			});
		}
	};
	
	public BalanceBoardGUI() {
		initGUI();
	}
	
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	
	protected void initGUI() {
		buttonPanel.add(new JButton(findBalanceBoardAction));
		buttonPanel.add(new JButton(statusInformationAction));
		buttonPanel.add(new JButton(moteCalibrationAction));
		buttonPanel.add(new JButton(bbCalibrationAction));
		buttonPanel.add(new JButton(bbListenerAction));
		buttonPanel.add(new JButton(graphicalPanelAction));
		buttonPanel.add(new JButton(report0x32Action));
		frame = new JFrame("motej BalanceBoard GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
				new BalanceBoardGUI();
			}
		});
	}
}
