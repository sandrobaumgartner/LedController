package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public interface LedController {
    void demo() throws IOException;
    ArrayList<JSONObject> getGroupLEDs() throws IOException;
    void getSpecificLED(int id) throws IOException;
    ArrayList<JSONObject> turnOffAllLEDs() throws IOException;
    void runLight(String color, int rounds) throws IOException, InterruptedException;
    void setLED(int id, String color) throws IOException;
    void spinWheel(int amountSteps) throws IOException;
}
