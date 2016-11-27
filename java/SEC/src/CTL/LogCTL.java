/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTL;

import DAL.LogDAL;

/**
 *
 * @author iosep
 */
public class LogCTL {

    private final LogDAL ld = new LogDAL();

    public void addLog(String ip) {
        ld.addLog(ip);
    }
}
