/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ProductDAO;
import DAO.TagDAO;
import DTO.Khoa;
import DTO.Product;
import DTO.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class Tag_BUS {
        public List<Tag> dssv;
    TagDAO data = new TagDAO();
    public Tag_BUS(){
        
    }
    public List<Tag> getListV(){
        if(dssv==null) dssv=new ArrayList<>();
        dssv=data.getListTag();
        return dssv;
    }
    public static void docDSTV(){
        //if(dstv==null) dstv=new ArrayList<>();
        //dstv=data.getListTV();
    }
    public void them(Tag tv){
        data.them(tv);
        dssv.add(tv);
    }
    public void sua(Tag tv,String dk){
        
        data.sua(tv,dk);
    }
    public void xoa(Tag tv){
        data.xoa(tv);
    }
    public List<Tag> request(String tf){
        if(dssv==null) dssv=new ArrayList<>();
        dssv=data.request(tf);
        return dssv;
    }
    public String query_product_id(String id){
        return data.query_product_id(id);
    }

}
