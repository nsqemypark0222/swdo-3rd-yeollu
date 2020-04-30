package com.yeollu.getrend.user.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.yeollu.getrend.util.PropertiesUtil;

/**
 * @Class 	: ProfileImageHandler.java
 * @Package	: com.yeollu.getrend.user.util
 * @Project : GeTrend
 * @Author	: 문지연
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: Cloudinary 서비스를 호출해 이미지 파일을 관리한다.
 */
public class ProfileImageHandler {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProfileImageHandler.class);
	private Cloudinary cloudinary;
	private String url = "";
	private String publicId = "";
	
	/**
	 * Constructor
	 */
	public ProfileImageHandler() {
		cloudinary = new Cloudinary(ObjectUtils
							.asMap( "cloud_name", PropertiesUtil.get("image", "CLOUD_NAME"),
									"api_key", PropertiesUtil.get("image", "API_KEY"),
									"api_secret", PropertiesUtil.get("image", "API_SECRET")));
	}
	
	/**
	 * @Method	: upload
	 * @Return	: boolean
	 * @Author	: 문지연
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 이미지 파일을 업로드한 후 publicId와 url를 저장한다.
	 * @param multipartFile
	 */
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
	
	/**
	 * @Method	: delete
	 * @Return	: boolean
	 * @Author	: 문지연
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 매개변수로 넘겨받은 publicId를 이용해 이미지 파일을 삭제한다.
	 * @param publicId
	 */
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
	
	/**
	 * @Method	: convert
	 * @Return	: File
	 * @Author	: 문지연
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: MultipartFile를 File로 변환하여 반환한다.
	 * @param multipartFile
	 * @return
	 */
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
	
	/**
	 * @Method	: getPublicId
	 * @Return	: String
	 * @Author	: 문지연
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public String getPublicId() {
		return publicId;
	}
	
	/**
	 * @Method	: getUrl
	 * @Return	: String
	 * @Author	: 문지연
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public String getUrl() {
		return url;
	}
}
