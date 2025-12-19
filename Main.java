// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};

    static int[][][] data = new int[MONTHS][DAYS][COMMS];// ı used for data storage
    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {  // ı wanted to find the most profitable commodity in the given month and calculate its profit
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        int maxProfit = 0;                   //store total profit of the first commodity
        for (int d = 0; d < DAYS; d++) {
            maxProfit += data[month][d][0]; //for initial commodity Gold
        }

        int maxCommodityIndex = 0; //assume the first commodity is maximum

        for (int c = 1; c < COMMS; c++) {  //remaining commodities

            int totalProfit = 0;

            for (int d = 0; d < DAYS; d++) {
                totalProfit += data[month][d][c];
            }

            if (totalProfit > maxProfit) { //make comparison which value is greater
                maxProfit = totalProfit;
                maxCommodityIndex = c;
            }
        }

        return commodities[maxCommodityIndex] + " " + maxProfit;

    }



    public static int totalProfitOnDay(int month, int day) {  // find total profit given day and month
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }
        int d = day - 1; // convert the day array format

        int totalProfit = 0;

        for (int c = 0; c < COMMS; c++) {
            totalProfit += data[month][d][c]; // sum all comms this day and this month
        }
        return totalProfit;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) { //find total profit given commodity for all months day ranges

        if (from < 1 || from > DAYS || to < 1 || to > DAYS || from > to) {
            return -99999;
        }

        int commIndex = -1;//find commodity index
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(commodity)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1) {
            return -99999;
        }

        int totalProfit = 0;

        for (int m = 0; m < MONTHS; m++) {      //for all month

            for (int d = from - 1; d <= to - 1; d++) { // days convert index version
                totalProfit += data[m][d][commIndex];
            }
        }

        return totalProfit;

    }

    public static int bestDayOfMonth(int month) { //find the best day of given month according to profit
        if (month < 0 || month >= MONTHS) {
            return -1;
        }

        int bestDay = 1;  //assume day1 is the best day initially

        int maxProfit = 0;
        for (int c = 0; c < COMMS; c++) {  // calculate day1 profit
            maxProfit += data[month][0][c];
        }

        for (int d = 1; d < DAYS; d++) { //check remaining days
            int dayTotal = 0;
            for (int c = 0; c < COMMS; c++) {
                dayTotal += data[month][d][c]; // sum profits of all commodities for that day
            }

            if (dayTotal > maxProfit) {
                maxProfit = dayTotal;
                bestDay = d + 1; // convert index to day number
            }
        }

        return bestDay;

    }

    public static String bestMonthForCommodity(String comm) { //find which month is the most profit for given commodity

        int commIndex = -1;  //find commodity index
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1) {
            return "INVALID_COMMODITY";
        }

        int bestMonthIndex = 0;  //assume first month is the best
        int maxProfit = 0;
        for (int d = 0; d < DAYS; d++) {
            maxProfit += data[0][d][commIndex];
        }

        for (int m = 1; m < MONTHS; m++) {

            int monthTotal = 0;

            for (int d = 0; d < DAYS; d++) {
                monthTotal += data[m][d][commIndex]; //according to given comms total profit
            }

            if (monthTotal > maxProfit) {
                maxProfit = monthTotal;  //make comparison which value is greater
                bestMonthIndex = m;
            }
        }

        return months[bestMonthIndex];
    }

    public static int consecutiveLossDays(String comm) {  //returns the longest consecutive streak of negative profit days for the given commodity across the whole year

        int commIndex = -1;  //find commodity index
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }
        if (commIndex == -1) {
            return -1;
        }

        int currentStreak = 0;
        int maxStreak = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {   //all years
                int profit = data[m][d][commIndex];

                if (profit < 0) {
                    currentStreak++; //increase streak
                    if (currentStreak > maxStreak) {
                        maxStreak = currentStreak; //update maximum streak
                    }
                } else {
                    currentStreak = 0; // reset streak because positive profit
                }
            }
        }
        return maxStreak;
    }

    public static int daysAboveThreshold(String comm, int threshold) { // find the number of days when the profit is above the given threshold

        int commIndex = -1;    //find commodity index
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1) {
            return -1;
        }

        int count = 0;

        for (int m = 0; m < MONTHS; m++) {    // each month each day = all year
            for (int d = 0; d < DAYS; d++) {
                if (data[m][d][commIndex] > threshold) {   //control profit greather than threshold
                    count++;                               // increase count
                }
            }
        }

        return count;

    }

    public static int biggestDailySwing(int month) {  //finds the largest profit change between consecutive days
        if (month < 0 || month >= MONTHS) {
            return -99999;
        }

        int maxSwing = 0;

        int previousDayTotal = 0;
        for (int c = 0; c < COMMS; c++) {
            previousDayTotal += data[month][0][c];  //find total profit first day(today)
        }

        for (int day = 1; day < DAYS; day++) {
            int currentDayTotal = 0;
            for (int c = 0; c < COMMS; c++) {       // find total profit second day(tomorrow)
                currentDayTotal += data[month][day][c];
            }

            int swing = currentDayTotal - previousDayTotal;
            if (swing < 0) swing = -swing;  //absolute control

            if (swing > maxSwing) {
                maxSwing = swing;
            }

            previousDayTotal = currentDayTotal; //tomorrow become today
        }

        return maxSwing;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        int index1 = -1;
        int index2 = -1;

        for (int c = 0; c < COMMS; c++) {   // find commodity index
            if (commodities[c].equals(c1)) {
                index1 = c;
            }
            if (commodities[c].equals(c2)) {
                index2 = c;
            }
        }

        if (index1 == -1 || index2 == -1) {
            return "INVALID_COMMODITY";
        }

        int total1 = 0;
        int total2 = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                total1 += data[m][d][index1];
                total2 += data[m][d][index2];  //profits
            }
        }


        if (total1 > total2) {
            return c1 + " is better by " + (total1 - total2);  //compare total both
        } else if (total2 > total1) {
            return c2 + " is better by " + (total2 - total1);
        } else {
            return "Equal";
        }

    }

    public static String bestWeekOfMonth(int month) { //find the most profitable week of the given month
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        int bestWeek = 1;   // start by assuming week1 is the best week


        int week1Total = 0;               //total profit week1
        for (int d = 0; d < 7; d++) {
            for (int c = 0; c < COMMS; c++) {
                week1Total += data[month][d][c];
            }
        }

        int maxProfit = week1Total;


        for (int week = 1; week < 4; week++) { //remaining weeks

            int weekTotal = 0;
            int startDay = week * 7;  //determine start and end day
            int endDay = startDay + 7;

            for (int d = startDay; d < endDay; d++) {
                for (int c= 0; c < COMMS; c++) {  //sum each day of week
                    weekTotal += data[month][d][c];
                }
            }

            if (weekTotal > maxProfit) { //compare higher
                maxProfit = weekTotal;
                bestWeek = week + 1;  //covert ındex to week number
            }
        }

        return "Week " + bestWeek;
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}