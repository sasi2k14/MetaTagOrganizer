package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.sax.BodyContentHandler;

public class TikaJpegParseTest {

	public static void main(String[] args) throws Exception {
		// detecting the file type
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\Sasi Kumar\\Downloads\\IMG_20150207_143941.jpg"));
		ParseContext pcontext = new ParseContext();

		// Jpeg Parse
		JpegParser JpegParser = new JpegParser();
		JpegParser.parse(inputstream, handler, metadata, pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + ": " + metadata.get(name));
		}

	}

}
