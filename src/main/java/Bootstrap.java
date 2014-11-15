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
        SingleLogSeparateProcesser singleLogSeparateProcesser = new SingleLogSeparateProcesser("D:\\EA\\Log-ana-master\\src\\main\\resources\\hydra.log");
        singleLogSeparateProcesser.process();
    }
}
