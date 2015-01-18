/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metatagorganiser;

import metatagorganiser.core.MetaTagOrganiser;
import metatagorganiser.core.MetaTagOrganiserModel;
import metatagorganiser.gui.UIController;

/**
 *
 * @author shobana sasi
 */
public class Main {

    public static final String PROGRAM_VERSION = "0.7";
    public static final String PROGRAM_NAME = "MetaTagOrganiser";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        MetaTagOrganiserModel model = new MetaTagOrganiserModel();
//        
//        try {
//            model.consumeArguments(args);
//        } catch (Exception iae) {
//            System.out.println(iae.getMessage());
//            MetaTagOrganiser.printHelpAndExit();
//            System.exit(0);
//        }
//
//        new MetaTagOrganiser(model).execute();
        
        UIController controller = new UIController();
        controller.setupAndShowUI();
    }
    
}
