package simulator.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar  extends JPanel implements SimulatorObserver {

	private JLabel _currTime = new JLabel(); // for current time
	private JLabel _currLaws = new JLabel(); // for gravity laws
	private JLabel _numOfBodies = new JLabel(); // for number of bodies
	
	public StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		JToolBar toolbar = new JToolBar();
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		// TODO complete the code to build the tool bar
		JLabel time = new JLabel("Time: ");
	    JLabel laws = new JLabel("Laws: ");
	    JLabel bodies = new JLabel("Bodies: ");
	    toolbar.add(time);
	    toolbar.add(_currTime);
	    toolbar.addSeparator();
	    toolbar.add(bodies);
	    toolbar.add(_numOfBodies);
	    toolbar.addSeparator();
	    toolbar.add(laws);
	    toolbar.add(_currLaws);
		this.add(toolbar);
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		_currLaws.setText(fLawsDesc);
		_numOfBodies.setText(String.valueOf(bodies.size()));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		_currTime.setText(String.valueOf(time));
		_currLaws.setText(fLawsDesc);
		_numOfBodies.setText(String.valueOf(bodies.size()));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		_numOfBodies.setText(String.valueOf(bodies.size()));
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		_currTime.setText(String.valueOf(time));
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		_currLaws.setText(fLawsDesc);
	}

}
