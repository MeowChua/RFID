package com.example.sdksamples;

import BUS.Tag_BUS;
import DTO.RFID;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class TagReportListenerImplementation implements TagReportListener {

    public static Set<RFID> listScan;
    Map<String, RFID> map1;
    Map<String, Integer> map;
    DefaultTableModel model1, model2;
    Set<RFID> tempList;
    Tag_BUS tagBUS = new Tag_BUS();

    public TagReportListenerImplementation() {
    }

    public TagReportListenerImplementation(Map<String, RFID> map1, Map<String, Integer> map, DefaultTableModel model1, DefaultTableModel model2) {
        this.map1 = map1;
        this.map = map;
        this.model1 = model1;
        this.model2 = model2;
    }

    @Override
    public void onTagReported(ImpinjReader reader, TagReport report) {
        List<Tag> tags = report.getTags();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Thread thread = new Thread() {
            @Override
            public void run() {
                for (Tag t : tags) {

                    String epc = t.getEpc().toString();
                    String time = dtf.format(now);
                    String gate = String.valueOf(t.getAntennaPortNumber());

                    listScan.add(new RFID(epc, time, gate));
                    //Loc datas
                    listScan.forEach((ls) -> {
                        map1.put(ls.getTagID(), new RFID(ls.getDate(), ls.getGate()));
                        //model.addRow(new Object[]{ls.getTagID(), ls.getDate(), ls.getGate()});
                    });
                    model1.setRowCount(0);

                    for (Map.Entry<String, RFID> entry : map1.entrySet()) {
                        //tempList=new HashSet<>();
                        String k = entry.getKey();
                        RFID v = entry.getValue();
                        model1.addRow(new Object[]{k, v.getDate(), v.getGate()});
                        tempList.add(new RFID(k, v.getDate(), v.getGate()));
                        for (RFID s : tempList) {
                            System.out.println(s.getTagID());
                        }

                    }

                    System.out.print(" EPC: " + t.getEpc().toString());

                    if (reader.getName() != null) {
                        System.out.print(" Reader_name: " + reader.getName());
                    } else {
                        System.out.print(" Reader_ip: " + reader.getAddress());
                    }

                    if (t.isAntennaPortNumberPresent()) {
                        System.out.print(" antenna: " + t.getAntennaPortNumber());
                    }

                    if (t.isFirstSeenTimePresent()) {
                        System.out.print(" first: " + t.getFirstSeenTime().ToString());
                    }

                    if (t.isLastSeenTimePresent()) {
                        System.out.print(" last: " + t.getLastSeenTime().ToString());
                    }

                    if (t.isSeenCountPresent()) {
                        System.out.print(" count: " + t.getTagSeenCount());
                    }

                    if (t.isRfDopplerFrequencyPresent()) {
                        System.out.print(" doppler: " + t.getRfDopplerFrequency());
                    }

                    if (t.isPeakRssiInDbmPresent()) {
                        System.out.print(" peak_rssi: " + t.getPeakRssiInDbm());
                    }

                    if (t.isChannelInMhzPresent()) {
                        System.out.print(" chan_MHz: " + t.getChannelInMhz());
                    }

                    if (t.isRfPhaseAnglePresent()) {
                        System.out.print(" phase angle: " + t.getPhaseAngleInRadians());
                    }

                    if (t.isFastIdPresent()) {
                        System.out.print("\n     fast_id: " + t.getTid().toHexString());

                        System.out.print(" model: "
                                + t.getModelDetails().getModelName());

                        System.out.print(" epcsize: "
                                + t.getModelDetails().getEpcSizeBits());

                        System.out.print(" usermemsize: "
                                + t.getModelDetails().getUserMemorySizeBits());
                    }

                    System.out.println("");
                }
                showProduct();
            }
        };
        thread.start();
    }

    private void showProduct() {
        model2.setRowCount(0);
        for (RFID ls : tempList) {
            String element = tagBUS.query_product_id(ls.getTagID());
            System.out.println(element);
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
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
