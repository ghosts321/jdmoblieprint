package com.lanxum.jd.mobile.print.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 定时任务删除指定文件夹下指定后缀名的文件
 * @author JiangJunpeng
 * @date 2019/4/12
 */
@Component
@Configuration
@EnableScheduling
public class QuartzDeleteFile {
    @Scheduled(cron = "0 0 2 * * ?")
    private void deleteFiles(){
        File file = new File("D:\\nginx\\html");
        File[] files=file.listFiles();
        for (File delFile : files) {
            String fix = delFile.getName().substring(delFile.getName().lastIndexOf(".")+1);
            if("pdf".equals(fix) || "docx".equals(fix) || "doc".equals(fix)
                    || "ppt".equals(fix) || "pptx".equals(fix) || "xls".equals(fix)
                    || "xlsx".equals(fix) || "txt".equals(fix) || "jpg".equals(fix)
                    || "jpeg".equals(fix) || "png".equals(fix) || "gif".equals(fix)
                    || "xml".equals(fix) || "pcl".equals(fix)){
                delFile.delete();
            }
        }
    }
}
