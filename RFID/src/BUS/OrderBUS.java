/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.OrderDAO;
import DTO.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class OrderBUS {
    public List<Order> dsmh;
    OrderDAO data = new OrderDAO();
    public OrderBUS(){
        
    }
    public List<Order> getListV(){
        if(dsmh==null) dsmh=new ArrayList<>();
        dsmh=data.getListMH();
        return dsmh;
    }
    public static void docDSTV(){
        //if(dstv==null) dstv=new ArrayList<>();
        //dstv=data.getListTV();
    }
    public void them(Order tv){
        data.them(tv);
        dsmh.add(tv);
    }
    public void sua(Order tv,String dk){
        
        data.sua(tv,dk);
    }
    public void xoa(Order tv){
        data.xoa(tv);
    }
}
