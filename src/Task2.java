import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class Task2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		extractDoc o1 = new extractDoc("docset1.txt");
		String[] content = o1.extractDocOnly();
		String[] docNo = o1.extractDocNo();
		doc2map o2 = new doc2map(content, "stop_words.txt");
		TreeMap<String, Integer> finalWords = o2.getMap();
		try {
			bw = new BufferedWriter(new FileWriter("posting.csv"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int lines = 0;
		for (Entry<String, Integer> entry : finalWords.entrySet()) {
			// calc freq
			int[] freq = new int[content.length];
			for (int i = 0; i < content.length; i++) {
				ArrayList<String> docWords = new ArrayList<String>(Arrays.asList(content[i].split("[^a-z0-9]")));
				for (String s : docWords) {
					if (s.equals(entry.getKey())) {
						freq[i]++;
					}
				}
			}
			// Process Doc Freq and DocNo output string
			String str1 = "";
			str1 += Arrays.toString(freq);
			ArrayList<String> foundNo = new ArrayList<String>();
			for (int k = 0; k < freq.length; k++) {
				if (freq[k] > 0) {
					foundNo.add(docNo[k]);
				}
			}
			String str2 = "";
			str2 += Arrays.toString(foundNo.toArray());
			// Write into csv
			try {
				lines++;
				bw.write(lines + "," + entry.getKey() + "," + entry.getValue() + "," + "Document Freq:" + ","
						+ StringUtils.substringBetween(str1, "[", "]") + "," + "DocNO:" + ","
						+ StringUtils.substringBetween(str2, "[", "]"));
				bw.newLine();
				System.out.println(lines + " " + entry.getKey() + " " + entry.getValue() + " " + "Document Freq:" + str1
						+ "+DocNO:" + str2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			bw.close();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Output the result to CSV successfully!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
