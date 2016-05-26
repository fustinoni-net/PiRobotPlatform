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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import motejx.extensions.nunchuk.NunchukCalibrationData;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
@SuppressWarnings("serial")
public class AnalogStickPanel extends JPanel {
	
	private NunchukCalibrationData nunchukCalibrationData;
	
	private AffineTransform trafo;
	
	private Point stickPoint;
	
	public NunchukCalibrationData getNunchukCalibrationData() {
		return nunchukCalibrationData;
	}

	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		int width = getWidth();
		int height = getHeight();
		
		Graphics2D g2 = (Graphics2D) g;
		Color fc = g2.getColor();
		
		g2.setColor(Color.BLACK);
		g2.drawOval((int) (width * 0.025), (int) (height * 0.025), (int) (width * 0.95), (int) (height * 0.95));
		
		if (trafo == null && nunchukCalibrationData != null) {
			Point min = nunchukCalibrationData.getMinimumAnalogPoint();
			Point max = nunchukCalibrationData.getMaximumAnalogPoint();
			
			double sx = width / (max.x - min.x);
			double sy = height / (max.y - min.y);
			trafo = AffineTransform.getScaleInstance(sx, sy);
		}
		
		if (trafo != null && stickPoint != null) {
			Point2D p = trafo.transform(stickPoint, null);
			g2.setColor(Color.BLUE);
			g2.fillOval((int) (p.getX() - width * 0.05), (int) (p.getY() - height * 0.05), (int) (width * 0.1), (int) (height * 0.1));
		}
		
		g2.setColor(fc);
		
	}

	public void setNunchukCalibrationData(NunchukCalibrationData nunchukCalibrationData) {
		this.nunchukCalibrationData = nunchukCalibrationData;
	}

	public void setStickPoint(Point stickPoint) {
		this.stickPoint = stickPoint;
	}
}
