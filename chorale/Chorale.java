package com.eshimoniak.choraleeartrainer.chorale;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Key;
import org.jfugue.theory.Note;
import org.jfugue.theory.Scale;

import com.eshimoniak.choraleeartrainer.ChordProgressionFactory;

public class Chorale {
	private List<Note> soprano, alto, tenor, bass;
	private ChordProgression cp;
	private String romanProg;
	private Key key;
	
	/**
	 * @param key The key that the chorle will be in
	 * @param length The number of measures the chorale will last
	 * @param tsTop The top number of the time signature
	 * @param tsBottom The bottom number of the time signature
	**/
	public Chorale(Key key, int length, int tsTop, int tsBottom) {
		this.key = key;
		Random gen = new Random();
		
		//Initialize voices
		soprano = new ArrayList<Note>();
		alto = new ArrayList<Note>();
		tenor = new ArrayList<Note>();
		bass = new ArrayList<Note>();
		
		romanProg = ChordProgressionFactory.getBetterChordProg(key.getScale(), tsTop * length);
		
		cp = new ChordProgression(romanProg);
		
		cp.setKey(key);
		
		System.out.println(romanProg);
		
		
		//Create bassline
		for (Chord c : cp.getChords()) {
			bass.add(new Note(c.getBassNote().getOriginalString() + "3"));
		}
		System.out.print("\n");
		
		//Create Melody
		soprano.add(Melody.getFirstNote(cp.getChords()[0]));
		for (int i = 1; i < cp.getChords().length; i++) {
			soprano.add(Melody.nextNote(soprano.get(i - 1), cp.getChords()[i]));
		}
		
		//Fill inner voices
		for (int i = 0; i < cp.getChords().length; i++) {
			Note[] innerVoices = InnerVoices.getInnerVoices(soprano.get(i), bass.get(i), cp.getChords()[i]);
			alto.add(innerVoices[0]);
			tenor.add(innerVoices[1]);
		}
	}
	
	public Chorale(Key key, int length) {
		this(key, length, 4, 4);
	}
	
	public Chorale() {
		this(new Key("C"), 2, 4, 4);
	}
	
	public void play() {
		Pattern pat = new Pattern(toStaccatto());
		pat.setTempo(60);
		pat.setInstrument("Piano");
		Player p = new Player();
		p.play(pat);
	}
	
	public String toStaccatto() {
		String stacc = "V0 ";
		
		for (Note n : soprano) {
			stacc += n.getToneString() + " ";
		}
		
		stacc += "V1 ";
		for (Note n : alto) {
			stacc += n.getToneString() + " ";
		}
		
		stacc += "V2 ";
		for (Note n : tenor) {
			stacc += n.getToneString() + " ";
		}
		
		stacc += "V3 ";
		for (Note n : bass) {
			stacc += n.getToneString() + " ";
		}
		
		return stacc.trim();
	}
	
	public void toMidi() {
		try {
			int id = 1;
			File f = new File("chorale" + id + ".midi");
			while (f.exists()) {
				id++;
				f = new File("chorale" + id + ".midi");
			}
			
			
			Pattern pat = new Pattern(toStaccatto());
			pat.setTempo(60);
			
			MidiFileManager.savePatternToMidi((PatternProducer) pat, f);
			PrintWriter out = new PrintWriter("chords" + id + ".txt");
			if (key.getScale() == Scale.MAJOR) {
				out.print(Note.getToneStringWithoutOctave(key.getRoot().getValue()) + ": " + romanProg);
			} else if (key.getScale() == Scale.MINOR) {
				out.print(Note.getToneStringWithoutOctave(key.getRoot().getValue()).toLowerCase() + ": " + romanProg);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check() {
		for (int i = 1; i < cp.getChords().length; i++) {
			//Spacing
			
			//Parallelism
			for (int j = 0; i < 4; i++) {
				
			}
			
			//Directs
			
			//Contraries
			
		}
	}
}
