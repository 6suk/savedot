package com.mulcam.finalproject.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.mulcam.finalproject.dto.OcrDTO;
import com.mulcam.finalproject.dto.OcrImageDTO;

@Service
public class CashOcrUtil {


	@Value("${apiURL}") private String apiURL;
	@Value("${secretKey}") private String secretKey;
	@Value("${spring.servlet.multipart.location}")
	private String location;

	public String getOcrResult(MultipartFile multipartFile) throws Exception {
		/* BASE64 인코딩 */
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] receiptEncode = encoder.encode(multipartFile.getBytes());
		String receiptString = new String(receiptEncode, "UTF8");
		int index = multipartFile.getOriginalFilename().indexOf(".");
		String format = multipartFile.getOriginalFilename().substring(index+1);

		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		//header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		headers.add("X-OCR-SECRET", secretKey);

		/* Body */
		//set Image
		List<OcrImageDTO> ocrData = new ArrayList<>();
		OcrImageDTO imageDTO = new OcrImageDTO();
		imageDTO.setFormat(format);
		imageDTO.setName("medium");
		imageDTO.setData(receiptString);
		ocrData.add(imageDTO);

		// set ocr
		OcrDTO ocrDTO = new OcrDTO();
		ocrDTO.setImages(ocrData);
		ocrDTO.setResultType("string");
		ocrDTO.setRequestId(UUID.randomUUID().toString());
		ocrDTO.setTimestamp("0");
		ocrDTO.setVersion("V1");

		//httpEnitity
		HttpEntity<OcrDTO> requestEntity = new HttpEntity<>(ocrDTO,headers);
		ResponseEntity<String> rateResponse = restTemplate.exchange(apiURL, HttpMethod.POST, requestEntity, String.class);
		JSONObject jo3 = new JSONObject(rateResponse.getBody());
		JSONArray images = jo3.getJSONArray("images").getJSONObject(0).getJSONArray("fields");

		for(int i=0; i<images.length(); i++){
			String inferText = images.getJSONObject(i).get("inferText").toString();
			if(checkDate(inferText)){
				jo3.getJSONArray("images").getJSONObject(0).getJSONArray("fields").getJSONObject(0).put("time", inferText);
				continue;
			}
			if(isPrice(inferText)){
				jo3.getJSONArray("images").getJSONObject(0).getJSONArray("fields").getJSONObject(0).put("price", inferText);
				continue;
			}
			if(images.getJSONObject(i).get("inferText").equals("[매장명]") || images.getJSONObject(i).get("inferText").equals("상호:")) {
				jo3.getJSONArray("images").getJSONObject(0).getJSONArray("fields").getJSONObject(0).put("shopName", images.getJSONObject(i+1).get("inferText"));
			}
		}
		return jo3.getJSONArray("images").getJSONObject(0).getJSONArray("fields").toString();
	}


	public static boolean checkDate(String checkDate) {
		checkDate = checkDate.replaceAll("년","-");
		checkDate = checkDate.replaceAll("월","-");
		checkDate = checkDate.replaceAll("일","");
		try {
			SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
			dateFormatParser.setLenient(false);
			dateFormatParser.parse(checkDate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isPrice(String price){
		if(price.indexOf(",") != -1){
			price = price.replaceAll(",", "");
			try{
				Integer.parseInt(price);
				return true;
			}catch (NumberFormatException e){
				return false;
			}
		}
		return false;
	}


	public static boolean isContent(String content) {

		if(content.contains("매장")|| content.contains("상호")) {

		}
		return false;
	}



	public static byte[] ocrImgArray(String filePath) throws Exception {
		byte[] returnValue = null;

		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;


		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(filePath);

			byte[] buf = new byte[1024];
			int read = 0;

			while((read=fis.read(buf,0,buf.length))!= -1) {
				baos.write(buf, 0, read);
			}

			returnValue = baos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(baos != null) {
				baos.close();
			}
			if(fis != null) {
				fis.close();
			}
		}


		return returnValue;
	}


}
