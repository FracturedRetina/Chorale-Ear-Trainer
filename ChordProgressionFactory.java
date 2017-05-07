package com.eshimoniak.choraleeartrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	
	/**Reversed flow of root position chords in major**/
	public static Map<String, String[]> majorFlow = new HashMap<String, String[]>();
	static {
		majorFlow.put("I",    new String[] {"IV", "V",   "viio"});
		majorFlow.put("ii",   new String[] {"I",  "iii", "IV"});
		majorFlow.put("iii",  new String[] {"I"});
		majorFlow.put("IV",   new String[] {"I",  "iii", "vi"});
		majorFlow.put("V",    new String[] {"I",  "ii",  "IV"});
		majorFlow.put("vi",   new String[] {"I",  "iii", "V"});
		majorFlow.put("viio", new String[] {"I",  "ii",  "IV", "V"});
	}
	
	/**Reversed flow of root position chords in minor**/
	public static Map<String, String[]> minorFlow = new HashMap<String, String[]>();
	static {
		minorFlow.put("i",    new String[] {"iv", "V",   "viio"});
		minorFlow.put("I",    new String[] {"iv", "V",   "viio"});
		minorFlow.put("iio",  new String[] {"i",  "III", "iv"});
		minorFlow.put("III",  new String[] {"i",  "VII"});
		minorFlow.put("iv",   new String[] {"i",  "III", "VI"});
		minorFlow.put("V",    new String[] {"i",  "iio", "iv"});
		minorFlow.put("VI",   new String[] {"i",  "III", "V"});
		minorFlow.put("VII",  new String[] {"i"});
		minorFlow.put("viio", new String[] {"i",  "iio", "iv", "V"});
	}
	
	
	public static String getChordProg(Scale scale, int length) {
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
					String[] possible = majorFlow.get(chords.get(0));
					chords.add(0, possible[r.nextInt(possible.length)]);
				}
			}
		} else if (scale == Scale.MINOR) {
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					int ri = r.nextInt(2);
					
					if (ri == 0) {
						chords.add("i");
					} else if (ri == 1) {
						chords.add("I");
					} else if (ri == 2) {
						chords.add("V");
					}
				} else {
					String[] possible = minorFlow.get(chords.get(0));
					chords.add(0, possible[r.nextInt(possible.length)]);
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