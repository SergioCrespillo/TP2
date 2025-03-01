package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import simulator.control.Controller;

public class BodiesTable extends JPanel {
	protected static final long serialVersionUID = 1L;
	
	public BodiesTable(Controller ctrl) 
	{
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black, 2), 
					"Bodies", 
					TitledBorder.LEFT, TitledBorder.TOP));
		JTable t = new JTable( new BodiesTableModel(ctrl));
		t.setFillsViewportHeight(true);
		t.setShowGrid(false);
		this.add( new JScrollPane(t) );
	}
}


