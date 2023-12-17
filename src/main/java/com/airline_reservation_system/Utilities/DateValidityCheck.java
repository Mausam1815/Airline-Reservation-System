package com.airline_reservation_system.Utilities;

public class DateValidityCheck {
    public static boolean checkDateValidity(String date) {
        if(date.length() != 10) {
            return false;
        }
        if(date.charAt(2) != '-' || date.charAt(5) != '-') {
            return false;
        }
        String[] dateArr = date.split("-");
        for(String d : dateArr) {
            try{
                Integer.parseInt(d);
            }catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
