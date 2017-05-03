package com.eshimoniak.choraleeartrainer.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.Key;

import com.eshimoniak.choraleeartrainer.chorale.Chorale;

public class Window extends javax.swing.JFrame {
	JComboBox<String> key, mode;
	JButton create, play, export, reveal;
	JSpinner measures;
	JPanel content;
	Chorale chorale;
	JTextArea output;
	
	public Window() {
		DefaultComboBoxModel<String> majorModel = new DefaultComboBoxModel<String>(new String[] {"C", "F", "G", "Bb", "D", "Eb", "A",  "Ab", "E",  "Db", "B",  "Gb", "F#", "Cb", "C#"});
		DefaultComboBoxModel<String> minorModel = new DefaultComboBoxModel<String>(new String[] {"A", "D", "E", "G",  "B", "C",  "F#", "F",  "C#", "Bb", "G#", "Eb", "D#", "Ab", "A#"});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(240, 240));
		setTitle("Chorale Tester");
		
		content = new JPanel();
		add(content);
		
		key = new JComboBox<String>();
		key.setModel(majorModel);
		content.add(key);
		
		mode = new JComboBox<String>(new String[] {"Major", "Minor"});
		mode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				key.removeAll();
				if (mode.getSelectedItem() == "Major") {
					key.setModel(majorModel);
				} else if (mode.getSelectedItem() == "Minor") {
					key.setModel(minorModel);
				}
			}
		});
		content.add(mode);
		
		SpinnerModel model = new SpinnerNumberModel(1, 1, 8, 1);     
		measures = new JSpinner(model);
		content.add(measures);
		
		create = new JButton();
		create.setText("Create");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mode.getSelectedItem() == "Major") {
					chorale = new Chorale(new Key((String) key.getSelectedItem()), (Integer) measures.getValue());
				} else if (mode.getSelectedItem() == "Minor") {
					chorale = new Chorale(new Key(((String) key.getSelectedItem()) + "min"), (Integer) measures.getValue());
				}
				JOptionPane.showMessageDialog(null, "Successfully created chorale");
				output.setText("<hidden>");
			}
		});
		content.add(create);
		
		play = new JButton();
		play.setText("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chorale != null) {
					Pattern pat = new Pattern(chorale.toStaccatto());
					pat.setTempo(60);
					Player p = new Player();
					p.play(pat);
				}
			}
		});
		content.add(play);
		
		export = new JButton();
		export.setText("Export");
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chorale != null) {
					chorale.toMidi();
					JOptionPane.showMessageDialog(null, "Successfully exported chorale");
				}
			}
		});
		content.add(export);
		
		reveal = new JButton();
		reveal.setText("Reveal");
		reveal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chorale != null) {
					output.setText(chorale.toASCII());
				}
			}
		});
		content.add(reveal);
		
		output = new JTextArea();
		output.setFont(new Font("monospaced", Font.PLAIN, 12));
		output.setText("<hidden>");
		content.add(output);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}