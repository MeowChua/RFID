package com.example.sdksamples;

import BUS.Tag_BUS;
import DTO.RFID;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class TagReportListener1 implements TagReportListener {

    Set<RFID> listScan = new HashSet<>();
    DefaultTableModel model;
    Map<String, RFID> map;

    public TagReportListener1() {
    }

    public TagReportListener1(DefaultTableModel model, Map<String, RFID> map) {
        this.model = model;
        this.map = map;
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
                        map.put(ls.getTagID(), new RFID(ls.getDate(), ls.getGate()));
                        //model.addRow(new Object[]{ls.getTagID(), ls.getDate(), ls.getGate()});
                    });
                    model.setRowCount(0);
                    for (Map.Entry<String, RFID> entry : map.entrySet()) {

                        String k = entry.getKey();
                        RFID v = entry.getValue();
                        model.addRow(new Object[]{k, v.getDate(), v.getGate()});

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
            }
        };
        thread.start();
    }

}
