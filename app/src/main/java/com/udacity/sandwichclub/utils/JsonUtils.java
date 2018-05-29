package com.udacity.sandwichclub.utils;

import android.util.Log;
import com.udacity.sandwichclub.model.Sandwich;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    /**
     * Get a string value from json object identified by key
     *
     * @param jsonObject {@link JSONObject} from which we need the value
     * @param key JSON key/property name
     * @return String value at key if found else null
     */
    private static String getString(JSONObject jsonObject, String key) throws JSONException {
        return jsonObject.getString(key);
    }

    /**
     * Get a list/array of strings from the json objected identified by key
     *
     * @param jsonObject {@link JSONObject} from which we need the list of values
     * @param key JSON key/property name
     * @return List of strings at key if found else null
     */
    private static List<String> getListOfString(JSONObject jsonObject, String key) throws JSONException {
        List<String> result = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < jsonArray.length(); i++)
            result.add(jsonArray.getString(i));
        return result;
    }

    /**
     * Get a {@link JSONObject} identified by key
     *
     * @param jsonObject parent json object
     * @param key JSON key/property name
     * @return JSONObject identified by key
     */
    private static JSONObject getObject(JSONObject jsonObject, String key) throws JSONException {
        return jsonObject.getJSONObject(key);
    }

    public static Sandwich parseSandwichJson(String json) {
        try {
            // parse string to JSON
            JSONObject sandwichJson = new JSONObject(json);

            // create new sandwich
            Sandwich sandwich = new Sandwich();

            // fill in the sandwich with delicious properties
            sandwich.setMainName(
                getString(getObject(sandwichJson, "name"), "mainName"));
            sandwich.setAlsoKnownAs(
                getListOfString(getObject(sandwichJson, "name"), "alsoKnownAs"));
            sandwich.setDescription(getString(sandwichJson, "description"));
            sandwich.setImage(getString(sandwichJson, "image"));
            sandwich.setIngredients(getListOfString(sandwichJson, "ingredients"));
            sandwich.setPlaceOfOrigin(getString(sandwichJson, "placeOfOrigin"));

            // return the deliciously prepared sandwich
            return sandwich;
        } catch (JSONException ex){
            // Oops! something bad happened log it and return null
            Log.e(TAG, "Failed to make sandwich from JSON", ex);
            return null;
        }
    }
}
