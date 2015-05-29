package com.ttianjun.mybatis;

import com.ttianjun.mybatis.test.User;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wodetianjun on 15/5/27.
 */
public class MyBatisUtil {
    public static void main(String [] args) throws IOException {
        String xml=MyBatisUtil.createXml(User.class);
        System.out.println(xml);
    }
    public static String createXml(Class c) throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/template.txt");

        String tableName = c.getSimpleName();
        KTable table = (KTable) c.getAnnotation(KTable.class);
        if(table!=null) tableName = table.value();

        Map<String,String> idBean = buildId(c);
        t.binding("className", c.getCanonicalName());
        t.binding("fields", buildFields(c));
        t.binding("params", buildParam(c));
        t.binding("tableName",tableName);
        t.binding("tableId",buildId(c));
        return t.render();
    }
    private static Map<String,String> buildId(Class bean){
        Map<String,String> map = new LinkedHashMap<String, String>();
        Field[] fields=bean.getDeclaredFields();
        for(Field field : fields){
            String key = field.getName();
            KId id=field.getAnnotation(KId.class);
            if(id!=null) {
                String fieldName = field.getName();
                String value = id.value().isEmpty() ? fieldName : id.value();
                map.put("field", fieldName);
                map.put("column", value);
            }
        }
        return map;
    }
    private static Map<String,String> buildFields(Class bean){
        Map<String,String> map = new LinkedHashMap<String, String>();
        Field[] fields=bean.getDeclaredFields();
        for(Field field : fields){
            String key = field.getName();
            KField commit=field.getAnnotation(KField.class);
            if(commit!=null) {
                String value = commit.value().isEmpty() ? field.getName() : commit.value();
                map.put(key, value);
            }
        }
        return map;
    }
    private static Map<String,String> buildParam(Class bean){
        Map<String,String> map = new LinkedHashMap<String, String>();
        Field[] fields=bean.getDeclaredFields();
        for(Field field : fields){
            String key = field.getName();
            KParam commit=field.getAnnotation(KParam.class);
            if(commit!=null) {
                String value = commit.value().isEmpty() ? field.getName() : commit.value();
                map.put(key, value);
            }
        }
        return map;
    }
}
