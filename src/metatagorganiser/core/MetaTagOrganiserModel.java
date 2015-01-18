/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metatagorganiser.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shobana sasi
 */
public class MetaTagOrganiserModel {
    private String sourcePath;
    private String destinationDir;
    private boolean isShowVersion;
    private boolean isShowHelp;
    private List<File> selectedFiles = new ArrayList<File>();

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        this.destinationDir = destinationDir;
    }

    public boolean isIsShowHelp() {
        return isShowHelp;
    }

    public void setIsShowHelp(boolean isShowHelp) {
        this.isShowHelp = isShowHelp;
    }

    public File getPathToScan() {
        return new File(sourcePath);
    }

    public void setDirectoryToScan(File directoryToScan) {
        this.sourcePath = directoryToScan.getAbsolutePath();
    }

    public List<File> getSelectedFiles() {
        return selectedFiles;
    }

    public void setSelectedFiles(List<File> selectedFiles) {
        this.selectedFiles = selectedFiles;
    }
    
    public void consumeArguments(String args[]) throws IllegalArgumentException, FileNotFoundException {
        if(args.length == 0) {
            isShowHelp = true;
            return;
        }
        
        if(args.length == 1) {

            String arg0 = args[0];
            if(arg0.equals("--version")) {
                isShowVersion = true;
            } else {
//                consumeAllFileArgs();
                if(!checkFilePath(arg0)) {
                    if(!checkDirectoryPath(arg0)){
                        throw new IllegalArgumentException("File type is not valid");  
                    }
                }
            }
            
            sourcePath = arg0;
            
        } else if(args.length == 2) {
            String srcPath = args[0];
            String destPath = args[1];
            try {
                
                if(!checkFilePath(srcPath)) {
                    throw new IllegalArgumentException("File type is not valid");
                }
                
                checkDirectoryPath(destPath);
                
                sourcePath = srcPath;
                destinationDir = destPath;
                
            } catch (FileNotFoundException ex) {
                throw ex;
            }
        }
    }
    
    public boolean isIsShowVersion() {
        return isShowVersion;
    }

    public void setIsShowVersion(boolean isShowVersion) {
        this.isShowVersion = isShowVersion;
    }

    private boolean checkFilePath(String path) throws FileNotFoundException {
        File file = new File(path);
        if(file.exists()) {
            return MetaTagOrganiser.isValidFile(path);
        }
        
        throw new FileNotFoundException("Path doesn't exist, " + path);
    }
    
    private boolean checkDirectoryPath(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.isDirectory()) {
            return false;
        }
        
        if(!file.exists()) {
            throw new FileNotFoundException("Path doesn't exist, " + path);
        }
        
        return true;
    }

    private void consumeAllFileArgs(String[] args) {
        
    }
}
