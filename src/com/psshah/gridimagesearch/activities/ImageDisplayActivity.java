package com.psshah.gridimagesearch.activities;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ortiz.touch.TouchImageView;
import com.psshah.gridimagesearch.R;
import com.psshah.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		
		getActionBar().hide();
		
		ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra(SearchActivity.IMAGE);
		TouchImageView ivImageResult = (TouchImageView) findViewById(R.id.ivImageResult);
		ivImageResult.getLayoutParams().height = imageResult.height;
		ivImageResult.getLayoutParams().width = imageResult.width;
		Picasso.with(this).load(imageResult.fullUrl).resize(imageResult.width, imageResult.height).into(ivImageResult);
	}

	public void onShare(View v) {
		TouchImageView siv = (TouchImageView) findViewById(R.id.ivImageResult);
		Drawable mDrawable = siv.getDrawable();
		Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();
		String path = Images.Media.insertImage(getContentResolver(), 
			    mBitmap, "Image Description", null);
		Uri uri = Uri.parse(path);
		//Log.i("INFO", "uri=" + uri.toString());
		
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*");
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(shareIntent, "Share image using"));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
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
