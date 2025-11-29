package test_cases.omar.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONReader {

    private static JSONObject loadJson(String fileName) throws IOException, ParseException {

        InputStream inputStream = JSONReader.class
                .getClassLoader()
                .getResourceAsStream("testData/" + fileName);

        if (inputStream == null) {
            throw new IOException("File not found in resources/testData: " + fileName);
        }

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(new InputStreamReader(inputStream));
    }

    public static String existingUser(String data) throws IOException, ParseException {
        return (String) loadJson("ExistingUser.json").get(data);
    }

    public static String accountDetails(String data) throws IOException, ParseException {
        return (String) loadJson("AccountDetails.json").get(data);
    }

    public static String paymentDetails(String data) throws IOException, ParseException {
        return (String) loadJson("PaymentDetails.json").get(data);
    }

    public static String poloBrandProducts(String data) throws IOException, ParseException {
        return (String) loadJson("PoloBrandProducts.json").get(data);
    }

    public static String madameBrandProducts(String data) throws IOException, ParseException {
        return (String) loadJson("MadameBrandProducts.json").get(data);
    }
}
