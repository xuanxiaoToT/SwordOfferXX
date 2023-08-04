package com.xx.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author XuanXiao
 * @CreateDate 2023/8/4
 */
public class CodeStatics {

    public static int sumFile = 0;

    /**
     * 统计类的个数(相当于题目个数)
     *
     * @param inFile 输入的文件，包含子文件和子文件夹
     * @param bw     缓冲输出流
     * @throws IOException
     */
    public void codeStatics(File inFile, HashSet<String> notAllowedDir)
            throws IOException {
        for (File file : Objects.requireNonNull(inFile.listFiles())) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                sumFile++;

            } else if (file.isDirectory()) {
                if (!notAllowedDir.contains(file.getName())) {
                    codeStatics(file, notAllowedDir);
                }
            }
        }
    }

    private HashSet<String> generateNotDir() {
        HashSet<String> notAllowedDir = new HashSet<>();
        notAllowedDir.add("domain");
        notAllowedDir.add("util");
        return notAllowedDir;
    }


    public static void main(String[] args) {
        try {
            CodeStatics codeStatics = new CodeStatics();
            HashSet<String> notAllowedDir = codeStatics.generateNotDir();
            File inFile = new File("G:\\MyCode\\SwordOfferXX\\SwordOfferXX");
            codeStatics.codeStatics(inFile, notAllowedDir);
            System.out.println("统计:" + sumFile + "个类\t");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
