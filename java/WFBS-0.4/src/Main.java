
import PL.Desktop;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iosep
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Desktop d = new Desktop();
        d.setExtendedState(d.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        d.setVisible(true);
        Dimension di = d.getSize();
        JDesktopPane dp = new JDesktopPane();
        d.add(dp);
        dp.setSize(di);
        dp.setVisible(true);
    }
    
}
