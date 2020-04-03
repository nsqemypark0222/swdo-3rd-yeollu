package com.yeollu.getrend.user.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

public class ProfileImageHandler {
	
	private Cloudinary cloudinary;
	
	public ProfileImageHandler() {
		cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dw5oh4ebf",
				  "api_key", "634487884346878",
				  "api_secret", "rLuJXODsUy7Kgmvxwv6iD9C367U"));
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
