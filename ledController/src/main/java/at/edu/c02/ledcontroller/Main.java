package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());

        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!input.equalsIgnoreCase("exit"))
        {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'groupstatus' to get Group LEDs");
            System.out.println("Enter 'status' to get the status of one LED");
            System.out.println("Enter 'spinningled' to spin the LEDs");
            System.out.println("Enter 'setled' to set a LED");
            System.out.println("Enter 'spinningwheel' to spin the wheel");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            if(input.equalsIgnoreCase("groupstatus")) {
                ArrayList<JSONObject> groupLEDs = ledController.getGroupLEDs();
            }
            if(input.equalsIgnoreCase("status")) {
                System.out.print("PLease specify LED: ");
                int id = Integer.parseInt(reader.readLine());
                ledController.getSpecificLED(id);
            }
            if(input.equalsIgnoreCase("spinningled")) {
                System.out.print("Enter color: ");
                String color = reader.readLine();
                System.out.print("Enter amount of turns: ");
                int amountTurns = Integer.parseInt(reader.readLine());
                try {
                    ledController.runLight(color, amountTurns);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(input.equalsIgnoreCase("setled")) {
                System.out.print("Enter LED ID: ");
                int id = Integer.parseInt(reader.readLine());
                System.out.print("Enter color: ");
                String color = reader.readLine();
                ledController.setLED(id, color);
            }
            if(input.equalsIgnoreCase("spinningwheel")) {
                System.out.print("Enter how many steps: ");
                int steps = Integer.parseInt(reader.readLine());
                ledController.spinWheel(steps);
            }
        }
    }
}
