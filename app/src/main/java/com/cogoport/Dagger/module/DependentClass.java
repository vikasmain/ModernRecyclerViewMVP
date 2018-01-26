package com.cogoport.Dagger.module;



public class DependentClass {

    private IndependentClass ind;

    public DependentClass(IndependentClass ind){
        this.ind = ind;
    }

    public void increaseSpeed(String value){
        ind.add(value);
    }

    public void stop(){
        ind.nu();
    }

    public String getString(){
        return ind.getStr();
    }
}