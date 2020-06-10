import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class LyricAnalyzer {
	private static int wordPosition = 0;

	public static void main(String[] args) throws IOException {
		Map<String, ArrayList<Integer>> myMap = new HashMap<>();
		displayLyrics(myMap);
		add(myMap);
		displayWords(myMap);
	}

	// determine if the given lyric word is in the map
	public static void add(Map<String, ArrayList<Integer>> map) throws IOException {
		String fileName = "highhopes2.txt";
		File file = new File(fileName);
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		// reading file
		while (true) {
			line = buffer.readLine();
			if (line == null || line.length() == 0)
				break;
			// System.out.println(line);
			String[] tokens = line.split(" ");
			for (int i = 0; i < tokens.length; i++) {
				String word = tokens[i].toLowerCase();
				ArrayList<Integer> pos = new ArrayList<Integer>();
				if (map.containsKey(word)) {
					pos = map.get(word);
					// finds the end of the line
					if (i == tokens.length - 1) {
						wordPosition = -wordPosition; // negate current position before
						pos.add(wordPosition - 1); // to get the right current position(negative) we subtracted by one
						wordPosition = Math.abs(wordPosition); // makes negative to positive position
						map.put(word, pos);
						wordPosition++;
					} else {
						pos.add(++wordPosition);
						map.put(word, pos);
					}
				} else {
					if (i == tokens.length - 1) {
						wordPosition = -wordPosition; // negate current position before
						pos.add(wordPosition - 1); // to get the right current position(negative) we subtracted by one
						wordPosition = Math.abs(wordPosition); // makes negative to positive position
						map.put(word, pos);
						wordPosition++;
					} else {
						pos.add(++wordPosition);
						map.put(word, pos);
					}
				}
			}
		}
	}

	// display the words of the song along with the word positions (one word per
	// line in alphabetical order)
	public static void displayWords(Map<String, ArrayList<Integer>> myMap) {
		/// *
		Set<String> keys = myMap.keySet();
		System.out.printf("%nMap contains:%nWord\t\tWord Positions%n");
		TreeSet<String> sortedKeys = new TreeSet<>(keys);
		for (String key : sortedKeys)
			System.out.printf("%-10s\t%s%n", key, myMap.get(key));
	}

	// display the lyrics of the song stored in the map
	public static void displayLyrics(Map<String, ArrayList<Integer>> map) throws IOException {
		String fileName = "highhopes2.txt";
		File file = new File(fileName);
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		// reading file
		while (true) {
			line = buffer.readLine();
			if (line == null || line.length() == 0)
				break;
			System.out.println(line);
		}
	}
}