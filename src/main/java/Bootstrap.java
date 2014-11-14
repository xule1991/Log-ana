import com.xule.tools.Processer;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: lxu
 * Date: 11/14/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {
    public static void main(String []args) {
        Processer processer = new Processer("C:\\Users\\lxu\\IdeaProjects\\local\\log-ana\\target\\hydra.log");
        processer.process();
        //processer.showClassTypes();
        processer.showDescription();
    }
}
