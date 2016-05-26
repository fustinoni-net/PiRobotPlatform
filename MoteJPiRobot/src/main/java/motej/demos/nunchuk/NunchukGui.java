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

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import motej.Extension;
import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;
import motej.event.AccelerometerEvent;
import motej.event.AccelerometerListener;
import motej.event.ExtensionEvent;
import motej.event.ExtensionListener;
import motej.request.ReportModeRequest;
import motejx.extensions.nunchuk.AnalogStickEvent;
import motejx.extensions.nunchuk.AnalogStickListener;
import motejx.extensions.nunchuk.Nunchuk;
import motejx.extensions.nunchuk.NunchukButtonEvent;
import motejx.extensions.nunchuk.NunchukButtonListener;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
@SuppressWarnings("serial")
public class NunchukGui implements MoteFinderListener, ExtensionListener, AccelerometerListener<Nunchuk>, AnalogStickListener, NunchukButtonListener {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		
                        @Override
			public void run() {
				new NunchukGui();
			}
		
		});
	}
	private JFrame frame;
	private Mote mote;
	private MoteFinder finder;
	
	private Nunchuk nunchuk;
	private JLabel maxPoint = new JLabel("cal (max)");
	private JLabel minPoint = new JLabel("cal (min)");
	
	private JLabel centerPoint = new JLabel("cal (center)");
	
	private AnalogStickPanel analogDisplay = new AnalogStickPanel();
	
	private AccelerometerPanel accelerometerPanel = new AccelerometerPanel();
	private JLabel moteLabel = new JLabel("none");
	
	private JLabel extensionLabel = new JLabel("none");
	
	private JButton zButton;
	
	private JButton cButton;
	
	private Color originalColor;

	private Action startDiscoverAction = new AbstractAction() {
	
		public void actionPerformed(ActionEvent e) {
			finder.startDiscovery();
		}
	
	};

	private Action cancelDiscoverAction = new AbstractAction() {
		
		public void actionPerformed(ActionEvent e) {
			finder.stopDiscovery();
		}
	
	};
	
	public NunchukGui() {
		finder = MoteFinder.getMoteFinder();
		finder.addMoteFinderListener(this);
		
		initGui();
	}
	
	public void accelerometerChanged(AccelerometerEvent<Nunchuk> evt) {
		accelerometerPanel.accelerometerValueChanged(evt.getX() & 0xff, evt.getY() & 0xff, evt.getZ() & 0xff);
	}
	
        @Override
	public void analogStickChanged(AnalogStickEvent evt) {
		analogDisplay.setStickPoint(evt.getPoint());
		analogDisplay.repaint();
	}

        @Override
	public void buttonPressed(NunchukButtonEvent evt) {
		if (evt.isButtonCPressed()) {
			cButton.setBackground(Color.BLUE);
		} else {
			cButton.setBackground(originalColor);
		}
		
		if (evt.isButtonZPressed()) {
			zButton.setBackground(Color.BLUE);
		} else {
			zButton.setBackground(originalColor);
		}
	}

        @Override
	public void extensionConnected(ExtensionEvent evt) {
		final Extension ext = evt.getExtension();
		
		if (ext instanceof Nunchuk) {
			nunchuk = (Nunchuk) ext;
			nunchuk.addAccelerometerListener(this);
			nunchuk.addAnalogStickListener(this);
			nunchuk.addNunchukButtonListener(this);
			
			Thread t = new Thread(new Runnable() {
			
				public void run() {
					while (nunchuk.getCalibrationData() == null) {
						try {
							Thread.sleep(1l);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					analogDisplay.setNunchukCalibrationData(nunchuk.getCalibrationData());
					
					SwingUtilities.invokeLater(new Runnable() {
					
						public void run() {
							if (nunchuk != null && nunchuk.getCalibrationData() != null) {
								Point min = nunchuk.getCalibrationData().getMinimumAnalogPoint();
								Point max = nunchuk.getCalibrationData().getMaximumAnalogPoint();
								Point center = nunchuk.getCalibrationData().getCenterAnalogPoint();
								minPoint.setText("cal (min) - x: " + min.x + " / y: " + min.y);
								maxPoint.setText("cal (max) - x: " + max.x + " / y: " + max.y);
								centerPoint.setText("cal (center) - x: " + center.x + " / y: " + center.y);

								int zero = nunchuk.getCalibrationData().getZeroForceX();
								int earth = nunchuk.getCalibrationData().getGravityForceX();
								accelerometerPanel.setCalibrationDataX(zero, earth);
								
								zero = nunchuk.getCalibrationData().getZeroForceY();
								earth = nunchuk.getCalibrationData().getGravityForceY();
								accelerometerPanel.setCalibrationDataY(zero, earth);
								
								zero = nunchuk.getCalibrationData().getZeroForceZ();
								earth = nunchuk.getCalibrationData().getGravityForceZ();
								accelerometerPanel.setCalibrationDataZ(zero, earth);
							}
						}
					
					});
				}
			
			});
			t.start();
			
			
			mote.setReportMode(ReportModeRequest.DATA_REPORT_0x32);
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				extensionLabel.setText(ext.toString());
			}
		
		});
	}

        @Override
	public void extensionDisconnected(ExtensionEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
				extensionLabel.setText("none");
				minPoint.setText("");
				maxPoint.setText("");
				centerPoint.setText("");
			}
		
		});
	}
	
	protected void initGui() {
		frame = new JFrame("Nunchuk Gui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		
			@Override
			public void windowClosing(WindowEvent e) {
				if (mote != null) {
					mote.setReportMode(ReportModeRequest.DATA_REPORT_0x30);
					mote.disconnect();
				}
			}
		
		});
		
		JButton discoverButton = new JButton(startDiscoverAction);
		discoverButton.setText("Start Discovery");
		
		JButton cancelDiscoverButton = new JButton(cancelDiscoverAction);
		cancelDiscoverButton.setText("Cancel Discovery");
		
		cButton = new JButton("C");
		cButton.setEnabled(false);
		
		zButton = new JButton("Z");
		zButton.setEnabled(false);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new FlowLayout());
		pane.add(discoverButton);
		pane.add(cancelDiscoverButton);
		pane.add(moteLabel);
		pane.add(extensionLabel);
		pane.add(cButton);
		pane.add(zButton);
		pane.add(minPoint);
		pane.add(maxPoint);
		pane.add(centerPoint);
		pane.add(analogDisplay);
		analogDisplay.setPreferredSize(new Dimension(250,250));
		analogDisplay.setBackground(Color.WHITE);
		analogDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pane.add(accelerometerPanel);
		
//		frame.setSize(800, 600);
		frame.pack();
		
		frame.setVisible(true);
		originalColor = zButton.getBackground();
	}

        @Override
	public void moteFound(final Mote mote) {
		this.mote = mote;
//		finder.stopDiscovery();
		mote.setPlayerLeds(new boolean[] {true, false, false, false} );
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
				moteLabel.setText(mote.toString());
			}
		
		});
		mote.addExtensionListener(this);
	}
}
