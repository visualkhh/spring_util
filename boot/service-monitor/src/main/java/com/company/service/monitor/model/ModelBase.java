package com.company.service.monitor.model;

import org.modelmapper.ModelMapper;

public class ModelBase {
    public <T> T map(Class<T> classs){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, classs);
    }
}
