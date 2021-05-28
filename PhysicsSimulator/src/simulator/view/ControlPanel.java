package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;
	private JLabel _steps;
	private JLabel _delta;
    private JTextField txtCajaDeTextoD;
    private JSpinner _stepsSpinner;
	private JButton filebutton;
	private JButton forceLawsbutton;
	private JButton runbutton;
	private JButton stopbutton;
	private JButton closebutton;
	private LawsSelectionDialog _dialog;
	
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		JToolBar toolbar = new JToolBar();
		
		_steps = new JLabel("Steps: ");
		_delta = new JLabel("Delta-Time: ");
	    txtCajaDeTextoD = new JTextField();
	    _stepsSpinner = new JSpinner(new SpinnerNumberModel(10000, 100, 10000, 1));
		filebutton = new JButton();
		forceLawsbutton = new JButton();
		runbutton = new JButton();
		stopbutton = new JButton();
		closebutton = new JButton();
		
		this._stepsSpinner.setPreferredSize(new Dimension(70,30));
		this._stepsSpinner.setMaximumSize(_stepsSpinner.getPreferredSize());
		
		this.txtCajaDeTextoD.setPreferredSize(new Dimension(70,30));
		this.txtCajaDeTextoD.setMaximumSize(_stepsSpinner.getPreferredSize());
		
		//los botones
		filebutton.setIcon(new ImageIcon("resources/icons/open.png"));
		filebutton.setToolTipText("This button opens a file");
		filebutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				fc.setCurrentDirectory(workingDirectory);
				
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 System.out.println(file.getAbsolutePath());
					try {
						InputStream in = new FileInputStream(file);
						_ctrl.reset();
						_ctrl.loadBodies(in);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(filebutton, "Archivo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(filebutton, "Archivo no valido", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else System.out.println("load cancelled by user");
			}
		});
		
		forceLawsbutton.setIcon(new ImageIcon("resources/icons/physics.png"));
		forceLawsbutton.setToolTipText("This button choose force laws");
		forceLawsbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					select_forcelaw();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(forceLawsbutton, "Something went wrong: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		runbutton.setIcon(new ImageIcon("resources/icons/run.png"));
		runbutton.setToolTipText("This button runs the simulator");
		runbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				try {
					disabledButton();
					_stopped = false;
					_ctrl.setDeltaTime(Double.parseDouble(txtCajaDeTextoD.getText()));
					run_sim((Integer)_stepsSpinner.getValue());
				}catch(ClassCastException ex) {
					JOptionPane.showMessageDialog(runbutton, "No se puede realizar esta acción", "Error", JOptionPane.ERROR_MESSAGE);
					_stopped = true;
					enabledButtons();
				}
			}
		});
		
		
		stopbutton.setIcon(new ImageIcon("resources/icons/stop.png"));
		stopbutton.setToolTipText("This button stops the simulator");
		stopbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {  
				try {
					_stopped = true;
					enabledButtons();
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
	    toolbar.add(forceLawsbutton);
	    toolbar.addSeparator();
	    toolbar.add(runbutton);
	    toolbar.add(stopbutton);
	    toolbar.add(_steps);
		toolbar.add(_stepsSpinner);
		toolbar.addSeparator();
	    toolbar.add(_delta);
		toolbar.add(txtCajaDeTextoD);
		toolbar.addSeparator();
		toolbar.add(Box.createHorizontalGlue());
	    toolbar.add(closebutton, BorderLayout.EAST);
	    this.add(toolbar);
	}
	
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.runGUI(1);
			} catch (Exception e) {
				//show the error in a dialog box
				JOptionPane.showMessageDialog(runbutton, e);
				//enable all buttons
				enabledButtons();
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
			//enable all buttons
			enabledButtons();
		}
	}
	
	protected void select_forcelaw() throws JSONException, Exception {
		if(_dialog == null) {
			_dialog = new LawsSelectionDialog((Frame) SwingUtilities.getWindowAncestor(this),_ctrl.getForceLawsInfo());
		}
		
		int status = _dialog.open(_ctrl.getForceLawsInfo());
		if (status == 0) {
			System.out.println("Canceled");
		}
		else { 
			_ctrl.setForceLaws(_dialog.getForceLaw());
		}
	}
	
	private void enabledButtons() {
		this.filebutton.setEnabled(true);
		this.forceLawsbutton.setEnabled(true);
		this.runbutton.setEnabled(true);
		this.closebutton.setEnabled(true);
	}
	
	private void disabledButton() {
		this.filebutton.setEnabled(false);
		this.forceLawsbutton.setEnabled(false);
		this.runbutton.setEnabled(false);
		this.closebutton.setEnabled(false);
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		txtCajaDeTextoD.setText(Double.toString(dt));
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		
	}

}
