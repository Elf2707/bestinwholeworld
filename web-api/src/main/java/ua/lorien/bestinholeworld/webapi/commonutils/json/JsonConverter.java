package ua.lorien.bestinholeworld.webapi.commonutils.json;

import com.google.gson.Gson;

public class JsonConverter {
	
	public static <T> String objToJsonString(T objToConvert){
		return new Gson().toJson(objToConvert);
	}
	
	public static <T> T jsonToObj(String json, Class<T> objClass){
		return new Gson().fromJson(json, objClass);
	}
}
