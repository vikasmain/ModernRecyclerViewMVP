package com.cogoport.Dagger.module;


public class IndependentClass {

    private String data;

    public IndependentClass(){
        this.data = "Hello Guys I am Dagger2 Use me To Provide \n\nDependency Injection \n\nto your code\n\nFor using me Rebuild Project time to time";
    }

    public String getStr(){
        return data;
    }

    public void add(String value){
        data = data + value;
    }

    public void nu(){
        data = " ";
    }
}