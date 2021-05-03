/*package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.SimulatorObserver;

public class ParametersTableModel extends AbstractTableModel {
	private List<JSONObject> dataForce;
	private JTextField value;
	private String columnNames[] = { "Key", "Value", "Description" };

	public ParametersTableModel(Controller ctrl, String item) {
		value = new JTextField();
		dataForce = ctrl.getForceLawsInfo();
	}
	
	@Override
	public int getRowCount() {
		return dataForce == null ? 0 : dataForce.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		JSONObject fl = this.dataForce.get(rowIndex);
		switch (columnIndex) {
		 case 0: s = fl.get("data"); break;
		 case 1: s = value; break;
		 case 2: s = p.getPosition();  break;
		 default: assert (false);
		}
		return s;
	}
}
*/