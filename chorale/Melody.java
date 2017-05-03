package com.eshimoniak.choraleeartrainer.chorale;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import com.eshimoniak.choraleeartrainer.ChordUtil;

public class Melody {
	public static Note getFirstNote(Chord chord) {
		Random r = new Random();
		int minval = 12*5;
		List<Note> possible = new ArrayList<Note>();
		Note[] chordTones = chord.getNotes();
		
		for (int i = minval; i < minval + 24; i++) {
			Note n = new Note(Note.getToneString((byte) i));
			
			for (Note chordTone : chordTones) {
				if (Note.getToneStringWithoutOctave(n.getValue()) == Note.getToneStringWithoutOctave(chordTone.getValue())) {
					possible.add(n);
				}
			}
		}
		
		
		return possible.get(r.nextInt(possible.size()));
	}
	
	public static Note nextNote(Note currNote, Chord chord) {
		Random r = new Random();
		int minval = currNote.getValue() - 12;
		List<Note> possible = new ArrayList<Note>();
		
		for (int i = minval; i < minval + 24; i++) {
			Note n = new Note(Note.getToneString((byte) i));
			
			if (ChordUtil.isChordTone(n, chord)) {
				possible.add(n);
			}
		}
		
		
		return possible.get(r.nextInt(possible.size()));
	}
}
