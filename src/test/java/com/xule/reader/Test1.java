package com.xule.reader;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    @Test
    public void test() {
        String str = "NamespaceMapper loaded mapping from nucleus NS [ swtor ] to VCM Game ID [ BASE_MSG ]";
        str = str.replaceAll("\\[.*\\]", "");
        System.out.println(str);;
    }
    @Test
    public void test1() {
        String str = "Got reponse body: <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<pidProfileEntry>\n" +
                "    <pidUri>/pids/1000000075039/</pidUri>\n" +
                "    <entryId>1000000075039</entryId>\n" +
                "    <entryCategory>SECURITY_QA</entryCategory>\n" +
                "    <entryElement>\n" +
                "        <profileType>SEC_QUESTION</profileType>\n" +
                "        <value>What is the name of your favorite pet?</value>\n" +
                "    </entryElement>\n" +
                "    <entryElement>\n" +
                "        <profileType>SEC_ANSWER</profileType>\n" +
                "        <value>1234</value>\n" +
                "    </entryElement>\n" +
                "</pidProfileEntry>";
        Pattern pattern = Pattern.compile("^((.|\\n)+)$");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.find());
        System.out.println(matcher.group(0).equals(matcher.group(1)));
        /*
        if (matcher.find()) {
            System.out.println(matcher.find(0));
            System.out.println(matcher.find(1));
            System.out.println(matcher.find(2));
            System.out.println(matcher.find(3));
            System.out.println(matcher.find(4));
            System.out.println(matcher.find(5));
        }*/
    }
    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("^([0-9]+)\\s+(\\[.+\\])\\s+(\\w+)\\s+([\\w\\.]+)\\s+-\\s+([\\s\\S]*)$");
        String str = "272020 [http-80-3] DEBUG com.ea.eadp.cp.infra.rest.client.RESTClient - Got reponse body: <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<pidProfileEntry>\n" +
                "    <pidUri>/pids/1000000075039/</pidUri>\n" +
                "    <entryId>1000000075039</entryId>\n" +
                "    <entryCategory>SECURITY_QA</entryCategory>\n" +
                "    <entryElement>\n" +
                "        <profileType>SEC_QUESTION</profileType>\n" +
                "        <value>What is the name of your favorite pet?</value>\n" +
                "    </entryElement>\n" +
                "    <entryElement>\n" +
                "        <profileType>SEC_ANSWER</profileType>\n" +
                "        <value>1234</value>\n" +
                "    </entryElement>\n" +
                "</pidProfileEntry>";
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.find());
       System.out.println(matcher.group(5));
    }
}
