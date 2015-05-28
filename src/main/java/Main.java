import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wodetianjun on 15/5/27.
 */
public class Main {
    public static void main(String [] args) throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/template.txt");

        Map<String,String> map = buildMap(User.class);
        t.binding("map",map);
        String str = t.render();
        System.out.println(str);
    }
    public static Map<String,String> buildMap(Class c){
        Map<String,String> map = new LinkedHashMap<String, String>();
        Field[] fields=c.getDeclaredFields();
        for(Field field : fields){
            Commit commit=field.getAnnotation(Commit.class);
            String key = field.getName();
            String value=commit==null?field.getName():commit.value();
            map.put(key,value);
        }
        return map;
    }
}
