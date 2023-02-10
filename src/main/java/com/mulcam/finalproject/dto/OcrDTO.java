package com.mulcam.finalproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OcrDTO {
	
    private String lang = "ko";
    private String requestId = "string";
    private String resultType = "string";
    private String timestamp = "";
    private String version = "V1";
    private List<OcrImageDTO> images;

}
