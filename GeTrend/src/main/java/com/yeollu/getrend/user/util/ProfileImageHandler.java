package com.yeollu.getrend.user.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.transformation.Layer;
import com.cloudinary.utils.ObjectUtils;
import com.yeollu.getrend.util.PropertiesUtil;

public class ProfileImageHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileImageHandler.class);
	
	private Cloudinary cloudinary;
	private String url = "";
	private String publicId = "";
	
	public ProfileImageHandler() {
		cloudinary = new Cloudinary(ObjectUtils
							.asMap( "cloud_name", PropertiesUtil.get("image", "CLOUD_NAME"),
									"api_key", PropertiesUtil.get("image", "API_KEY"),
									"api_secret", PropertiesUtil.get("image", "API_SECRET")));
	}
	
	public boolean upload(MultipartFile multipartFile) {
		File toUpload = convert(multipartFile);
		
		try {
			Map uploadResult = cloudinary
								.uploader()
								.upload(toUpload, ObjectUtils.emptyMap());
			if(uploadResult == null) {
				return false;
			}
			publicId = (String) uploadResult.get("public_id");
			url = cloudinary
					.url()
					.format("jpg")
					.transformation(new Transformation()
					  .gravity("face")
					  .radius("max")
					  .crop("thumb")
					  .chain())
					.generate(publicId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean delete(String publicId) {
		try {
			Map result = cloudinary
					.uploader()
					.destroy(publicId, ObjectUtils.emptyMap());
			if(result == null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private File convert(MultipartFile multipartFile) {
		File convFile = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
	
	public String getPublicId() {
		return publicId;
	}
	
	public String getUrl() {
		return url;
	}
}
