package com.eshimoniak.choraleeartrainer;

import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

public class NoteMath {
	public static final int
	PER_0 = 0,
	DIM_2 = 0,
	MIN_2 = 1,
	MAJ_2 = 2,
	MIN_3 = 3,
	MAJ_3 = 4,
	PER_4 = 5,
	AUG_4 = 6,
	DIM_5 = 6,
	PER_5 = 7,
	AUG_5 = 8,
	MIN_6 = 8,
	MAJ_6 = 9,
	DIM_7 = 9,
	MIN_7 = 10,
	MAJ_7 = 11,
	AUG_7 = 12,
	PER_8 = 12;
	
	public class ComparableNote extends Note implements Comparable {
		@Override
		public int compareTo(Object o) {
			ComparableNote n = (ComparableNote) o;
			return n.getValue() - getValue();
		}
	}
	
	public static Chord diminish(Chord c) {
		Note[] notes = c.getNotes();
		
		for (int i = 2; i < notes.length; i++) {
			notes[i].changeValue(-1);
		}
		
		return Chord.fromNotes(notes);
	}
}
