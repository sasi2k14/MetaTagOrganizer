/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class SingleFileTest {
    
    private File srcTestFile;
    private String testFileDir = "single-file-test";
    private String testFileDirPathName;
    
    private String srcTestFileName = "file1.jpg";
    private String srcTestFilePathName;
    
    private String destTestFileName = "2008-Oct-19 17.18.52 PM.jpg";
    
    public SingleFileTest() {
    }
    
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
    }
    
    @Test
    public void test() throws FileNotFoundException {
        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
        model.consumeArguments(new String[]{srcTestFilePathName});
        
        new MetaTagOrganiser(model).execute();
        
        assertTrue(srcTestFile.exists() == false);
        assertTrue(srcTestFile.getParentFile().listFiles().length == 1);
    }
    
    @After
    public void tearDown() throws IOException {
        File srcFile = new File(testFileDirPathName).listFiles()[0];        
        FileUtils.moveFile(srcFile, srcTestFile);
    }
    
}