package com.bookmystay.exception;

public class InsufficientRoomException extends RuntimeException{
public InsufficientRoomException(String type,int requested, int available) {
	super("Insufficient room type : "+type+", Requested: " + requested + ", Available: " + available);
}
}
