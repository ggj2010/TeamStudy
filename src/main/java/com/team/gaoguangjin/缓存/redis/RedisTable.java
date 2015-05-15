package com.team.gaoguangjin.缓存.redis;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedisTable {
	private Long redisId; // 保存redis的主键ID
	private String redisType;// redis的类型如：set/list/hash/sortedset/string
	private String redisKey;// 保存redis时使用的key
	private String objectName;// 此属性主要用于hash数据结构时，保存member的
	private String redisValue;// 存储的redis的值
	private String keyToken;// 保存Token时，为区分拼接的字符串
	private String score;// 此属性为sortedset数据结构时，保存的score值
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private String macIp;// redis的IP地址 当然此处也可以存储mac地址
	private String port;// redis使用的端口号
	private String appCode;// 应用区分码
	private String remark;// 备注
	private String isModify;// 是否修改。此属性可以用于增量备份时，即在每个redis存储时可以更具key多存储一个属性isModify。 如果有修改，则置为 Y,否则为N.
}
