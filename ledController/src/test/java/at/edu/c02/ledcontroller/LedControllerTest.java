package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void getGroupLEDsTest() {
        ApiService apiService = new ApiServiceMock();
        LedController ledController = new LedControllerImpl(apiService);

        try {
            ArrayList<JSONObject> lights = ledController.getGroupLEDs();
            for (JSONObject light : lights) {
                JSONObject group = light.getJSONObject("groupByGroup");
                if(!group.getString("name").equals("G")) {
                    assertTrue(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setLightTest() {
        ApiService apiService = new ApiServiceImpl();
        try {
            apiService.setLight(47, "#FF0000", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void turnOffAllLightsTest() {
        ApiServiceMock apiService = new ApiServiceMock();
        LedController ledController = new LedControllerImpl(apiService);

        try {
            ArrayList<JSONObject> lights = ledController.turnOffAllLEDs();
            for (JSONObject light : lights) {
                JSONObject lightResponse = light.getJSONObject("update_lights_by_pk");
                boolean on = lightResponse.getBoolean("on");
                if(on) {
                    assertTrue(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void roundLightTest() {
        ApiService apiService = new ApiServiceImpl();
        LedController ledController = new LedControllerImpl(apiService);
        try {
            ledController.runLight("#FF0000", 3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
