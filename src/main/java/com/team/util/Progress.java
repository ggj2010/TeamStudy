package com.team.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Progress {
	public long pBytesRead;
	public long pContentLength;
	public int pItems;
	public String percent;
	public boolean isover = false;
	
	public String toString() {
		return pBytesRead + "" + pContentLength + pItems;
	}
	
}
