package com.edu.util;

import java.util.UUID;

public class UUIDUtil {
	
    public static String getCode(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


}
