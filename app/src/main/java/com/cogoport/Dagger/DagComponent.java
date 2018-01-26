package com.cogoport.Dagger;

import com.cogoport.Dagger.module.DependentClass;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DaggerModule.class})
public interface DagComponent {

    DependentClass provideVehicle();

}