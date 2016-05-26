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

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import motejx.extensions.balanceboard.BalanceBoard;
import motejx.extensions.balanceboard.BalanceBoardEvent;
import motejx.extensions.balanceboard.BalanceBoardListener;

/**
 * 
 * <p>
 * @author <a href="mailto:vfritzsch@users.sourceforge.net">Volker Fritzsch</a>
 */
public class BalanceBoardListenerPanel implements BalanceBoardListener {

	private JPanel panel;
	
	private JTable table;
	
	private BalanceBoardEvent event;
	
	private class Model extends AbstractTableModel {
	
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
			switch(rowIndex) {
			case 0:
				return "Sensor A";
			case 1:
				return "Sensor B";
			case 2:
				return "Sensor C";
			case 3:
				return "Sensor D";
			}}
			
			if (columnIndex == 1) {
				if (event == null) {
					return "-";
				}
				
				switch (rowIndex) {
				case 0:
					return event.getTopRight();
				case 1:
					return event.getBottomRight();
				case 2:
					return event.getTopLeft();
				case 3:
					return event.getBottomLeft();
				}
			}
			
			if (columnIndex == 2) {
				if (event == null) {
					return "-";
				}
				
				switch (rowIndex) {
				case 0:
					return event.getTopRightInterpolated();
				case 1:
					return event.getBottomRightInterpolated();
				case 2:
					return event.getTopLeftInterpolated();
				case 3:
					return event.getBottomLeftInterpolated();
				}
			}
			return null;
		}
	
		public int getRowCount() {
			return 4;
		}
	
		public int getColumnCount() {
			return 3;
		}
		
		public void changed() {
			fireTableDataChanged();
		}
	};
	
	private Model model = new Model();
	
	public BalanceBoardListenerPanel(BalanceBoard board) {
		board.addBalanceBoardListener(this);
		
		table = new JTable(model);
		panel = new JPanel(new BorderLayout());
		panel.add(table, BorderLayout.CENTER);
	}
	
	public void balanceBoardChanged(BalanceBoardEvent evt) {
		this.event = evt;
		model.changed();
	}

	public JPanel getPanel() {
		return panel;
	}
}
