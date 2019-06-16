package util;

import domain.DiningTable;
import domain.TimeSlot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomainObjectConverter {

    private DomainObjectConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> convertDiningTables(List<DiningTable> diningTables) {
        List<String> tables = new ArrayList<>();
        for (DiningTable diningTable : diningTables) {
            tables.add(String.valueOf(diningTable.getTableNumber()));
        }
        return tables;
    }

    public static String convertTimeSlots(List<TimeSlot> timeSlots) {
        return getStringFromDate(timeSlots.get(0).getStartTime()) + " - " +
                getStringFromDate(timeSlots.get(timeSlots.size() - 1).getEndTime());
    }

    private static String getStringFromDate(Date date) {
        String pattern = "HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
