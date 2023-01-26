package com.mulcam.finalproject.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MateImg {
	private String imgId;
	private String ext;
	private int bId;
	private String origFileName;
	private String filePath;
	private LocalDate regdate;

	public MateImg() {
	}

	public MateImg(String imgId, String ext, int bId, String origFileName, String filePath, LocalDate regdate) {
		super();
		this.imgId = imgId;
		this.ext = ext;
		this.bId = bId;
		this.origFileName = origFileName;
		this.filePath = filePath;
		this.regdate = regdate;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getOrigFileName() {
		return origFileName;
	}

	public void setOrigFileName(String origFileName) {
		this.origFileName = origFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public LocalDate getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDate regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MateImg [imgId=" + imgId + ", ext=" + ext + ", bId=" + bId + ", origFileName=" + origFileName
				+ ", filePath=" + filePath + ", regdate=" + regdate + "]";
	}

}
