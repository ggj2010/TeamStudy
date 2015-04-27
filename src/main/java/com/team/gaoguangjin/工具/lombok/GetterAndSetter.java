package com.team.gaoguangjin.工具.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
/***打印**/
@ToString
/**带参数的构造方法*/
@AllArgsConstructor
/**日志**/
@Slf4j
// @Log4j
public class GetterAndSetter {
	private String name;
	private String age;
	private String sex;
	
	public static void main(String[] args) {
		log.info("日志" + new GetterAndSetter("高广金", "24", "男"));
	}
}
