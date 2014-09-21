package com.psshah.gridimagesearch.adapters;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.psshah.gridimagesearch.R;
import com.psshah.gridimagesearch.models.ImageResult;
import com.squareup.picasso.Picasso;


public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultsAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get item at position
		ImageResult image = getItem(position);    

		// Check if this is recycled view, if not, create / inflate it.
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
		}

		// Get resources from view to populate
		//TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

		// Clear recycled view
		ivImage.setImageResource(0);
		
		// Populate resources
		//tvTitle.setText(Html.fromHtml(image.title));
		
		// Use Picasso to download images
		//Log.i("DEBUG", "fetching image " + image.thumbUrl);
		Picasso.with(getContext()).load(image.thumbUrl).into(ivImage);

		return convertView;
	}

}
