package com.genome.dx.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.modelmapper.ModelMapper;

public class ModelBase {
    @JsonIgnore
    public <T> T map(Class<T> classs) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, classs);
    }

}
