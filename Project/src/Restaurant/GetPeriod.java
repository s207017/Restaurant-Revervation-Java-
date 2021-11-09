package Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GetPeriod {
    private static Scanner sc = new Scanner(System.in);
    private static int datesInMonthLeap[] = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static int datesInMonth[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static LocalDateTime getDate() {
        int yearInt;
        int monthInt;
        int dateInt;
        LocalDateTime finalDate;
        String temp;
        //chose year
        System.out.print("Please enter year:");
        yearInt = sc.nextInt();
        //chose month
        System.out.println("Enter month (1-12): ");
        monthInt = sc.nextInt();
        //chose date
        int dateArrayUsed[];
        if (yearInt % 4 == 0) {//Checking for leap year
            dateArrayUsed = datesInMonthLeap;
        } else {
            dateArrayUsed = datesInMonth;
        }
        System.out.print("Enter date:");
        dateInt = sc.nextInt();
        while (dateInt > dateArrayUsed[monthInt- 1]) {//Check if the date is within the limit
            System.out.printf("Please enter date between 1 and %d inclusive: ", dateArrayUsed[monthInt - 1]);
            dateInt = sc.nextInt();
        }
        //convert to format: 2007-12-03T10:15:30
        String year;
        String month;
        String date;
        year = Integer.toString(yearInt);
        if (monthInt > 9) {
            month = Integer.toString(monthInt);
        } else {
            month = '0' + Integer.toString(monthInt);
        }
        if (dateInt > 9) {
            date = Integer.toString(dateInt);
        } else {
            date = '0' + Integer.toString(dateInt);
        }
        temp = year + '-' + month + '-' + date + "T00:00:00";
        finalDate = LocalDateTime.parse(temp);
        return finalDate;
    }



    public static LocalDateTime getDateAndTime(){
        int yearInt;
        int monthInt;
        int dateInt;
        int hourInt;
        //int minuteInt;
        String year;
        String month;
        String date;
        String hour;
        //String minute;
        String temp;
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime finalDateTime;
        int dateArrayUsed[];
        //----------------------
        //ASKING FOR STUFF FIRST
        //----------------------
        //chose year






        do {
            System.out.print("Please enter year:");
            yearInt = sc.nextInt();
            //chose month
            System.out.println("Enter month (1-12): ");
            monthInt = sc.nextInt();
            //chose date
            if (yearInt % 4 == 0) {//Checking for leap year
                dateArrayUsed = datesInMonthLeap;
            } else {
                dateArrayUsed = datesInMonth;
            }
            System.out.print("Enter date:");
            dateInt = sc.nextInt();
            while (dateInt > dateArrayUsed[monthInt - 1]) {//Check if the date is within the limit
                System.out.printf("Please enter date between 1 and %d inclusive: ", dateArrayUsed[monthInt - 1]);
                dateInt = sc.nextInt();
            }
            //chose time
            System.out.print("Enter hour (24H format within opening hours): ");
            hourInt = sc.nextInt();
            while (10 > hourInt || hourInt > 22) {
                System.out.print("Please enter an hour within opening hours");
                hourInt = sc.nextInt();
            }
            /*System.out.print("Enter minutes: ");
            minuteInt = sc.nextInt();
            while (minuteInt < 0 || minuteInt > 59) {
                System.out.print("Please enter a valid minute value (1-59): ");
                minuteInt = sc.nextInt();
            }*/
            //--------------------------------------
            //convert to format: 2007-12-03T10:15:30
            //--------------------------------------
            year = Integer.toString(yearInt);
            if (monthInt > 9) {
                month = Integer.toString(monthInt);
            } else {
                month = '0' + Integer.toString(monthInt);
            }
            if (dateInt > 9) {
                date = Integer.toString(dateInt);
            } else {
                date = '0' + Integer.toString(dateInt);
            }
            hour = Integer.toString(hourInt);
            /*if (minuteInt > 9) {
                minute = Integer.toString(minuteInt);
            } else {
                minute = '0' + Integer.toString(minuteInt);
            }*/
            temp = year + '-' + month + '-' + date + 'T' + hour + ":00:00";
            finalDateTime = LocalDateTime.parse(temp);
            if(finalDateTime.isBefore(currentDateTime)){
                System.out.printf("Intended date (%s) is before current date, please try again\n",finalDateTime);
            }
        }while(finalDateTime.isBefore(currentDateTime));
        return finalDateTime;
    }
}