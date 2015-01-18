/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metatagorganiser.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author shobana sasi
 */
public class ExifToolParser {
    private static String[] command = new String[]{".\\res\\exiftool.exe", "-createDate", "<file-path>"};
    private static final String NEW_LINE = "\n";
    private static final String MODIFIED_DATE_LINE = "Create Date                     :";
    
    public String parseLastModifiedDate(File file) throws IOException {
        StringBuffer procOutput = executeAndReadMediaInfoProcess(file.getAbsolutePath());
        String modifiedDate = findModifiedDateLine(procOutput.toString());
        return customiseModifiedDate(modifiedDate);
    }

    private  StringBuffer executeAndReadMediaInfoProcess(String absolutePath) throws IOException {
        command[2] = absolutePath;
        
        Process process = null;

        try {
            process = Runtime.getRuntime().exec(command);            
            return readProcessOutput(process);
        } catch (IOException ex) {
            String msg = "Error executing command for:" + absolutePath;
            msg += "\n" + ex.getMessage();
            
            throw new IOException(msg);
        } finally {
            
        }
    }

    private  StringBuffer readProcessOutput(Process process) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line ;
        StringBuffer processOutput = new StringBuffer();
        
        while((line = bufferedReader.readLine()) != null) {
            processOutput.append(line);
            processOutput.append(NEW_LINE);
        }
        
        return processOutput;
    }
    
    private  String findModifiedDateLine(String processOutput) {
        StringTokenizer tokenizer = new StringTokenizer(processOutput, NEW_LINE);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(token.contains(MODIFIED_DATE_LINE)) {
                token = token.replace(MODIFIED_DATE_LINE, "");
                token = token.trim();
                
                return token;
            }
        }
        return "";
    }

    public String customiseModifiedDate(String modifiedDate) {
//        Create Date                     : 2011:11:11 14:12:48
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        try {
            Date parse = dtFormat.parse(modifiedDate);
            dtFormat.applyPattern("yyyy-MMM-dd HH.mm.ss a");
            
            return dtFormat.format(parse);
        } catch (ParseException ex) {
            ex.printStackTrace();
            return modifiedDate;
        }
    }

    public void parseLastModifiedDate(String dMy_DocumentsMy_PicturesShrutiVID_2013021) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
