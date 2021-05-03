package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import simulator.control.Controller;

public class MainWindow extends JFrame {
	Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		ControlPanel cp = new ControlPanel(_ctrl, this);
		StatusBar sb = new StatusBar(_ctrl);
		cp.setLayout( new BoxLayout(cp, BoxLayout.Y_AXIS));
		sb.setLayout( new BoxLayout(sb, BoxLayout.Y_AXIS));
		mainPanel.add(cp, BorderLayout.PAGE_START);
		mainPanel.add(sb, BorderLayout.PAGE_END);
		
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		// tables
		JPanel eventsView = createViewPanel(new JTable(new BodiesTableModel(_ctrl)), "Bodies");
		eventsView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(eventsView);
		
		Viewer v = new Viewer(_ctrl);
		tablesPanel.add(v);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c , String title ) {
		JPanel p = new JPanel( new BorderLayout() );
		// TODO add a framed border to p with title
		p.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.black, 2), 
					title, 
					TitledBorder.LEFT, TitledBorder.TOP));
		p.add(new JScrollPane(c));
		return p;
	}
}
