/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author MSI
 */
public class DNDAO {
    public static Connection conn=Connect.getConnect();
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;
    
    public  void DNhap(String user,String password){
         try {
             
             String sql = "select * from qltk where account=? and pass=?";
        
             ps=conn.prepareStatement(sql);
             ps.setString(1, user);
             ps.setString(2, password); 
             rs = ps.executeQuery();
         
             if(rs.next()){                 
                 
                 JOptionPane.showMessageDialog(null, "Đăng nhập thành công.");
                 Main DN = new Main();
                 DN.setVisible(true);
             }        
             else{
                JOptionPane.showMessageDialog(null, "Thông tin đăng nhập sai."); 
             }
         } catch (SQLException ex) {
             Logger.getLogger(DNDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
