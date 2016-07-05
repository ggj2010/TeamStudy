package com.team.gaoguangjin.设计模式.工厂模式.工厂方法模式;

import java.net.URL;
import java.util.List;

import com.alibaba.dubbo.registry.NotifyListener;

/**
 *
 * @author:gaoguangjin
 * @date 2016/5/6 9:34
 */
public class RedisRegistry implements Registry {
    @Override
    public void register(URL url) {

    }

    @Override
    public void unregister(URL url) {

    }

    @Override
    public void subscribe(URL url, NotifyListener listener) {

    }

    @Override
    public void unsubscribe(URL url, NotifyListener listener) {

    }

    @Override
    public List<URL> lookup(URL url) {
        return null;
    }
}
