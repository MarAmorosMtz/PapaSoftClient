package com.example.papasoftclient.repositories;

public class RestAPI {
    public static final String API_VERSION = "v0.5.0";
    public static final String HOST = "http://localhost:8000/api/"+API_VERSION;
    public static final String CARRERAS_ENDPOINT = HOST+"/carreras/";
    public static final String MATERIAS_ENDPOINT = HOST+"/materias/";
    public static final String MAESTROS_ENDPOINT = HOST+"/maestros/";
    public static final String PERIODOS_ENDPOINT = HOST+"/periodos/";
    public static final String SALONES_ENDPOINT = HOST+"/salones/";
    public static final String ASESORADOS_ENDPOINT = HOST+"/asesorados/";
    public static final String ASESORIAS_ENDPOINT = HOST+"/asesorias/";
    public static final String HORARIOS_ASESORES_ENDPOINT = HOST+"/horarios/asesores";
    public static final String HORARIOS_SALONES_ENDPOINT = HOST+"/horarios/salones";
    public static final String ASESORES_ENDPOINT = HOST+"/asesores";
    public static final String LOGIN_ENDPOINT = HOST+"/login";
}
