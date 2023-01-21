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
}
