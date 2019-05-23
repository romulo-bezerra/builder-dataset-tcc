package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;
import br.edu.ifpb.builderdataset.controller.FileController;
import br.edu.ifpb.builderdataset.controller.GitController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContentExtractorServiceImpl implements ContentExtractorService {

    private GitController gitController;
    private final FileController fileController;

    public ContentExtractorServiceImpl(GitController gitController, FileController fileController) {
        this.gitController = gitController;
        this.fileController = fileController;
    }

    @Override
    public List<String> extractRows() {

        //this.gitUtil = new GitServiceImpl(linkHttpRepo);

        File clonedRepo = gitController.doClone();

        List<String> aggregateLines = new ArrayList<>();
        for (File scannedFile : fileController.getFiles(clonedRepo)){
            for (String scannedLine : fileController.readContentFileAsList(scannedFile)){
                aggregateLines.add(scannedLine);
            }
        }

        //Deletando o arquivo clonado após o escaneamento
        fileController.deleteDir(clonedRepo);

        return aggregateLines;
    }

}
