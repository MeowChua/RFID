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
public class RFID {
    String TagID;
    String date;
    String gate;

    public RFID(String date, String gate) {
        this.date = date;
        this.gate = gate;
    }

    public RFID(String TagID, String date, String gate) {
        this.TagID = TagID;
        this.date = date;
        this.gate = gate;
    }

    public String getTagID() {
        return TagID;
    }

    public RFID() {
    }

    public void setTagID(String TagID) {
        this.TagID = TagID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
    
}
