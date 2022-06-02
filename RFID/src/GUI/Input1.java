/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.Tag_BUS;
import DTO.RFID;
import static GUI.TagRFID.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class Input1 {
    DefaultTableModel model;
    Map<String, RFID> map;

    public Input1(DefaultTableModel model, Map<String, RFID> map) {
        this.model = model;
        this.map = map;
    }
    
    void scan() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Scanner sc = new Scanner(System.in);
        long start = System.currentTimeMillis();
        long end = start + 20 * 1000;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (System.currentTimeMillis() < end) {
                    System.out.println("Nhap tag id: ");
                    String tagid = sc.nextLine();
                    String date = dtf.format(now);
                    System.out.println("Nhap gate: ");
                    String gate = sc.nextLine();
                    map.put(tagid, new RFID(date, gate));
                    model.setRowCount(0);

                    for (Map.Entry<String, RFID> entry : map.entrySet()) {
                        //tempList=new HashSet<>();
                        String k = entry.getKey();
                        RFID v = entry.getValue();
                        model.addRow(new Object[]{k, v.getDate(), v.getGate()});

                    }
                }

            }
        };
        thread.start();

    }

}
