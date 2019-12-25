import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Task1 {
	public static void main(String[] args) {
		extractDoc o1 = new extractDoc("docset1.txt");
		String[] story = o1.extractDocOnly();
		doc2map o2 = new doc2map(story, "stop_words.txt");
		TreeMap<String, Integer> map = o2.getMap();
		savetoCSV(map);
	}
	
	private static void savetoCSV(TreeMap<String, Integer> map) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("docProcessOutput.csv"));
			int lines = 0;
			for (Entry<String, Integer> entry : map.entrySet()) {
				lines++;
				bw.write(lines + "," + entry.getKey() + "," + entry.getValue() + ",");
				System.out.println(lines + " " + entry.getKey() + " " + entry.getValue());
				bw.newLine();
			}
			bw.close();
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Output the result to CSV successfully!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
