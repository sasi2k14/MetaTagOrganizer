/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metatagorganiser.core;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metatagorganiser.Main;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author shobana sasi
 */
public class MetaTagOrganiser {

    MetaTagOrganiserModel model;
    
    public MetaTagOrganiser(MetaTagOrganiserModel model) {
        this.model = model;
    }
    
    private void printVersionAndExit() {
        System.out.print(Main.PROGRAM_NAME);
        System.out.println(" " + Main.PROGRAM_VERSION);
        System.exit(0);
    }

    public static void printHelpAndExit() {
        System.out.println("Command Usage:");
        System.out.println("--------------");
        System.out.println("java -jar metatagorganiser.jar " +
                           "[--version]" +
                           "[[src directory/file to rename] [dest directory]]");
    }

    private enum ACTION {
        SHOW_VERSION,
        PRINT_HELP,
        PARSE_FOLDER,
        PARSE_FILES
    };
    
    private void executeAction(ACTION action) {
       switch(action){
           case SHOW_VERSION:
               printVersionAndExit();
               break;
           case PRINT_HELP:
               printHelpAndExit();
               break;
           case PARSE_FOLDER:
               List<File> mediaFiles = scanMediaFilesIn(model.getPathToScan());
               renameFilesBasedOnLastModifiedDate(mediaFiles, model.getDestinationDir());
               break;
           case PARSE_FILES:
               renameFilesBasedOnLastModifiedDate(model.getSelectedFiles(), 
                                                  model.getDestinationDir() );
               break;
           default:
               printHelpAndExit();
       }
    }
    
    public void execute() {
        if(model.isIsShowVersion()) {
            executeAction(ACTION.SHOW_VERSION);
        } else if( model.isIsShowHelp() ) {
            executeAction(ACTION.PRINT_HELP);
        } else {
            executeAction(ACTION.PARSE_FOLDER);
        }
    }
    
    public static List<File> scanMediaFilesIn(File path){
        if(!path.isDirectory()){
            return Arrays.asList(new File[]{path});
        }
        
        return Arrays.asList(
            path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                    return isValidFile(name);
            }})
        );
    }
    
    public void renameFilesBasedOnLastModifiedDate(List<File> files, String destinationDir) {
        ExifToolParser parser = new ExifToolParser();
        
        for(File srcFile : files){
            try {
                String modifiedDate = parser.parseLastModifiedDate(srcFile);
                System.out.println(srcFile.getName() + "\t" + modifiedDate);
                
                File destinationFile = null;
                if(destinationDir != null) {
                    destinationFile = prepareNewFileName(srcFile, modifiedDate, destinationDir);
                } else {
                    destinationFile = prepareNewFileName(srcFile, modifiedDate, null);
                }
                
                if(destinationFile.exists()){
                    System.out.println("Destination file exists!, skipping file" + destinationFile.getName());
                }
                else {
                    if(destinationDir != null) {
                        copyFile(srcFile, destinationFile);                                        
                    } else {
                        moveFile(srcFile, destinationFile);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MetaTagOrganiser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void moveFile(File srcFile, File destFile) throws IOException {        
        System.out.println("Moving:" + srcFile.getAbsolutePath() + "->" 
                            + destFile.getAbsolutePath());
        FileUtils.moveFile(srcFile, destFile);
    }
    
    private void copyFile(File srcFile, File destFile) throws IOException {
        System.out.println("Copying:" + srcFile.getAbsolutePath() + "->" 
                            + destFile.getAbsolutePath());
        FileUtils.copyFile(srcFile, destFile);
    }
    
    private File prepareNewFileName(File srcFile, String fileName, String destinationDir){
        String parentDir = null;
        if(destinationDir == null) {
            parentDir = srcFile.getParent();
        } else {
            parentDir = destinationDir;
        }
        
        String fileExtn  = FilenameUtils.getExtension(srcFile.getName());
        
        String destination = parentDir + File.separator + fileName + "." + fileExtn;
        return new File(destination);
    }
    
    public static boolean isValidFile(String name) {
        name = name.toLowerCase();
                    if (name.endsWith("jpg") || 
                        name.endsWith("3gp")  || 
                        name.endsWith("mp4")  ||
                        name.endsWith("mpg")  ||
                        name.endsWith("avi")
                        ) {
                        return true;
                    }
                    return false;
    }
}
