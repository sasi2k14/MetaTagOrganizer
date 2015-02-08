package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.sax.BodyContentHandler;

public class TikaTest {

	public static void main(String[] args) throws Exception {
		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		String mp4 = "C:\\Users\\Sasi Kumar\\Downloads\\20150126_150449.mp4";
		FileInputStream inputstream = new FileInputStream(mp4 );
		ParseContext pcontext = new ParseContext();

		// Html parser
		MP4Parser MP4Parser = new MP4Parser();
		MP4Parser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:  :" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}
	}

}
