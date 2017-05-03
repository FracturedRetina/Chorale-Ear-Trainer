package com.eshimoniak.choraleeartrainer.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.jfugue.theory.Key;

import com.eshimoniak.choraleeartrainer.chorale.Chorale;

public class Window extends javax.swing.JFrame {
	JComboBox<String> key, mode;
	JButton create, play;
	JSpinner measures;
	JPanel content;
	Chorale chorale;
	
	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(240, 120));
		setTitle("Chorale Tester");
		
		content = new JPanel();
		add(content);
		
		key = new JComboBox<String>(new String[] {"C","G","D","A","E","B","F#","C#"});
		content.add(key);
		
		mode = new JComboBox<String>(new String[] {"Major"});
		content.add(mode);
		
		SpinnerModel model = new SpinnerNumberModel(1, 1, 8, 1);     
		measures = new JSpinner(model);
		content.add(measures);
		
		create = new JButton();
		create.setText("Create");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chorale = new Chorale(new Key((String) key.getSelectedItem()), (Integer) measures.getValue());
				chorale.toMidi();
				JOptionPane.showMessageDialog(null, "Successfully exported chorale");
			}
		});
		content.add(create);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}