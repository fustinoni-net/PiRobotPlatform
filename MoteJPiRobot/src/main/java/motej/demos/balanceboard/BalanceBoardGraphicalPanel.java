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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import motejx.adapters.BalanceBoardDirectionAdapter;
import motejx.extensions.balanceboard.BalanceBoard;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class BalanceBoardGraphicalPanel extends BalanceBoardDirectionAdapter {
	
	private int x,y;
	
	private JLabel xyLabel = new JLabel();

	private JPanel panel = new JPanel() {
		protected void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);
			
			if (x == 0 && y == 0) {
				x = getWidth() / 2;
				y = getHeight() / 2;
			}
			
			Graphics2D g2d = (Graphics2D) g;
			Color bak = g2d.getColor();
			g2d.setColor(Color.RED);
			g2d.fillOval(x, y, 10, 10);
			g2d.setColor(bak);
		}
	};
	
	@Override
	public void directionChanged(float x, float y) {
		this.x += Math.round(x);
		this.y -= Math.round(y);
		
		if (this.x < 1)
			this.x = 1;
		if (this.x > panel.getWidth() - 1)
			this.x = panel.getWidth() - 1;
		if (this.y < 1)
			this.y = 1;
		if (this.y > panel.getHeight() - 1)
			this.y = panel.getHeight() - 1;
		
		xyLabel.setText("X: " + this.x + " - Y: " + this.y + " - x: " + Math.round(x) + " - y: " + Math.round(y));
		
		panel.repaint();
	}
	
	public BalanceBoardGraphicalPanel(BalanceBoard board) {
		board.addBalanceBoardListener(this);
		panel.setLayout(new FlowLayout());
		panel.add(xyLabel);
	}
	
	public JPanel getPanel() {
		panel.setSize(400, 400);
		return panel;
	}
}
