package com.automationcalling.commonutils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

public class JsonParser {
    private HashMap<Object, String> pairs = new HashMap <>();
    private JSONParser parser;
    String data = null;
    Object obj=null;

    public String readJSon(String pagename, String fieldname) {

        parser = new JSONParser();
        try {
            obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/" +"src/main/resources/"+
                    "testdata.json"));
            JSONObject jsonobj = new JSONObject(obj.toString());
            JSONArray appPageName = jsonobj.getJSONArray(pagename);
            for (int i = 0; i < appPageName.length(); i++) {
                JSONObject j = appPageName.optJSONObject(i);
                Iterator it = j.keys();
                while (it.hasNext()) {
                    String n = it.next().toString();
                    pairs.put(n, j.getString(n));
                }
                data = pairs.get(fieldname);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args)
    {
        System.out.println(new JsonParser().readJSon("credentials","Password"));
    }
}
