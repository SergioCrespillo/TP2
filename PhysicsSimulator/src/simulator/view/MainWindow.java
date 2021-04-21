package simulator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		setContentPane(mainPanel);
		
		mainPanel.setPreferredSize(getPreferredSize());
		ControlPanel cp = new ControlPanel(_ctrl);
		StatusBar sb = new StatusBar(_ctrl);
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		sb.setLayout(new BoxLayout(sb, BoxLayout.Y_AXIS));
		JPanel content = new JPanel();
		BodiesTable bt = new BodiesTable(_ctrl);
		//Viewer v = new Viewer(_ctrl);
		content.add(bt);
		//content.add(v);
		content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS));
		
		mainPanel.add(cp, BorderLayout.PAGE_START);
		mainPanel.add(sb,BorderLayout. PAGE_END);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c , String title ) {
		JPanel p = new JPanel( new BorderLayout() );
		// TODO add a framed border to p with title
		p .add( new JScrollPane( c ));
		return p ;
	}
}
