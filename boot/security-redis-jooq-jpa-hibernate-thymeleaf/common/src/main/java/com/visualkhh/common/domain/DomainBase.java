package com.visualkhh.common.domain;

import org.modelmapper.ModelMapper;

public class DomainBase {
    public <T> T map(Class<T> classs){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, classs);
    }
}
