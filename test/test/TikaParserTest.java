package test;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Sasi Kumar on 2/9/2015.
 */
public class TikaParserTest {

    public static final Path RESORCES = Paths.get("res", "test", "tika");
    public static final String JPG = "";

    @Test
    public void testExtractMetadata_ValidJPGFile() {
        Path jpgFile = RESORCES.resolve(JPG);
        String info = TikaParser.getInstance().extractMetaData(TikaParser.CREATED_DATE,  jpgFile);

    }

    @Test
    public void testExtractMetadata_ValidMP4File() {

    }

    @Test
    public void testExtractMetadata_OtherFiles() {

    }

    @Test
    public void testExtractMetadata_CorruptMediaFile() {

    }
}
