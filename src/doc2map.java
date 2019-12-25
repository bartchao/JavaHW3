import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*Need StopWords and Content
Remove stopwords in content
and calculate all vocabulary showed in content*/
public class doc2map {
	TreeMap<String, Integer> map = null;
	String[] story;
	String stopwordsPath;

	doc2map(String[] story, String stopwordsPath) {
		this.story = story;
		this.stopwordsPath = stopwordsPath;

	}

	public TreeMap<String, Integer> getMap() {
		map = calc(stopWords(story, stopwordsPath));
		return this.map;
	}

	// Remove StopWords in all content
	private ArrayList<String> stopWords(String[] str, String stopWordsPath) {
		File file = new File(stopWordsPath);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Missing stopwords file,Exit.");
			System.exit(0);
		}
		ArrayList<String> stopwords = new ArrayList<String>();
		while (sc.hasNext()) {
			stopwords.add(sc.next());
		}
		sc.close();
		Collections.sort(stopwords);
		ArrayList<String> allWords = new ArrayList<String>();
		for (int k = 0; k < str.length; k++) {
			ArrayList<String> docWords = new ArrayList(Arrays.asList(str[k].split("[^a-z0-9]")));
			docWords.removeAll(stopwords);
			allWords.addAll(docWords);
		}
		return allWords;

	}

	// Calculate words showed in content
	private TreeMap<String, Integer> calc(java.util.List<String> allWords) {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		Set<String> wordSet = map.keySet();
		for (String s : allWords) {
			if (s.matches("[a-z]+")) {
				if (wordSet.contains(s)) {
					int number = map.get(s);
					number++;
					map.put(s, number);
				} else {
					map.put(s, 1);
				}
			}
		}
		return map;
	}
}
