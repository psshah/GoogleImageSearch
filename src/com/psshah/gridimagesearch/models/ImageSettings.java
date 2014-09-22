package com.psshah.gridimagesearch.models;

import java.io.Serializable;

public class ImageSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5381590531599351786L;
	public String imgSize;
	public int imgSizePos;
	public String imgColor;
	public int imgColorPos;
	public String imgType;
	public int imgTypePos;
	public String imgSite;

	public ImageSettings() {
		imgSize = "any";
		imgSizePos = 0;
		imgColor = "any";
		imgColorPos = 0;
		imgType = "any";
		imgTypePos = 0;
		imgSite = "";
	}
}
