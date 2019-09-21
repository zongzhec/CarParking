package foo.zongzhe.carpark.builder;

import foo.zongzhe.common.utils.DateUtil;
import foo.zongzhe.common.utils.LogUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarParking {

    // Main logic entry


    public static void main(String args[]) {

        List<Calendar> holidayList = addHolidayCode();
        LogUtil.logInfo("Recorded holidays amount: " + holidayList.size());

        for (Calendar c:holidayList){
            LogUtil.logInfo(c.);
        }


        int priceMon = 750;
        int[] parkUse = new int[12];
        String[] parkStartDate = new String[12];
        String[] parkEndDate = new String[12];

        for (int i : parkUse) {
            parkUse[i] = 0;
            parkStartDate[i] = "";
            parkEndDate[i] = "";
        }

        DateFormat df = new SimpleDateFormat(DateUtil.YYYY_MM_DD_WITH_HYPHEN);
        Date date = null;
        try {
            date = df.parse("2019-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar currentDate = Calendar.getInstance();
        Calendar tempDate = Calendar.getInstance();
        currentDate.setTime(date);
        tempDate.setTime(date);
        holidayList.add(tempDate);
        LogUtil.logInfo(String.valueOf(holidayList.contains(currentDate)));

    }

    public static List<Calendar> addHolidayCode() {
        List<String> holidayCode = new ArrayList<>();
        List<Calendar> holidayList = new ArrayList<>();
        holidayCode.add("2019-01-01");
        holidayCode.add("2019-02-04");
        holidayCode.add("2019-02-05");
        holidayCode.add("2019-02-06");
        holidayCode.add("2019-02-07");
        holidayCode.add("2019-02-08");
        holidayCode.add("2019-02-09");
        holidayCode.add("2019-04-05");
        holidayCode.add("2019-05-01");
        holidayCode.add("2019-06-07");
        holidayCode.add("2019-09-13");
        holidayCode.add("2019-10-01");
        holidayCode.add("2019-10-02");
        holidayCode.add("2019-10-03");
        holidayCode.add("2019-10-04");
        holidayCode.add("2019-10-07");
        for (String holiday:holidayCode){
            DateFormat df = new SimpleDateFormat(DateUtil.YYYY_MM_DD_WITH_HYPHEN);
            Date date = new Date();
            try {
                date = df.parse(holiday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime(date);
            holidayList.add(currentDate);
        }
        return holidayList;
    }
}
