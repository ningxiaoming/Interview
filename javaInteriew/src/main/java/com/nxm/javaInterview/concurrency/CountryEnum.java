package com.nxm.javaInterview.concurrency;


/*
    关于枚举 的使用 教学：    类似与一个小数据库。

 */

public enum CountryEnum {

    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),
    FOUR(4,"赵国"),FIVE(5,"韩国"),SIX(6,"魏国");



    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode,String retMessage){
        this.retCode=retCode;
        this.retMessage=retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum:values
             ) {
            if (countryEnum.retCode==index){
                return countryEnum;
            }
            
        }
        return null;
    }
}
