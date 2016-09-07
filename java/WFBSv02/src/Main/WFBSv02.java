/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.MainView;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author GRUPO2
 */
public class WFBSv02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final MainView main;
        main = new MainView();
        main.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        main.setLocation(dim.width / 2 - main.getSize().width / 2, dim.height / 2 - main.getSize().height / 2);

    }

}
