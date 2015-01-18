/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metatagorganiser.core.MetaTagOrganiser;
import metatagorganiser.core.MetaTagOrganiserModel;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shobana sasi
 */
public class SingleFileTestWithDestinationDir {
    
    private File srcTestFile;
    private String testFileDir = "single-file-test";
    private String testFileDirPathName;
    
    private String srcTestFileName = "file1.jpg";
    private String srcTestFilePathName;
    
    private String destTestDir;
        
    @Before
    public void setUp() {
        String resDir = "." + File.separator + 
                        "res" + File.separator + 
                        "test";
        
        testFileDirPathName = resDir + File.separator +
                                     testFileDir;
        
        String path = testFileDirPathName + File.separator +
                      srcTestFileName;
        
        srcTestFile = new File(path);
        
        if(!srcTestFile.exists()) {
            throw new IllegalArgumentException("Unable to locate the test file");
        }
        
        srcTestFilePathName = path;
        destTestDir = resDir + File.separator + "single-file-test-w-dest";
    }
    
    @Test
    public void test() throws FileNotFoundException {
        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
        model.consumeArguments(new String[]{srcTestFilePathName, destTestDir});

        new MetaTagOrganiser(model).execute();

        assertEquals(new File(destTestDir).listFiles().length, 1);
        assertEquals(new File(destTestDir).listFiles()[0].getName(), "2013-Sep-01 11.46.05 AM.jpg");
    }
    
    @After
    public void tearDown() throws IOException {
        File srcFile = new File(destTestDir).listFiles()[0];
        FileUtils.deleteQuietly(srcFile);
    }
    
}