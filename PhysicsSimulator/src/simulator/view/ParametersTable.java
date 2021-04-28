/*package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class ParametersTable extends JPanel {
	
	public ParametersTable(Controller ctrl, String item) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black, 2), 
					"Parameters Force Law", 
					TitledBorder.LEFT, TitledBorder.TOP));
		JTable t = new JTable( new ParametersTableModel(ctrl, item));
		t.setFillsViewportHeight(true);
		t.setShowGrid(false);
		this.add( new JScrollPane(t) );
	}{

}
*/