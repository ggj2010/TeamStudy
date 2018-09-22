package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

/**
 * 运行时环境：-XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=2
 * jstat -gc pid 3000
 * @author:gaoguangjin
 * @date:2018/5/10
 */
public class TestJstatMinorGC {
    //setp1:edn use 1m
    public static final int _1MB = 1024 * 1024;

    /**
     *SurvivorRatio=2  则 10/2+2 则s1=2.5m 2s=2.5m edn=5m
     *
     * gc setp 1~5
     * S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
     2560.0 2560.0  0.0    0.0    5120.0   1411.1   10240.0      0.0     4480.0 785.3  384.0   75.9       0    0.000   0      0.000    0.000
     2560.0 2560.0  0.0    0.0    5120.0   3459.2   10240.0      0.0     4480.0 785.3  384.0   75.9       0    0.000   0      0.000    0.000
     2560.0 2560.0  0.0   2548.1  5120.0   2048.0   10240.0     104.0    4864.0 2736.5 512.0  289.5       1    0.004   0      0.000    0.004
     2560.0 2560.0  0.0   2548.1  5120.0   4193.9   10240.0     104.0    4864.0 2736.5 512.0  289.5       1    0.004   0      0.000    0.004
     2560.0 2560.0 2548.1  0.0    5120.0   2048.0   10240.0     4224.0   4864.0 2736.9 512.0  289.5       2    0.011   0      0.000    0.011
     jstat -gcutil 1109 5000
     S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
     0.00   0.00  27.56   0.00  17.53  19.76      0    0.000     0    0.000    0.000
     0.00   0.00  67.56   0.00  17.53  19.76      0    0.000     0    0.000    0.000
     0.00  99.53  40.00   1.09  56.26  56.54      1    0.003     0    0.000    0.003
     0.00  99.53  81.91   1.09  56.26  56.54      1    0.003     0    0.000    0.003
     98.91  0.00  40.00  41.48 56.27  56.54       2    0.012     0    0.000    0.012
     * @param args
     */
    public static void main(String[] args) {
        byte[] a, b, c, d,e,f,g;
        //当前3m
        a = new byte[2 * _1MB];//setp2: edn use:3m
        //当前5m edn use:5m不够，触发mionr gc,
        b = new byte[2 * _1MB];//setp3:   edn use:2.5m surivor from use:2.5m
        //当前7m
        c = new byte[2 * _1MB];//setp4: edn use:4.5m,surivor from use:2.5m 不触发gc
        //当前9m，edn use 6.5m 不够 触发mionr gc,
        d = new byte[2 * _1MB];//setp5: edn use 2.5m surivor to use 2.5m  old use 4m
    }

    /**
     *【 GC 前该区域已使用容量 -> GC 后该区域已使用容量 (该区域内存总容量) 】  【GC 前Java堆已使用容量 -> GC后Java堆已使用容量 （Java堆总容量）】
     [GC (Allocation Failure) [PSYoungGen: 4986K->1514K(7680K)] 4986K->1522K(17920K), 0.0018209 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
     [GC (Allocation Failure) [PSYoungGen: 5760K->2528K(7680K)] 5768K->5423K(17920K), 0.0048279 secs] [Times: user=0.03 sys=0.01, real=0.00 secs]
     Heap
     PSYoungGen      total 7680K, used 6792K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     eden space 5120K, 83% used [0x00000007bf600000,0x00000007bfa2a128,0x00000007bfb00000)
     from space 2560K, 98% used [0x00000007bfd80000,0x00000007bfff8010,0x00000007c0000000)
     to   space 2560K, 0% used [0x00000007bfb00000,0x00000007bfb00000,0x00000007bfd80000)
     ParOldGen       total 10240K, used 2895K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     object space 10240K, 28% used [0x00000007bec00000,0x00000007beed3fe0,0x00000007bf600000)
     Metaspace       used 3195K, capacity 4496K, committed 4864K, reserved 1056768K
     class space    used 349K, capacity 388K, committed 512K, reserved 1048576K
     *
     */

}
