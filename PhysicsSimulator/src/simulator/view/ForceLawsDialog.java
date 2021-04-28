package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.json.JSONObject;

import simulator.control.Controller;

public class ForceLawsDialog extends JDialog{
	
	private static final String M1="Select a force law and provide values for the parameters in the Value column (default values are used for)";
	private static final String M2="parameters with no value)";

	protected Controller _ctrl;
	
	protected int status;
	
	protected JComboBox<String> forces;
	
	public ForceLawsDialog(Controller ctrl) {
		_ctrl=ctrl;
		initGUI();
	}
	
	private void initGUI() {
		status = 0;

		setTitle("Choose Force Laws");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1,0,15));
		
		setContentPane(mainPanel);

		JPanel labelPanel = new JPanel(new GridLayout(2,1));
		JLabel m1 = new JLabel(M1);
		JLabel m2 = new JLabel(M2);
		m1.setAlignmentX(CENTER_ALIGNMENT);
		m2.setAlignmentX(CENTER_ALIGNMENT);
		
		labelPanel.add(m1);
		labelPanel.add(m2);

		mainPanel.add(labelPanel);

		JPanel viewsPanel = new JPanel(new FlowLayout());
		mainPanel.add(viewsPanel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		

		this.forces = new JComboBox<String>();
		this.forces.setPreferredSize(new Dimension(100,25));
		
		List<JSONObject> listaJson = _ctrl.getForceLawsInfo();
		for(JSONObject json:listaJson) {
			forces.addItem(json.getString("desc"));
		}
		
		viewsPanel.add(new JLabel("Force Laws:"));
		viewsPanel.add(this.forces);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				status = 0;
				ForceLawsDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (forces.getSelectedItem() != null) {
					status = 1;
					changeForceLaw();
					ForceLawsDialog.this.setVisible(false);
				}
			}

			private void changeForceLaw() {
				
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}

}
