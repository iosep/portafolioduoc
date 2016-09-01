/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WFBScontroller;

import WFBSmodel.*;
/**
 *
 * @author iosep
 */
public class UsuarioController 
{
    public boolean login(String user, String pass)
    {
        boolean bul = false;
        UsuarioModel model = new UsuarioModel();
        bul = model.findUserPass(user, pass);
        return bul;
    }
}
