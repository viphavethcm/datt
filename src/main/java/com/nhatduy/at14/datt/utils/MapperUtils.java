package com.nhatduy.at14.datt.utils;

import org.modelmapper.ModelMapper;

public class MapperUtils {
    public static <S,T> T mapperObject(S source, Class<T> targetObject){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, targetObject);
    }
}
