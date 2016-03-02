package ua.lorien.bestinholeworld.webapi.commonutils.json.typeadapter.date;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonDateSerializer implements JsonSerializer<Date> {

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		return date == null ? null : new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date));
	}
}
