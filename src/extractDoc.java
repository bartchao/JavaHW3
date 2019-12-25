import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
//Read source file and extract the content for use
public class extractDoc {
	String source;
	String content = "";

	extractDoc(String source) {
		this.source = source;
		fileToString(this.source);

	}
	
	//Read source file and turn into string
	private void fileToString(String filePath) {
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			System.out.println("Missing input file,Exit.");
			System.exit(0);
		}

	}
	//Split every news into an array(only extract content)
	public String[] extractDocOnly() {
		String[] story = content.split("</DOC>");
		for (int i = 0; i < story.length; i++) {
			story[i] = StringUtils.substringBetween(story[i], "<TEXT>", "</TEXT>").toLowerCase();
		}
		return story;
	}
	//Split DOCNO information from every news
	public String[] extractDocNo() {
		String[] content = this.content.split("</DOC>");
		String[] docNo = new String[content.length];
		for (int i = 0; i < content.length; i++) {
			docNo[i] = StringUtils.substringBetween(content[i], "<DOCNO>", "</DOCNO>");
		}
		return docNo;

	}

}
