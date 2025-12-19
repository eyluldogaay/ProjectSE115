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

        int maxProfit = Integer.MIN_VALUE;//-2147483648    smallest

        int maxCommodityIndex = -1;

        for (int c = 0; c < COMMS; c++) {

            int totalProfit = 0;

            for (int d = 0; d < DAYS; d++) { //add  the profits for all days of the selected month
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

    public static int bestDayOfMonth(int month) { //find best day of given month according to profit
        if (month < 0 || month >= MONTHS) {
            return -1;
        }
        int bestDay = 1;
        int maxProfit = Integer.MIN_VALUE; //-2147483648 smallest

        for (int d = 0; d < DAYS; d++) { //Days 0 to 27

            int dayTotal = 0;

            for (int c = 0; c < COMMS; c++) {
                dayTotal += data[month][d][c];  //sum all comms that day
            }

            if (dayTotal > maxProfit) {  //make comparison which value is greater
                maxProfit = dayTotal;
                bestDay = d + 1;   // covert days 0 to 27  from 1 to 28
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

        int bestMonthIndex = 0;
        int maxProfit = Integer.MIN_VALUE; //-2147483648    smallest


        for (int m = 0; m < MONTHS; m++) {

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

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}