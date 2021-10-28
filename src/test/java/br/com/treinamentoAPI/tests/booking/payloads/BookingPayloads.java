package br.com.treinamentoAPI.tests.booking.payloads;

import org.json.simple.JSONObject;

public class BookingPayloads {

    public static JSONObject payLoadValidBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2018-01-01");

        payload.put("firstname", "Cristiano");
        payload.put("lastname", "Ronaldo");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "breakfast");


        return payload;


    }
}

