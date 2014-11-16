import com.xule.utils.SingleLogSeparateProcesser;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {
    public static void main(String []args) {
        SingleLogSeparateProcesser singleLogSeparateProcesser = new SingleLogSeparateProcesser("inputfiles\\http_access.log.2014-11-15-16");
        singleLogSeparateProcesser.process();
    }
}
