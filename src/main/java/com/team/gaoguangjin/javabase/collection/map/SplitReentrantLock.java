package com.team.gaoguangjin.javabase.collection.map;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:gaoguangjin
 * @date 2017/6/20 9:12
 */
public class SplitReentrantLock {
    private Lock[] locks;

    private int LOCK_NUM;

    public SplitReentrantLock(int lockNum) {
        super();
        LOCK_NUM = lockNum;
        locks = new Lock[LOCK_NUM];
        for (int i = 0; i < LOCK_NUM; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    /**
     * 获取锁, 使用HashMap的hash算法
     *
     *
     * @param key
     * @return
     */
    public Lock getLock(String key) {

        int lockIndex = index(key);
        return locks[lockIndex];
    }

    int index(String key) {
        int hash = hash(key.hashCode());
        return hash & (LOCK_NUM - 1);
    }

    int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}
