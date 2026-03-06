package com.bookmystay.exception;

public class RoomTypeException extends RuntimeException{

	public RoomTypeException(String roomType) {
        super("Room type not found: " + roomType);
    }

}
