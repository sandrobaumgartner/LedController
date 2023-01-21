package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ApiService {
    JSONObject getLights() throws IOException;
    JSONObject getLight(int id) throws IOException;
    JSONObject setLight(int id, String color, Boolean on) throws IOException;
}
