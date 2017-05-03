package com.eshimoniak.choraleeartrainer.chorale;

import org.jfugue.theory.Chord;
import org.jfugue.theory.Note;

import com.eshimoniak.choraleeartrainer.ChordUtil;

public class InnerVoices {
	public static Note[] getInnerVoices(Note soprano, Note bass, Chord chord) {
		Note alto = null;
		Note tenor = null;
		int mid = (soprano.getValue() + bass.getValue()) / 2;
		boolean hasRoot = ChordUtil.isRoot(soprano, chord) || ChordUtil.isRoot(bass, chord);
		boolean hasThird = ChordUtil.isThird(soprano, chord) || ChordUtil.isThird(bass, chord);
		boolean hasFifth = ChordUtil.isFifth(soprano, chord) || ChordUtil.isFifth(bass, chord);
		
		//Alto
		for (int i = mid; i <= soprano.getValue() + 1; i++) {
			Note n = new Note(Note.getToneString((byte) i));
			
			if (ChordUtil.isChordTone(n, chord)) {
				int type = ChordUtil.asMember(n, chord);
				
				if (type == ChordUtil.ROOT && !hasRoot) {
					alto = n;
					hasRoot = true;
					break;
				} else if (type == ChordUtil.THIRD && !hasThird) {
					alto = n;
					hasThird = true;
					break;
				} else if (type == ChordUtil.FIFTH && !hasFifth) {
					alto = n;
					hasFifth = true;
					break;
				} else if (hasRoot && hasThird && hasFifth) {
					alto = n;
					break;
				}
			}
		}
		//Tenor
		for (int i = mid; i >= bass.getValue() - 1; i--) {
			Note n = new Note(Note.getToneString((byte) i));
			
			if (ChordUtil.isChordTone(n, chord)) {
				int type = ChordUtil.asMember(n, chord);
				
				if (type == ChordUtil.ROOT && !hasRoot) {
					tenor = n;
					hasRoot = true;
					break;
				} else if (type == ChordUtil.THIRD && !hasThird) {
					tenor = n;
					hasThird = true;
					break;
				} else if (type == ChordUtil.FIFTH && !hasFifth) {
					tenor = n;
					hasFifth = true;
					break;
				} else if (hasRoot && hasThird && hasFifth) {
					tenor = n;
					break;
				}
			}
		}
		
		return new Note[] {alto, tenor};
	}
}