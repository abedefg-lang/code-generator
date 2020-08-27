package com.utils.file;

import com.utils.reflect.ReflectUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    /**classpath的前缀*/
    public static final String CLASS_PATH_PREFIX = "classpath:";

    /**
     * 通过路径创建一个文件
     * @param pathname 路径名称
     * @return 返回一个文件
     */
    public static File createFile(String pathname){
        Objects.requireNonNull(pathname, "pathname不能为null");
        //进行判断是否是classpath
        if(pathname.startsWith(CLASS_PATH_PREFIX)){
            //截取出后面的内容
            String classpath = pathname.substring(CLASS_PATH_PREFIX.length());
            //获取url
            URL url = ReflectUtils.getDefaultClassLoader().getResource(classpath);
            Objects.requireNonNull(url, "找不到文件: " + classpath);
            pathname = url.getPath();
        }
        return new File(pathname);
    }

    /**
     * 写入文件
     * @param file file
     * @param str 内容
     */
    public static void write(File file, String str, boolean append){
        Objects.requireNonNull(file, "需要写入的文件不能为null");
        try(FileWriter writer = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(writer)){
            bw.write(str);
            bw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 拼接文件
     * @param file file
     * @param str 内容
     */
    public static void append(File file, String str){
        write(file, str, true);
    }

    /**
     * 扫描指定路径下所有文件
     * @param dir 指定路径
     * @return 返回list
     */
    public static List<File> scanAllFile(File dir){
        return scanFiles(dir, file -> true);
    }

    /**
     * 扫描指定路径下满足条件的文件  进行返回
     * @param dir dir
     * @param fileFilter 定义文件规则
     * @return 返回list
     */
    public static List<File> scanFiles(File dir, FileFilter fileFilter){
        Objects.requireNonNull(dir, "需要扫描的文件夹不能为null");
        Objects.requireNonNull(fileFilter, FileFilter.class.getName() + "不能为null");
        List<File> files = new ArrayList<>();
        //执行扫描逻辑
        doScanFiles(dir, fileFilter, files);
        return files;
    }

    /**
     * 递归扫描文件
     */
    private static void doScanFiles(File dir, FileFilter fileFilter, List<File> fileList){
        //获取dir子文件
        File[] files = dir.listFiles();
        if(files != null){
            //说明是一个文件夹
            for(File f : files){
                doScanFiles(f, fileFilter, fileList);
            }
        }else if(fileFilter.accept(dir)){
            //走到这里说明 这个是文件  并且是符合条件的文件
            fileList.add(dir);
        }
    }
}
