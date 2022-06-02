/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DNDAO;

/**
 *
 * @author MSI
 */
public class DNBUS {
    public DNBUS(){
        
    }
    public void DNhap(String tk, String pass){
        DNDAO data = new DNDAO();
        data.DNhap(tk, pass);
    }
}
