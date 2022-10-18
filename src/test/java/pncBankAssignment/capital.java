package pncBankAssignment;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class capital {
    static Scanner scanner =new Scanner(System.in);
    static String baseURI= RestAssured.baseURI ="https://restcountries.com/v3.1/";

    public static void main(String[] args) {
        capital();
    }
    public static void capital(){
        System.out.println("Please enter the country name or code:");
        String input = scanner.nextLine();
        Response capital = null;
        if (!input.contains(" ")){
            while (!input.equalsIgnoreCase("Quit")){
                if (input.length() <= 1) {
                    System.out.println("Country code or name must be min 3 character!");
                } else if (input.length() <= 3) {
                    capital = given().get("alpha/" + input);
                } else {
                    capital = given().get("name/" + input);
                }
                capital.then().statusCode(200);
//        capital.getBody().prettyPrint();
                System.out.println(capital.jsonPath().getString("capital")+"\n"
                        +"Please enter the country name or code:"+"\n"+
                        "If you wanna Quit please enter 'Quit'");
                input=scanner.nextLine();
            }
        }else{
            System.out.println("Input must be without blank!");
        }
    }
}
/*
//Positive Test Cases

        capital("United States of America");
        capital("united states of america");
        capital("USA");
        capital("usa");
        capital("US");
        capital("us");
        capital("840");

//Negative Test Cases

        capital("Apple");
        capital("APL");
        capital("AP");
        capital("5");
        capital(null);
        capital("---");
        capital(" ");
        capital("AF5");
        capital("USA1");
        capital("usa2");
        capital("usa&");
        capital("usa ");
        capital("84O");
 */