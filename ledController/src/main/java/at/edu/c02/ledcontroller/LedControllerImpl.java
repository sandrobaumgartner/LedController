package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;
    private final int[] groupLEDs = {46, 47, 48, 49, 50, 51, 52, 53};

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public ArrayList<JSONObject> getGroupLEDs() throws IOException
    {
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");
        ArrayList<JSONObject> lightsForGroup = new ArrayList<>();

        for(int i = 0; i < lights.length(); i++) {
            JSONObject light = lights.getJSONObject(i);
            JSONObject group = light.getJSONObject("groupByGroup");

            if(group.getString("name").equals("G")) {
                lightsForGroup.add(light);
                int id = light.getInt("id");
                String status = light.getBoolean("on") ? "on" : "off";
                String color = light.getString("color");
                System.out.println("LED " + id + " is currently " + status + ". Color: " + color);
            }
        }

        return lightsForGroup;
    }

    @Override
    public void getSpecificLED(int id) throws IOException {
        JSONObject response = apiService.getLight(id);
        JSONObject light = response.getJSONArray("lights").getJSONObject(0);
        int idByApi = light.getInt("id");
        String status = light.getBoolean("on") ? "on" : "off";
        String color = light.getString("color");
        System.out.println("LED " + idByApi + " is currently " + status + ". Color: " + color);
    }

    @Override
    public ArrayList<JSONObject> turnOffAllLEDs() throws IOException {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < groupLEDs.length; i++) {
            int ledId = groupLEDs[i];
            JSONObject response = apiService.setLight(ledId, "#000", false);
            jsonObjects.add(response);
        }

        return jsonObjects;
    }

    @Override
    public void runLight(String color, int rounds) throws IOException, InterruptedException {
        this.turnOffAllLEDs();
        for(int j = 0; j < rounds; j++) {
            for(int i = 0; i < groupLEDs.length; i++) {
                int ledId = groupLEDs[i];
                apiService.setLight(ledId, color, true);
                Thread.sleep(5000);
                apiService.setLight(ledId, color, false);
            }
        }
        this.turnOffAllLEDs();
    }

    @Override
    public void setLED(int id, String color) throws IOException {
        apiService.setLight(id, color, true);
        System.out.println("LED color set!");
    }
}
