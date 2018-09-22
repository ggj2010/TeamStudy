package com.team.gaoguangjin.算法.排序;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Bit-map的基本思想
 * 　　32位机器上，对于一个整型数，比如int a=1 在内存中占4*8=32bit位，这是为了方便计算机的运算。但是对于某些应用场景而言，
 * 这属于一种巨大的浪费，因为我们可以用对应的32bit位对应存储十进制的0-31个数，而这就是Bit-map的基本思想。
 * Bit-map算法利用这种思想处理大量数据的排序、查询以及去重。
 * 　　Bitmap在用户群做交集和并集运算的时候也有极大的便利
 * 参考：https://www.jianshu.com/p/6082a2f7df8e
 *
 * @author:gaoguangjin
 * @date:2018/9/21
 */
@Slf4j
public class BiteMapTest {

    private final static int maxSize = 10000 * 1;
    private int capacity;
    private byte[] bitArray;

    private BiteMapTest(int capacity) {
        bitArray = new byte[(capacity >> 3) + 1];
    }

    /* 举一个例子，有一个无序有界int数组{1,2,5,7},初步估计占用内存4*4=16字节，这倒是没什么奇怪的，
     但是假如有10亿个这样的数呢，10*4/(1024*1024*1024)=3.72G左右。
     如果这样的一个大的数据做查找和排序，那估计内存也崩溃了，有人说，这些数据可以不用一次性加载，
     那就是要存盘了，存盘必然消耗IO。我们提倡的是高性能，这个方案直接不考虑。*/
    //如果int的值用bit去存储，那就节省了32倍。
    //8个int 如果用bit去存储其实就是1byte,而对于int来说就是8*4=32byte 省了32倍
    // 1byte=8 bit  1int=4 byte= 4*8=32 bit
    //{1,2,5,7}
    //利用二进制0或者1，如果用bit位置代表值的有和无
    // 1010 0110  就可以代表 1,2,5,7
    // 7654 3210

    //一个数怎么快速定位它的索引号,也就是说搞清楚byte[index]的index是多少
    //比如 10 肯定超过byte[0],
    // 所以 index= 10/8= 10>>3
    // 对应的positon=10%8= N & 0x07;
    //增加一个值就是对应的index的值，变成1，其实就是将1左位移positon位置，再和原来的值求|。
    // add(10)=byte[index=10/8]=1<<positon(10%8)
    public static void main(String[] args) {
        Random random=new Random();
        BiteMapTest biteMapTest = new BiteMapTest(maxSize);
        for (int i = 0; i < maxSize; i++) {
            biteMapTest.add(random.nextInt(maxSize));
        }
        biteMapTest.sort();
    }

    private void sort() {
        //
        for (int i = 0; i < bitArray.length; i++) {
            //1010 1101
            //1010 1101
            int a = bitArray[i];

            for (int j = 0; j < 8; j++) {
                if ((a & 1 << j) > 0) {
                    log.info("exit :{}", i * 8 + j);
                }
            }
        }
    }

    /**
     * 增加其实将所在的位置从0变成1.其他位置不变.
     * 将1左位移到positon位置，再和原来的值求|。
     *
     * @param number
     * @return
     */
    public byte[] add(int number) {
        int index = number >> 3;
        int positon = number & 0x07;
        bitArray[index] |= 1 << positon;
        return bitArray;
    }

    /**
     * 去除就是将所在的位置变成0，其他位置不变。
     * 将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
     *
     * @param number
     * @return
     */
    public void remove(int number) {
        int index = number >> 3;
        int positon = number & 0x07;
        if ((bitArray[index] & (1 << positon)) != 0) {
            bitArray[index] &= ~(1 << positon);
        }
    }

    /**
     * 判断值是否存在，其实就是对应位置值是否是1
     * 将1左移position后，那个位置自然就是1,然后再和原来的值就&
     * 因为其他位置都是0，如果当前位置存在，那就1&1大于0
     *
     * @param number
     * @return
     */
    public boolean contains(int number) {
        int index = number >> 3;
        int positon = number & 0x07;
        return (bitArray[index] & (1 << positon)) != 0;
    }


}
