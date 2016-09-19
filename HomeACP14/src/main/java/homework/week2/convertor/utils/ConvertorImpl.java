package homework.week2.convertor.utils;

import com.google.gson.Gson;

import java.lang.reflect.Field;

public class ConvertorImpl implements Convertor {

    public ConvertorImpl() {
    }

    @Override
    public String convertToJson(Object obj) {

        if(obj == null){
            return null;
        }

        String result = "{" + toJson(obj) + "}" ;
        return result;
    }

    private String toJson(Object obj){
        Class cl = obj.getClass();

        String toJson = "";

        for (Field field : cl.getDeclaredFields()) {

            try {
                Class<?> fieldType = field.getType();
                if (!fieldType.isPrimitive() && !(fieldType == String.class)) {
                    toJson += "\"" + field.getName() + "\":{";
                    String res = toJson(field.get(obj));
                    toJson += res.substring(0, res.lastIndexOf(',')) + "}";
                } else {
                    if(fieldType == int.class){
                        toJson += "\"" + field.getName() + "\":" + field.get(obj) + ",";
                    } else {
                        toJson += "\"" + field.getName() + "\":\"" + field.get(obj) + "\",";
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return toJson;
    }

    @Override
    public String convertToXml(Object obj) {

        if(obj == null){
            return null;
        }

        String result = "<team>\n\t" + toXML(obj);
        return result + "</team>" ;
    }

    private String toXML(Object obj){
        Class cl = obj.getClass();

        String toXML = "";

        for (Field field : cl.getDeclaredFields()) {

            try {
                Class<?> fieldType = field.getType();

                if (!fieldType.isPrimitive() && !(fieldType == String.class)) {
                    toXML += "<" + field.getName() + ">" + "\n\t\t";
                    String res = toXML(field.get(obj));
                    res = res.replace("\t", "\t\t");
                    toXML += res.substring(0, res.lastIndexOf("\t")) + "</" + field.getName() + ">\n";
                } else {
                    toXML += "<" + field.getName() + ">" + field.get(obj) + "</" + field.getName() + ">\n\t";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return toXML;
    }

}