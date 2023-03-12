package org.sivakamiveerapathiran.onlinenursery.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    private String message;
    private static final long serialVersionUID = 1L;
public  CustomerAlreadyExistsException(){

}
    public CustomerAlreadyExistsException(String message) {
        this.message = message;
    }
}
