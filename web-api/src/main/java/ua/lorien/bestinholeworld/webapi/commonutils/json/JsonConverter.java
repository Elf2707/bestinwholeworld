package ua.lorien.bestinholeworld.webapi.commonutils.json;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ua.lorien.bestinholeworld.webapi.commonutils.json.typeadapter.date.JsonDateDeserializer;
import ua.lorien.bestinholeworld.webapi.commonutils.json.typeadapter.date.JsonDateSerializer;

public class JsonConverter {
	
	public static <T> String objToJsonString(T objToConvert){
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new JsonDateSerializer());
		Gson gson = gb.create();
		return gson.toJson(objToConvert);
	}
	
	public static <T> T jsonToObj(String json, Class<T> objClass){
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(Date.class, new JsonDateDeserializer());
		Gson gson = gb.create();
		return gson.fromJson(json, objClass);
	}
}
