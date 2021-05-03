package simulator.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.json.JSONObject;

import simulator.model.ForceLaws;

public class LawsSelectionDialog extends JDialog{
		
	private int _status;
	private JComboBox<String> _laws;
	private DefaultComboBoxModel<String> _lawsModel;
	JSONBuilderDialog _dialog;
	
	public LawsSelectionDialog(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	private void initGUI() {
		_status = 0;

		setTitle("Force Laws Selection");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		JLabel helpMsg = new JLabel("Select a force law and provide values for the parametes in the Value column (default values are used for parametes with no value).");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);

		_lawsModel = new DefaultComboBoxModel<>();
		_laws = new JComboBox<>(_lawsModel);

		viewsPanel.add(_laws);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status = 0;
				LawsSelectionDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (_lawsModel.getSelectedItem() != null) {
					_status = 1;
					LawsSelectionDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public int open(List<JSONObject> law) {
		_lawsModel.removeAllElements();
		for(JSONObject f: law)
			_lawsModel.addElement(f.getString("desc"));

		setVisible(true);
		return _status;
	}

	ForceLaws getLaw() {
		return (ForceLaws) _lawsModel.getSelectedItem();
	}

}
