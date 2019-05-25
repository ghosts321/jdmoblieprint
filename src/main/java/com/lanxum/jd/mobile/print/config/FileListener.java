package com.lanxum.jd.mobile.print.config;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * @author jiang
 */
public class FileListener extends FileAlterationListenerAdaptor {

	private Logger log = Logger.getLogger(String.valueOf(FileListener.class));
    /**
     * 文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());

    }

    /**
     * 文件创建修改
     */
    @Override
    public void onFileChange(File file) {
        log.info("[修改]:" + file.getAbsolutePath());
    }

    /**
     * 文件删除
     */
    @Override
    public void onFileDelete(File file) {
        log.info("[删除]:" + file.getAbsolutePath());
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("[新建]:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

//    public static void main(String[] args) throws Exception{
//        // 监控目录
//        String rootDir = "D:\\nginx\\html";
//        // 轮询间隔 5 秒
//        long interval = TimeUnit.SECONDS.toMillis(1);
//        // 创建过滤器
//        IOFileFilter directories = FileFilterUtils.and(
//                FileFilterUtils.directoryFileFilter(),
//                HiddenFileFilter.VISIBLE);
//        IOFileFilter files = FileFilterUtils.and(
//                FileFilterUtils.fileFileFilter(),
//                FileFilterUtils.suffixFileFilter(".xml"));
//        IOFileFilter filter = FileFilterUtils.or(directories, files);
//        // 使用过滤器
//        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
//        //不使用过滤器
//        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
//        FileListener fileListener = new FileListener();
//        observer.addListener(fileListener);
//        //创建文件变化监听器
//        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
//        // 开始监控
//        monitor.start();
//    }

}
