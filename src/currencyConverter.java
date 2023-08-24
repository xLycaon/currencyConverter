import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class currencyConverter {

   //Insert your API key here, you can get it from https://currencyapi.com/
   //You will sometimes get error code 401, just wait a few minutes and try again
   private final static String apiKey = "";
   


   private static double convert(double amount, String from, String to){
    String URLString = apiKey;
    double result = 0;
    try{
        URL url = new URL(URLString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONObject obj_rates = new JSONObject(content.toString());
        String rates_From=obj_rates.findRates(from);
        String rates_To=obj_rates.findRates(to);
        double rate_From=Double.parseDouble(rates_From);
        double rate_To=Double.parseDouble(rates_To);
        result = amount*rate_To/rate_From;
    }catch(Exception e){
        e.printStackTrace();
    }
    return result;
   }

   public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount of money you want to convert: ");
        double amount = input.nextDouble();
        System.out.println("Enter the currency you want to convert from: ");
        String from = input.next();
        System.out.println("Enter the currency you want to convert to: ");
        String to = input.next();
        input.close();
        System.out.println("You want to convert " + amount + " " + from.toUpperCase() + " to " + to.toUpperCase());
        System.out.println("The amount of money you will receive is: " + convert(amount,from.toUpperCase(),to.toUpperCase()));
   }

}
