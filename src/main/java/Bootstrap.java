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
        Map<String, String> context = new PropertiesUtil().createProperties(propFileName);
        SingleLogSeparateProcesser singleLogSeparateProcesser = new SingleLogSeparateProcesser(context);
        singleLogSeparateProcesser.process();
    }
}
