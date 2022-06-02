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
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class Input {

    //public static Set<RFID> listScan1 = new HashSet<>();;
    Map<String, RFID> map1;
    Map<String, Integer> map;

    DefaultTableModel model1, model2;
    Set<String> tempList;
    Tag_BUS tagBUS = new Tag_BUS();
    int count=0;

    public Input(Map<String, RFID> map1, Map<String, Integer> map, DefaultTableModel model1, DefaultTableModel model2) {
        this.map1 = map1;
        this.map = map;
        this.model1 = model1;
        this.model2 = model2;
    }

    public Input(Map<String, RFID> map, DefaultTableModel model1, DefaultTableModel model2) {
        this.map1 = map;
        this.model1 = model1;
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
                    //tempList = new HashSet<>();
                    System.out.println("Nhap tag id: ");
                    String tagid = sc.nextLine();
                    String date = dtf.format(now);
                    System.out.println("Nhap gate: ");
                    String gate = sc.nextLine();
                    map1.put(tagid, new RFID(date, gate));
                    //listScan1.add(new RFID(tagid, date, gate));
                    //Loc datas
                    //listScan1.forEach((ls) -> {
                    //map1.put(ls.getTagID(), new RFID(ls.getDate(), ls.getGate()));
                    //model.addRow(new Object[]{ls.getTagID(), ls.getDate(), ls.getGate()});
                    //});
                    model1.setRowCount(0);

                    for (Map.Entry<String, RFID> entry : map1.entrySet()) {
                        tempList = new HashSet<>();
                        String k = entry.getKey();
                        RFID v = entry.getValue();
                        model1.addRow(new Object[]{k, v.getDate(), v.getGate()});
                        tempList.add(k);

                        for (String s : tempList) {
                            System.out.println(s);
                        }
                    }

                    showProduct();
                }
                //showProduct();
            }
        };
        thread.start();

    }

    private void showProduct() {       
        model2.setRowCount(0);
        for (String ls : tempList) {
            String element = tagBUS.query_product_id(ls);
            
            System.out.println(element);
            if (map.containsKey(element)) {
                map.put(element, map.get(element)+1);
            } else {
                map.put(element, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            int v = entry.getValue();
            System.out.println(k + " + " + v);
            model2.addRow(new Object[]{k, v});
        }

    }
}
