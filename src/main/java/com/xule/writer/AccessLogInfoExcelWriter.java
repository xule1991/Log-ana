package com.xule.writer;

import com.xule.model.AccessLogInfo;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class AccessLogInfoExcelWriter {

    public void writeDownAccessLogInfo(Set<AccessLogInfo> accessLogInfos) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("new sheet");
        HSSFRow row = sheet.createRow((short)0);
        row.createCell((short)0).setCellValue("TIME");
        row.createCell((short)1).setCellValue("IP");
        row.createCell((short)2).setCellValue("REQUEST_FIEST_LINE");
        row.createCell((short)3).setCellValue("HTTP_METHOD");
        row.createCell((short)4).setCellValue("HTTP_STATUS");
        row.createCell((short)5).setCellValue("REQUEST_TIME");

        int num = 1;
        Iterator<AccessLogInfo> iterator = accessLogInfos.iterator();
        while (iterator.hasNext()) {
            AccessLogInfo accessLogInfo = iterator.next();
            row = sheet.createRow((short)num++);
            row.createCell((short)0).setCellValue(accessLogInfo.getTime());
            row.createCell((short)1).setCellValue(accessLogInfo.getIp());
            row.createCell((short)2).setCellValue(accessLogInfo.getFirstLineOfRequest());
            row.createCell((short)3).setCellValue(accessLogInfo.getHttpMethod());
            row.createCell((short)4).setCellValue(accessLogInfo.getHttpStatus());
            row.createCell((short)5).setCellValue(accessLogInfo.getRequestTime());
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("Log-ana-master\\outputfiles\\AccessLogInfo.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
