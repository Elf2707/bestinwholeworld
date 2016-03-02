package ua.lorien.bestinholeworld.webapi.commonutils.json.typeadapter.date;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonDateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement jsonDate, Type type, JsonDeserializationContext context) throws JsonParseException {
		String dateString = jsonDate.getAsString();
		if( dateString == null){
			throw new JsonParseException("Error to convert Date from json " + dateString);
		}
		
		Date date;
		try {
			date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).parse(dateString);
		} catch (ParseException e) {
			throw new JsonParseException("Error to convert Date from json " + dateString, e);
		}
		
		return date; 
	}

}
