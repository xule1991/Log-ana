import com.xule.utils.PropertiesUtil;
import com.xule.utils.SingleLogSeparateProcesser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {
    public static void main(String []args) {
        String propFileName = "config.properties";
        Properties prop = new PropertiesUtil().createProperties(propFileName);
        Map<String, String> context = createContext(prop);
        SingleLogSeparateProcesser singleLogSeparateProcesser = new SingleLogSeparateProcesser(context);
        singleLogSeparateProcesser.process();
    }

    private static Map<String,String> createContext(Properties prop) {
        Map<String, String> context = new HashMap<String, String>();
        for (Object key : prop.keySet()) {
            Object value = prop.get(key);
            context.put((String) key, (String) value);
        }
        return context;
    }

}
