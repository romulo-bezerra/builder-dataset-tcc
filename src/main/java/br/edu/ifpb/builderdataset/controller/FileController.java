package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.FileService;
import br.edu.ifpb.builderdataset.service.FileServiceImpl;

import java.io.File;
import java.util.List;

public class FileController {

    private FileService fileUtil;

    public FileController(FileServiceImpl fileUtil) {
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
