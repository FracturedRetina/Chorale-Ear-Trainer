package com.eshimoniak.choraleeartrainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfugue.theory.Scale;

public class ChordProgressionFactory {
	//V(7) -> I
	//vii*(7) -> I
	public static final int CADENCE_AUTHENTIC = 0;
	//V(7) -> I
	public static final int CADENCE_PERFECT_AUTHENTIC = 2;
	//IV -> I
	public static final int CADENCE_PLAGAL = 4;
	
	//V -> vi
	//V -> ?
	public static final int CADENCE_DECEPTIVE = 1;
	//? -> V
	public static final int CADENCE_HALF = 3;
	//iv6 -> V
	public static final int CADENCE_PHRYGIAN_HALF = 5;
	
	
	public static String getBetterChordProg(Scale scale, int length) {
		List<String> chords = new ArrayList<String>();
		Random r = new Random();
		
		if (scale == Scale.MAJOR) {
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					int ri = r.nextInt(2);
					
					if (ri == 0) {
						chords.add("I");
					} else if (ri == 1) {
						chords.add("V");
					}
				} else {
					String next = chords.get(0);
					
					if (next == "I") {
						int ri = r.nextInt(6);
						
						if (ri >= 0 && ri <= 1) {
							chords.add(0, "IV");
						} else if (ri >= 2 && ri <= 4) {
							chords.add(0, "V");
						} else if (ri == 5) {
							chords.add(0, "viio");
						}
					} else if (next == "ii") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else if (ri == 1) {
							chords.add(0, "iii");
						} else if (ri == 2) {
							chords.add(0, "IV");
						}
					} else if (next == "iii") {
						int ri = r.nextInt(10);
						
						if (ri != 0) {
							chords.add(0, "I");
						} else {
							chords.add(0, "viio");
						}
					} else if (next == "IV") {
						int ri = r.nextInt(2);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else if (ri == 1) {
							chords.add(0, "iii");
						}
					} else if (next == "V") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else if (ri == 1) {
							chords.add(0, "ii");
						} else if (ri == 2) {
							chords.add(0, "IV");
						}
					} else if (next == "vi") {
						int ri = r.nextInt(2);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else {
							chords.add(0, "V");
						}
					} else if (next == "viio") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else if (ri == 1) {
							chords.add(0, "ii");
						} else if (ri == 2) {
							chords.add(0, "IV");
						}
					}
				}
			}
		} else if (scale == Scale.MINOR) {
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					int ri = r.nextInt(2);
					
					if (ri == 0) {
						chords.add("i");
					} else if (ri == 1) {
						chords.add("V");
					}
				} else {
					String next = chords.get(0);
					
					if (next == "i") {
						int ri = r.nextInt(6);
						
						if (ri >= 0 && ri <= 1) {
							chords.add(0, "iv");
						} else if (ri >= 2 && ri <= 4) {
							chords.add(0, "V");
						} else if (ri == 5) {
							chords.add(0, "viio");
						}
					} else if (next == "iio") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "i");
						} else if (ri == 1) {
							chords.add(0, "III");
						} else if (ri == 2) {
							chords.add(0, "iv");
						}
					} else if (next == "III") {
						int ri = r.nextInt(2);
						
						if (ri == 0) {
							chords.add(0, "i");
						} else if (ri == 1) {
							chords.add(0, "VII");
						}
					} else if (next == "iv") {
						int ri = r.nextInt(2);
						
						if (ri == 0) {
							chords.add(0, "i");
						} else if (ri == 1) {
							chords.add(0, "III");
						}
					} else if (next == "V") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "i");
						} else if (ri == 1) {
							chords.add(0, "iio");
						} else if (ri == 2) {
							chords.add(0, "iv");
						}
					} else if (next == "VI") {
						int ri = r.nextInt(2);
						
						if (ri == 0) {
							chords.add(0, "i");
						} else {
							chords.add(0, "V");
						}
					} else if (next == "viio") {
						int ri = r.nextInt(3);
						
						if (ri == 0) {
							chords.add(0, "I");
						} else if (ri == 1) {
							chords.add(0, "ii");
						} else if (ri == 2) {
							chords.add(0, "IV");
						}
					} else if (next == "VII") {
						int ri = r.nextInt(10);
						
						if (ri != 0) {
							chords.add(0, "I");
						} else {
							chords.add(0, "iv");
						}
					}
				}
			}
		}
		
		String retStr = "";
		
		for (String c : chords) {
			retStr += c + " ";
		}
		
		retStr.trim();
		
		return retStr;
	}
}