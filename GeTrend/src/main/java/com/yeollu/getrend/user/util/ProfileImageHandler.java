package com.yeollu.getrend.user.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.yeollu.getrend.util.PropertiesUtil;

public class ProfileImageHandler {
	
	private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			  "cloud_name", PropertiesUtil.get("image", "CLOUD_NAME"),
			  "api_key", PropertiesUtil.get("image", "API_KEY"),
			  "api_secret", PropertiesUtil.get("image", "API_SECRET")));
	
	public static String requestUpload(MultipartFile multipartFile) {
		File toUpload = convert(multipartFile);
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
	
	private static File convert(MultipartFile multipartFile) {
		File convFile = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}
}
