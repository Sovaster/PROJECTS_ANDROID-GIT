package com.example.recyclerview;



public class Model {
    private String day;
    private String para1;
    private String para2;
    private String para3;
    private String para4;
    private String para5;
    private String prepod1;
    private String prepod2;
    private String prepod3;
    private String prepod4;
    private String prepod5;

    public Model( String day,
             String para1,
             String para2,
             String para3,
             String para4,
             String para5,
             String prepod1,
             String prepod2,
             String prepod3,
             String prepod4,
             String prepod5){
        this.day = day;
        this.para1 = para1;
        this.para2 = para2;
        this.para3 = para3;
        this.para4 = para4;
        this.para5 = para5;
        this.prepod1 = prepod1;
        this.prepod2 = prepod2;
        this.prepod3 = prepod3;
        this.prepod4 = prepod4;
        this.prepod5 = prepod5;
    }

    public String getDay(){ return day;}
    public String getPara1(){ return para1;}
    public String getPara2(){ return para2;}
    public String getPara3(){ return para3;}
    public String getPara4(){ return para4;}
    public String getPara5(){ return para5;}
    public String getPrepod1(){ return prepod1;}
    public String getPrepod2(){ return prepod2;}
    public String getPrepod3(){ return prepod3;}
    public String getPrepod4(){ return prepod4;}
    public String getPrepod5(){ return prepod5;}
}

