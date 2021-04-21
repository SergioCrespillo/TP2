package simulator.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;
	private JLabel _steps = new JLabel("Steps: ");
	private JLabel _delta = new JLabel("Delta-Time: ");
    private JTextField txtCajaDeTextoD = new JTextField();
    private JSpinner _stepsSpinner = new JSpinner();
	private JButton filebutton = new JButton();
	private JButton runbutton = new JButton();
	private JButton stopbutton = new JButton();
	private JButton closebutton = new JButton();
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		JToolBar toolbar = new JToolBar();
		
		//los botones
		filebutton.setIcon(new ImageIcon("resources/icons/open.png"));
		filebutton.setToolTipText("This button opens a file");
		filebutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 System.out.println(file.getAbsolutePath());
					try {
						InputStream in = new FileInputStream(file);
						_ctrl.reset();
						_ctrl.loadBodies(in);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(filebutton, "Archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else System.out.println("load cancelled by user");
			}
		});
		
		runbutton.setIcon(new ImageIcon("resources/icons/run.png"));
		runbutton.setToolTipText("This button runs the simulator");
		runbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				// TODO Auto-generated method stub
				try {
					filebutton.setEnabled(false);
					//gravitybutton.setEnabled(false);
					_stopped = false;
					_ctrl.setDeltaTime(Double.parseDouble(txtCajaDeTextoD.getText()));
					run_sim((Integer)_stepsSpinner.getValue());
				}catch(ClassCastException ex) {
					JOptionPane.showMessageDialog(runbutton, "No se puede realizar esta acción", "Error", JOptionPane.ERROR_MESSAGE);
					filebutton.setEnabled(true);
					//gravitybutton.setEnabled(true);
				}
			}
		});
		
		
		stopbutton.setIcon(new ImageIcon("resources/icons/stop.png"));
		stopbutton.setToolTipText("This button stops the simulator");
		stopbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				// TODO Auto-generated method stub
				try {
					_stopped = true;
					filebutton.setEnabled(true);
					//gravitybutton.setEnabled(true);
				}catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(stopbutton, ex);
				}
			}
		});
		
		
		closebutton.setIcon(new ImageIcon("resources/icons/exit.png"));
		closebutton.setToolTipText("This button closes the simulator");
		closebutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				// TODO Auto-generated method stub
				Object [] opciones ={"SI","NO"};
				int eleccion = JOptionPane.showOptionDialog(getRootPane(),"Desea cerrar la aplicacion","Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,opciones,"SI");
				if (eleccion == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		
		toolbar.add(filebutton);
	    toolbar.addSeparator();
	    //toolbar.add(gravitybutton);
	    toolbar.addSeparator();
	    toolbar.add(runbutton);
	    toolbar.add(stopbutton);
	    toolbar.add(_steps);
		toolbar.add(_stepsSpinner);
		toolbar.addSeparator();
	    toolbar.add(_delta);
		toolbar.add(txtCajaDeTextoD);
		toolbar.add(Box.createHorizontalGlue());
	    toolbar.add(closebutton, BorderLayout.EAST);
	    this.add(toolbar);
	}
	
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.runGUI(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				JOptionPane.showMessageDialog(runbutton, e);
				// TODO enable all buttons
				filebutton.setEnabled(true);
				//gravitybutton.setEnabled(true);
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
		} 
		else {
			_stopped = true;
			// TODO enable all buttons
			filebutton.setEnabled(true);
			//gravitybutton.setEnabled(true);
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

}
