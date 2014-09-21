package com.psshah.gridimagesearch.activities;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.psshah.gridimagesearch.R;
import com.psshah.gridimagesearch.adapters.EndlessScrollListener;
import com.psshah.gridimagesearch.adapters.ImageResultsAdapter;
import com.psshah.gridimagesearch.models.ImageResult;

public class SearchActivity extends Activity {
	private EditText etQuery;
	private StaggeredGridView gvResults;
	private ArrayList<ImageResult> imageResults;
	private ImageResultsAdapter aImageResults;
	public static String IMAGE = "image";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		imageResults = new ArrayList<ImageResult>();
		
		// Initialise adapter and attach data source
		aImageResults = new ImageResultsAdapter(this, imageResults);
		// Attach adapter to view (GridView)
		gvResults.setAdapter(aImageResults);
	}

	private void setupViews() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		gvResults = (StaggeredGridView) findViewById(R.id.gvResults);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageResult image = imageResults.get(position);
				Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
				i.putExtra(IMAGE, image);
				startActivity(i);
			}
			
		});
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {						
			@Override
			public void onLoadMore(int page, int totalItemCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
				customLoadMoreDataFromApi(page); 				
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				
			}
		});
	}
	

	public void onImageSearch(View v) {
		Log.i("INFO", "clearing existing images");
		//imageResults.clear();
		aImageResults.clear();
		customLoadMoreDataFromApi(0);
	}

	
	protected void customLoadMoreDataFromApi(int page) {
		Log.i("INFO", "Endless scroll for page " + page);
		if(page == 8) {
			Log.i("INFO", "no more images");
			return;
		}
		String query = etQuery.getText().toString();
		String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + query + "&rsz=8&start=" + page*8;
		Log.i("INFO", url);
		
        // Create network client and initiate the network request
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
        	@Override
        	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        		//Log.i("DEBUG", response.toString());
                JSONArray imageResultsJson = null;
        		try {
        			imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
        			aImageResults.addAll(ImageResult.fromJSONArray(imageResultsJson));
				} catch (JSONException e) {
					e.printStackTrace();
				}
        	}
        	
        	@Override
        	public void onFailure(int statusCode, Header[] headers,
        			Throwable throwable, JSONObject errorResponse) {
        		super.onFailure(statusCode, headers, throwable, errorResponse);
        		Toast.makeText(SearchActivity.this, "Failed to fetch images " + errorResponse, Toast.LENGTH_SHORT).show();
        	}
        });

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
