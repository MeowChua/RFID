/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author MSI
 */
public class TaiKhoan {
     String tenDangNhap;
     String matKhau;
     String phanLoai;
    public TaiKhoan(){
        
    }
    public TaiKhoan(String tenDangNhap,String matKhau,String phanloai,String maNV,String ngayTao){
        this.tenDangNhap=tenDangNhap;
        this.matKhau=matKhau;
        this.phanLoai=phanloai;
        
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }
    
}