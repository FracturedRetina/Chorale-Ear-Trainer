package com.eshimoniak.choraleeartrainer;

import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

public class ChordUtil {
	public static int ROOT = 0;
	public static int THIRD = 1;
	public static int FIFTH = 2;
	public static int SEVENTH = 3;
	
	public static boolean isChordTone(Note tone, Chord chord) {
		for (Note n : chord.getNotes()) {
			if (Note.getToneStringWithoutOctave(tone.getValue()) == Note.getToneStringWithoutOctave(n.getValue())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isRoot(Note note, Chord chord) {
		return Note.getToneStringWithoutOctave(note.getValue()) == Note.getToneStringWithoutOctave(chord.getRoot().getValue());
	}
	
	public static boolean isThird(Note note, Chord chord) {
		return Note.getToneStringWithoutOctave(note.getValue()) == Note.getToneStringWithoutOctave(chord.getNotes()[(chord.getInversion() + 1) % 3].getValue());
	}
	
	public static boolean isFifth(Note note, Chord chord) {
		return Note.getToneStringWithoutOctave(note.getValue()) == Note.getToneStringWithoutOctave(chord.getNotes()[(chord.getInversion() + 2) % 3].getValue());
	}
	
	public static int asMember(Note note, Chord chord) {
		if (isRoot(note, chord)) {
			return ROOT;
		} else if (isThird(note, chord)) {
			return THIRD;
		} else if (isFifth(note, chord)) {
			return FIFTH;
		}
	
		return -1;
	}
}
