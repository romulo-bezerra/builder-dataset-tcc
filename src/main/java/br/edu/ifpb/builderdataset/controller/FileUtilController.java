package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.FileUtil;
import br.edu.ifpb.builderdataset.util.FileUtilImpl;

import java.io.File;
import java.util.List;

public class FileUtilController {

    private FileUtil fileUtil;

    public FileUtilController(FileUtilImpl fileUtil) {
        this.fileUtil = fileUtil;
    }

    public File[] getFiles(File dir) {
        return this.fileUtil.getFiles(dir);
    }

    public String readContentFile(File file) {
        return this.fileUtil.readContentFile(file);
    }

    public List<String> readContentFileAsList(File file) {
        return this.fileUtil.readContentFileAsList(file);
    }

    public void deleteDir(File dir) {
        this.fileUtil.deleteDir(dir);
    }

}
