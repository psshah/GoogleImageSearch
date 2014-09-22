package com.psshah.gridimagesearch.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.psshah.gridimagesearch.R;
import com.psshah.gridimagesearch.models.ImageSettings;

public class SettingsActivity extends Activity {
	private Spinner spImageSize;
	private Spinner spImageType;
	private Spinner spImageColor;
	private EditText etSite;
	private ImageSettings settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		settings = (ImageSettings) getIntent().getSerializableExtra(SearchActivity.IMAGE_SETTINGS);
		initializeResources();
		populateValues();
		setupListeners();
	}

	
	private void initializeResources() {
		spImageSize = (Spinner) findViewById(R.id.spImageSize);
		spImageType = (Spinner) findViewById(R.id.spImageType);
		spImageColor = (Spinner) findViewById(R.id.spImageColor);
		etSite = (EditText) findViewById(R.id.etSite);
		etSite.setText(settings.imgSite);
	}

	
	private void populateValues() {
		/* Image Size */
		ArrayAdapter<CharSequence> aImgSize = ArrayAdapter.createFromResource(this,
		        R.array.imageSizeArr, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		aImgSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spImageSize.setAdapter(aImgSize);
		spImageSize.setSelection(settings.imgSizePos);
		
		/* Image Type */
		ArrayAdapter<CharSequence> aImgType = ArrayAdapter.createFromResource(this,
		        R.array.imageTypeArr, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		aImgType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spImageType.setAdapter(aImgType);
		spImageType.setSelection(settings.imgTypePos);


		/* Image Color */
		ArrayAdapter<CharSequence> aImgColor = ArrayAdapter.createFromResource(this,
		        R.array.imageColorArr, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		aImgColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spImageColor.setAdapter(aImgColor);
		spImageColor.setSelection(settings.imgColorPos);
	}
	
	
	private void setupListeners() {	
		spImageSize.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        Object item = parent.getItemAtPosition(pos);
		        settings.imgSizePos = pos;
		        settings.imgSize = item.toString();
		    }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
		        Log.i("INFO", "Nothing selected");				
			}
		});		

		spImageType.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        Object item = parent.getItemAtPosition(pos);
		        settings.imgTypePos = pos;
		        settings.imgType = item.toString();
		    }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
		        Log.i("INFO", "Nothing selected");				
			}
		});		
		
		/* Image color */
		spImageColor.setOnItemSelectedListener(new OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        Object item = parent.getItemAtPosition(pos);
		        settings.imgColorPos = pos;
		        settings.imgColor = item.toString();
		    }

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
		        Log.i("INFO", "Nothing selected");				
			}
		});
	}


	public void onSave(View v) {
		settings.imgSite = etSite.getText().toString();

		// set the result
		Intent i = new Intent();
		i.putExtra(SearchActivity.IMAGE_SETTINGS, settings);
		setResult(RESULT_OK, i);
		// dismiss the activity
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
