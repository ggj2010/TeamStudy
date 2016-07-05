package com.team.gaoguangjin.javabase.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 金额处理
 * @author:gaoguangjin
 * @date 2016/5/25 14:39
 */
@Slf4j
public class BigdecimalTest {

    public static void main(String[] args) {
        normal();

    }

    private static void normal() {



        /*金额的乘法要注意！！！！！！！*/
        float a=15000.48f;
        float b=0.001f;
        int c=10;
        log.info("使用floa乘以float会导致丢失精度="+a*b);
        log.info("使用floa乘以int会导致丢失精度="+a*c);

        double d=20.5;
        double e=0.2;
        double m=d*e;
        log.info("使用double*double会导致丢失精度="+d*e+"=====m"+m);

        //佣金金额
        BigDecimal totalOrderAmount = new BigDecimal(1.05);
        //分享金额
        BigDecimal totalShareAmount = new BigDecimal(4.05);


        //相加 总金额
        BigDecimal  total=totalOrderAmount.add(totalShareAmount);
        log.info("add= 相加后"+total.toString());


        //每天订单佣金占比统计(售卖佣金占比=佣金/总金额  总金额=分享赚所得金额+佣金)
        //通过BigDecimal的divide方法进行除法时当不整除，出现无限循环小数时，就会抛异常的 ,解决之道：就是给divide设置精确的小数点divide(xxxxx,2, BigDecimal.ROUND_HALF_EVEN)
        BigDecimal big = (totalOrderAmount.divide(total,2,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
        log.info("收入百分比big="+big.toString());
        //ROUND_HALF_UP: 遇到.5的情况时往上近似,例: 1.5 ->;2   ROUND_HALF_DOWN : 遇到.5的情况时往下近似,例: 1.5 ->;1
        float commissionPercent=big.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
        log.info("收入百分比CommissionPercent="+commissionPercent);

        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
        log.info("multiply="+interest.toString());







    }


}
