/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ProductDAO;
import DTO.Khoa;
import DTO.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class ProductBUS {
    public List<Product> dssv;
    ProductDAO data = new ProductDAO();
    public ProductBUS(){
        
    }
    public List<Product> getListV(){
        if(dssv==null) dssv=new ArrayList<>();
        dssv=data.getListProduct();
        return dssv;
    }
    public static void docDSTV(){
        //if(dstv==null) dstv=new ArrayList<>();
        //dstv=data.getListTV();
    }
    public void them(Product tv){
        data.them(tv);
        dssv.add(tv);
    }
    public void sua(Product tv,String dk){
        
        data.sua(tv,dk);
    }
    public void xoa(Product tv){
        data.xoa(tv);
    }
    public List<Khoa> fill_cbb() {
        List<Khoa> list = new ArrayList<>();
        list = data.fill_cbb();
        return list;
    }
}
