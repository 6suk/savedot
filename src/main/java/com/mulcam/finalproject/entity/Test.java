package com.mulcam.finalproject.entity;

public class Test {
	private int ver;
	private String top;
	private String emoji;
	private String etxt;
	private String bottom;
	private String right;
	
	public Test() {}
	
	public Test(int ver, String top, String emoji, String etxt, String bottom, String right) {
		super();
		this.ver = ver;
		this.top = top;
		this.emoji = emoji;
		this.etxt = etxt;
		this.bottom = bottom;
		this.right = right;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getEtxt() {
		return etxt;
	}

	public void setEtxt(String etxt) {
		this.etxt = etxt;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Test [ver=" + ver + ", top=" + top + ", emoji=" + emoji + ", etxt=" + etxt + ", bottom=" + bottom
				+ ", right=" + right + "]";
	}
	
	
	
	
	
}
