package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.FileService;
import br.edu.ifpb.builderdataset.service.FileServiceImpl;

import java.io.File;
import java.util.List;

public class FileController {

    private FileService fileService;

    public FileController() {
        this.fileService = new FileServiceImpl();
    }

    public File[] getFiles(File dir) {
        return this.fileService.getFiles(dir);
    }

    public String readContentFile(File file) {
        return this.fileService.readContentFile(file);
    }

    public List<String> readContentFileAsList(File file) {
        return this.fileService.readContentFileAsList(file);
    }

    public void deleteDir(File dir) {
        this.fileService.deleteDir(dir);
    }

}
