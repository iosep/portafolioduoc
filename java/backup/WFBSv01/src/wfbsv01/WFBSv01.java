/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfbsv01;

import WFBSview.Login;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author iosep
 */
public class WFBSv01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WFBSview.Login view = new Login();
        view.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        view.setLocation(dim.width/2-view.getSize().width/2, dim.height/2-view.getSize().height/2);
        //view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    
}
