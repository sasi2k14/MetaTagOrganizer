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
public class FoldersTest {

    private String testFileDirName = "folder-test";
    private String testFileDirPathName;
    
    private File testFileDir;
    
    public FoldersTest() {
        
    }

    @Before
    public void setUp() {
        String resDir = "." + File.separator
                + "res" + File.separator
                + "test";

        testFileDirPathName = resDir + File.separator
                + testFileDirName;

        testFileDir = new File(testFileDirPathName);

        if (testFileDir.exists() == false) {
            throw new IllegalArgumentException("Unable to locate the test directory");
        }

        if(testFileDir.listFiles().length > 2) {
            throw new IllegalArgumentException("Invalid test directory");
        }
    }
    
    @Test
    public void test() throws FileNotFoundException {
        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
        model.consumeArguments(new String[]{testFileDirPathName});
        
        new MetaTagOrganiser(model).execute();
        
        assertEquals(testFileDir.listFiles().length, 2);
    }

    @After
    public void tearDown() throws IOException {
        File[] files = testFileDir.listFiles();
        
        FileUtils.moveFile(files[0], new File(testFileDirPathName + File.separator + "file1.jpg"));
        FileUtils.moveFile(files[1], new File(testFileDirPathName + File.separator + "file2.jpg"));
    }
}