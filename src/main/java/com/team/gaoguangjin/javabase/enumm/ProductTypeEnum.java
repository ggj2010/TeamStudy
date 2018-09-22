package com.team.gaoguangjin.javabase.enumm;

public enum ProductTypeEnum {
    /**
     * ktv
     */
    KTVReserv(1, "ktv_reserv"),
    /**
     * 景点门票
     */
    Ticket(2, "ticket"),
    /**
     * 演出
     */
    Show(3, "show_reserv"),
    /**
     * 密室
     */
    BackRoom(4, "backroom"),
    /**
     * 游乐游艺
     */
    YouLeYouYi(5, "youleyouyi"),
    /**
     * 中医养生
     */
    ZhongYiYangSheng(6, "zhongyiyangsheng"),
    /**
     * 足疗按摩
     */
    ZuLiaoAnMo(7, "zuliaoanmo"),
    /**
     * ktv点单
     */
    KtvMarket(8, "ktvmarket"),
    /**
     * ktv商户通
     */
    ktvSHT(9, "ktvsht"),
    /**
     * 轰趴
     */
    HongPa(10, "hongpa"),
    /**
     * 私人影院
     */
    SiRenYingYuan(11, "sirenyingyuan"),
    /**
     * 齿科
     */
    ChiKe(12, "chike"),
    /**
     * 体检中心
     */
    TiJianZhongXin(13, "tijianzhongxin"),
    /**
     * 其他医疗
     */
    QiTaYiLiao(14, "qitayiliao"),
    /**
     * 教育课程
     */
    EduCourse(15, "education_course"),
    /**
     * 一分钱唱K
     */
    KtvOneCentK(16, "ktv_onecentk"),
    /**
     * 一分钱抽奖活动
     */
    LotteryActivity(17, "lottery_activity"),
    /**
     * 运动健身预约
     */
    FitnessBook(18, "fitness_book");

    private int code;
    private String desc;

    private ProductTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ProductTypeEnum valueOf(int code) {
        for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
            if (productTypeEnum.getCode() == code) return productTypeEnum;
        }
        return null;
    }
}
