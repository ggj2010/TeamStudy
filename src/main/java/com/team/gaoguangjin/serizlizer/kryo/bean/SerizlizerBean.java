package com.team.gaoguangjin.serizlizer.kryo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:gaoguangjin
 * @date:2017/12/15
 */
@Data
public class SerizlizerBean implements Serializable{
    private static final long serialVersionUID = 1533683778049042828L;
    private String name;
    private int age;
}
