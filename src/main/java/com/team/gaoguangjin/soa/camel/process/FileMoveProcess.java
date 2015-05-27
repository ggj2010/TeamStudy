package com.team.gaoguangjin.soa.camel.process;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class FileMoveProcess implements Processor {
	public void process(Exchange exchange) throws Exception {
		BufferedReader in = null;
		try {
			InputStream body = exchange.getIn().getBody(InputStream.class);
			
			in = new BufferedReader(new InputStreamReader(body, "utf-8"));
			StringBuffer strbf = new StringBuffer("");
			String str = null;
			str = in.readLine();
			while (str != null) {
				strbf.append(str + " ");
				str = in.readLine();
			}
			
			log.info("过程打印：" + strbf.toString());
		} catch (Exception e) {
			
		}
		finally {
			in.close();
		}
		
	}
	
}
