/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metatagorganiser.gui;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import metatagorganiser.core.MetaTagOrganiser;
import metatagorganiser.gui.panels.Form1;
import metatagorganiser.gui.panels.Form2;
import metatagorganiser.gui.panels.MainForm;

/**
 *
 * @author shobana sasi
 */
public class UIController {
    
    private MainForm main  = new MainForm();
    
    private Form1 form1 = new Form1(this);
    private Form2 form2 = new Form2(this);
    
    public void setupAndShowUI()
    {
        main.showPanel(form1);
        main.setLocationRelativeTo(null);
        
        main.setVisible(true);
    }
    
    public void onStartButtonClick()
    {
        main.showPanel(form2);
    }

    public void onStopButtonClick() 
    {
        main.showPanel(form1);
    }
    
    public void onShowFoldersButtonClick()
    {
        JFileChooser fileDialog = new JFileChooser(System.getProperty("user.home"));
        int saveDialogchoice = fileDialog.showOpenDialog(main);
        
        if(saveDialogchoice == JFileChooser.APPROVE_OPTION)
        {
            String text = "";
            File selectedFile = fileDialog.getSelectedFile();
            List<File> allMediaFiles = MetaTagOrganiser.scanMediaFilesIn(selectedFile);
            
            text = selectedFile.getAbsolutePath();
            
            form1.getLblTotalFiles().setText("" + allMediaFiles.size());
            form1.getLblSelectedFiles().setText(text);
        }
        else if(saveDialogchoice == JFileChooser.CANCEL_OPTION)
        {
            
        }
    }
    
    public void onShowFilesButtonClick()
    {
//        if(allMediaFiles.size() > 1)
//            {
//                text = allMediaFiles.get(0).getName() + " ...";
//            }
//            else
//            {
//                text = allMediaFiles.get(0).getName();
//            }
    }
}
