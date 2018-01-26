package com.cogoport.Dagger;

import com.cogoport.Dagger.module.DependentClass;
import com.cogoport.Dagger.module.IndependentClass;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DaggerModule {

    @Provides @Singleton
    IndependentClass provideind(){
        return new IndependentClass();
    }

    @Provides @Singleton
    DependentClass provideVehicle(){
        return new DependentClass(new IndependentClass());
    }
}