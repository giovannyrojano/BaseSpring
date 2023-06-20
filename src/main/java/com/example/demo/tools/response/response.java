package com.example.demo.tools.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class response<T> {
    private boolean error;
    private String message;
    private T data;
    private Iterable<T> data_array;

    public response( T data) {
        this.data = data;
    }

    public response(boolean error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public response(boolean error, String message, T data, Iterable<T> data_array) {
        this.error = error;
        this.message = message;
        this.data = data;
        this.data_array = data_array;
    }

       public boolean isError() {
        return error;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
    public Iterable<T> getData_array() {
        return data_array;
    }

    public void setData_array(List<T> data_array) {
        this.data_array = data_array;
    }


   public static <T> ResponseEntity <response<T>> successResponse() {
        return successResponse("OK",null,new ArrayList<T>());
    }
       public static <T> ResponseEntity <response<T>> successResponse(T data) {
        return successResponse("OK",data,new ArrayList<T>());
    }
    public static <T> ResponseEntity <response<T>> successResponse(Iterable<T> data_array) {
        return successResponse("OK",null,data_array);
    }
    public static <T> ResponseEntity <response<T>>successResponse(String message,T data,Iterable<T> data_array){
      return   new ResponseEntity<> (new response<T>(false,message,data,data_array), HttpStatus.OK);
    }



/**
 * Creates a success response with a default message and empty data.
 * @return ResponseEntity object representing the created success response.
 */
   public static <T> ResponseEntity <response<T>> createdSuccessResponse() {
        return createdSuccessResponse("REGISTRO CREADO CORRECTAMENTE",null,new ArrayList<T>());
    }
    public static <T> ResponseEntity <response<T>> createdSuccessResponse(String message){
        return   new ResponseEntity<> (new response<T>(false,message,null, new ArrayList<T>()), HttpStatus.CREATED);
    }
    public static  <T> ResponseEntity <response<T>> createdSuccessResponse(String message,T data){
 return   new ResponseEntity<> ( new response<T>(false,message,data, new ArrayList<T>()), HttpStatus.CREATED);
    }
    public static <T> ResponseEntity <response<T>>createdSuccessResponse(String message,Iterable<T> data_array){
         return   new ResponseEntity<> (new response<T>(false,message,null, data_array), HttpStatus.CREATED);
    }

    /**
     * Creates a success response with a custom message, data, and iterable data.
     * @param message The custom message for the response.
     * @param data The data to be included in the response.
     * @param data_array The iterable data to be included in the response.
     * @return ResponseEntity object representing the created success response.
     */
    public static <T> ResponseEntity <response<T>>createdSuccessResponse(String message,T data,Iterable<T> data_array){
      return   new ResponseEntity<> (new response<T>(false,message,data,data_array),  HttpStatus.CREATED);
    }



}
