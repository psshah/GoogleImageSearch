package com.psshah.gridimagesearch.models;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class ImageResult implements Serializable {
	private static final long serialVersionUID = 4762094728997689341L;
	public String title;
	public String fullUrl;
	public String thumbUrl;
	public int width;
	public int height;

	public ImageResult(JSONObject json) {
		// populate fields from json
		try {
			// Following are all guaranteed fields, hence skipping existence check
			title = json.getString("title");
			fullUrl = json.getString("url");
			thumbUrl = json.getString("tbUrl");
			width = json.getInt("width");
			height = json.getInt("height");
			//Log.i("INFO", "height=" + height + "width=" + width);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}

	public static ArrayList<ImageResult> fromJSONArray(JSONArray jsonArray) {
		ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
		for(int i = 0; i < jsonArray.length(); i++) {
			try {
				ImageResult image = new ImageResult(jsonArray.getJSONObject(i));
				imageResults.add(image);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return imageResults;
	}	
}
