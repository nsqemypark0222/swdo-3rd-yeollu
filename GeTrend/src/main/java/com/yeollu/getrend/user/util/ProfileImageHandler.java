package com.yeollu.getrend.user.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.yeollu.getrend.util.PropertiesUtil;

public class ProfileImageHandler {
	
	private Cloudinary cloudinary;
	
	public ProfileImageHandler() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", PropertiesUtil.get("image", "CLOUD_NAME"),
				  "api_key", PropertiesUtil.get("image", "API_KEY"),
				  "api_secret", PropertiesUtil.get("image", "API_SECRET")));
	}
	
	public String upload() {
		File toUpload = new File("C:/Users/user/Desktop/1a25b354de158976b69f40baffe61f1e.jpg");
		String url = "";
		try {
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			
			url = (String) uploadResult.get("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
}
