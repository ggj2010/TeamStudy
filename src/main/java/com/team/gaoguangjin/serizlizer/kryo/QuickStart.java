package com.team.gaoguangjin.serizlizer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.team.gaoguangjin.serizlizer.kryo.bean.SerizlizerBean;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author:gaoguangjin
 * @date:2017/12/15
 */
@Slf4j
public class QuickStart {
    public static void main(String[] args) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        SerizlizerBean serizlizerBean = new SerizlizerBean();
        serizlizerBean.setName("ggdsafsdafafaj");
        serizlizerBean.setAge(2712321);
        Output output = new Output(new FileOutputStream("/tmp/test.bin"));
        kryo.writeObject(output, serizlizerBean);
        output.flush();
        output.close();
        SerizlizerBean deseriable = kryo.readObject(new Input(new FileInputStream("/tmp/test.bin")), SerizlizerBean.class);
        log.info(deseriable.getName());
    }
}
