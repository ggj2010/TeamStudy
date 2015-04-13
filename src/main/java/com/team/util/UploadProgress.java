package com.team.util;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.fileupload.ProgressListener;

/**
 * @ClassName:UploadProgress.java
 * @Description: 上传文件带提示条的
 * @author gaoguangjin
 * @Date 2015-4-8 下午5:09:55
 */
@Getter
@Setter
public class UploadProgress implements ProgressListener {
	private double upRate = 0.0;
	private double percent = 0.0;
	private long useTime = 0;
	private long upSize = 0;
	private long allSize = 0;
	private int item;
	private long beginT = System.currentTimeMillis();
	private long curT = System.currentTimeMillis();
	
	public void update(long pBytesRead, long pContentLength, int pItems) {
		curT = System.currentTimeMillis();
		item = pItems;
		allSize = pContentLength; // byte
		upSize = pBytesRead; // byte
		useTime = curT - beginT; // ms
		if (useTime != 0)
			upRate = pBytesRead / useTime; // byte/ms
		else
			upRate = 0.0;
		if (pContentLength == 0)
			return;
		percent = (double) pBytesRead / (double) pContentLength; // 百分比
	}
	
}
