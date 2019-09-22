package foo.zongzhe.carpark.builder;

import foo.zongzhe.common.utils.DateUtil;
import foo.zongzhe.common.utils.LogUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CarParking {

    // Main logic entry
    final static int MON_PRICE = 750;
    final static int MONTH = 12;


    public static void main(String args[]) {

        // Input validation
        String startDate = (args.length == 0) ? "2019-01-02" : args[0];
        LogUtil.logInfo(String.format("Start parking date is %s", startDate));

        // Initialize
        List<Calendar> holidayList = addHolidayCode();
        LogUtil.logInfo("Recorded holidays amount: " + holidayList.size());

        LogUtil.logInfo("holiday list: ");
        for (Calendar c : holidayList) {
            LogUtil.logInfo(DateUtil.dateToString(c.getTime(), DateUtil.YYYY_MM_DD_WITH_HYPHEN));
        }


//        int[] parkDays = new int[12];
//        Calendar[] parkStartDate = new Calendar[12];
//        Calendar[] parkEndDate = new Calendar[12];

        Calendar startDateC = Calendar.getInstance();
        startDateC = DateUtil.stringToCalendar(startDate, DateUtil.YYYY_MM_DD_WITH_HYPHEN);

//        for (int i : parkDays) {
//            parkDays[i] = 0;
//            parkStartDate[i] = Calendar.getInstance();
//            parkEndDate[i] = Calendar.getInstance();
//        }
//
//        parkStartDate[0] = DateUtil.stringToCalendar(startDate, DateUtil.YYYY_MM_DD_WITH_HYPHEN);
        for (int i = 1; i <= MONTH; i++) {
            LogUtil.logInfo(String.format("Analyzing month: %d, and the start date is %s",
                    i, DateUtil.dateToString(startDateC.getTime(), DateUtil.YYYY_MM_DD_WITH_HYPHEN)));
            startDateC = getParkDays(startDateC, holidayList);
        }

        // Main functions
//        parkEndDate[0] = DateUtil.CopyCalendar(parkStartDate[0]);
//        parkStartDate[1] = getParkDays(parkStartDate[0], holidayList);
//
//        DateFormat df = new SimpleDateFormat(DateUtil.YYYY_MM_DD_WITH_HYPHEN);
//        Date date = null;
//        try {
//            date = df.parse("2019-01-01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar currentDate = Calendar.getInstance();
//        Calendar tempDate = Calendar.getInstance();
//        currentDate.setTime(date);
//        tempDate.setTime(date);
//        holidayList.add(tempDate);
//        LogUtil.logInfo(String.valueOf(holidayList.contains(currentDate)));

    }

    static Calendar getParkDays(Calendar startCalendar, List<Calendar> holidayList) {
        Calendar endCalendar = DateUtil.CopyCalendar(startCalendar);
        endCalendar.add(Calendar.MONTH, 1);
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);
        if (endCalendar.get(Calendar.YEAR) == startCalendar.get(Calendar.YEAR) + 1) {
            endCalendar.set(Calendar.YEAR, startCalendar.get(Calendar.YEAR));
            endCalendar.set(Calendar.MONTH, 12);
            endCalendar.set(Calendar.DAY_OF_MONTH, 31);
        }
        LogUtil.logInfo(String.format("Start date: %s, end date: %s",
                DateUtil.dateToString(startCalendar.getTime(), DateUtil.YYYY_MM_DD_WITH_HYPHEN),
                DateUtil.dateToString(endCalendar.getTime(), DateUtil.YYYY_MM_DD_WITH_HYPHEN)));
        int days = DateUtil.getBusDaysWithinPeriod(startCalendar, endCalendar, holidayList);
        Double dailyPrice = (double) (MON_PRICE / days);
        LogUtil.logInfo(String.format("Parking for %d days, and costing %.0f RMB each day", days, dailyPrice));
        endCalendar = DateUtil.getNextBusDate(endCalendar, holidayList);
        return endCalendar;
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
        for (String holiday : holidayCode) {
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
