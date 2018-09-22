package com.team.gaoguangjin.javabase.string;

import lombok.extern.slf4j.Slf4j;



/**
 * 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。
 * 但是要保证汉字不被截半个，如“我ABC”4，应该截为“我AB”，输入“我ABC汉DEF”，6，应该输出为“我ABC”而不是“我ABC+汉的半个
 * @author:gaoguangjin
 * @date 2017/2/15 10:30
 */
@Slf4j
public class StringSplitTest {
    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        String world=scanner.next();
//        String result=getSplitResult(world,scanner.nextInt(),"gbk");
        log.info(getSplitResult("我ABC汉DE",1,"GBK"));
        log.info(getSplitResult("我ABC汉DE",4,"GBK"));
        ////截取1个中文等于substring(0,5-1)
        log.info(getSplitResult("我ABC汉DE",5,"GBK"));
        //截取1个半中文等于substring(0,6-2)
        log.info(getSplitResult("我ABC汉DE",6,"GBK"));
        //截取2个中文 等于substring(0,7-2)
        log.info(getSplitResult("我ABC汉DE",7,"GBK"));
        log.info(getSplitResult("我ABC汉DE",8,"GBK"));
        log.info(getSplitResult("我ABC汉DE",9,"GBK"));
        log.info(getSplitResult("我ABC汉DE高",10,"GBK"));
        log.info(getSplitResult("我ABC汉DE高",11,"GBK"));

    }

    /**
     * 算出来有多少个中文汉字，然后截取的时候减去中文汉字
     * @param world
     * @param number
     * @param CharacterName
     * @return
     */
    private static String getSplitResult(String world, int number,String CharacterName) {
        int temp=0;
        try {
            byte[] byteArray = world.getBytes(CharacterName);
            for (int i = 0; i < number; i++) {
                if(byteArray[i]<0){
                   temp+=1;
                }
            }
            //整除的代表截取完整的一个字
            if(temp%2==0) {
                temp = temp / 2;
            }else{
                temp=(temp / 2)+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return world.substring(0,number-temp);
    }


}
