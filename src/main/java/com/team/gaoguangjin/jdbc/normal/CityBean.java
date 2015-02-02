package com.team.gaoguangjin.jdbc.normal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityBean {
	public String id;
	public String address_name;
	public String parent_id;
	public String sequence;
	
	public String toString() {
		return id + ":" + address_name + ":" + parent_id + ":" + sequence;
	}
}
