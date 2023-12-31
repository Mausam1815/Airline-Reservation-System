package com.airline_reservation_system.Utilities;

public class TimeValidityCheck {
    public static boolean checkTimeValidity(String time) {
        if(time.length() != 5 || time.charAt(2) != ':') {
            return false;
        }
        String[] timeArr = time.split(":");
        for(String timeParts : timeArr) {
            try {
                Integer.parseInt(timeParts);
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
