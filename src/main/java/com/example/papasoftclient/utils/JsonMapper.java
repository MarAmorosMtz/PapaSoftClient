package com.example.papasoftclient.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
    private static ObjectMapper mapper;

    public static ObjectMapper getMapper(){
        if(mapper == null){mapper = new ObjectMapper();}
        return mapper;
    }
}
