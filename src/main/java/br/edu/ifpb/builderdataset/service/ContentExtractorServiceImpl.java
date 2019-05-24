package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;
import br.edu.ifpb.builderdataset.controller.FileController;
import br.edu.ifpb.builderdataset.controller.GitController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentExtractorServiceImpl implements ContentExtractorService {

    private GitController gitController;
    private final FileController fileController;
    private final java.util.logging.Logger log = Logger.getLogger(ContentExtractorServiceImpl.class.getName());

    public ContentExtractorServiceImpl(GitController gitController, FileController fileController) {
        this.gitController = gitController;
        this.fileController = fileController;
    }

    @Override
    public List<String> extractRows() {

        log.log(Level.INFO, "Clonando repositório...");

        File clonedRepo = gitController.doClone();

        log.log(Level.INFO, "Repositório clonado!");

        log.log(Level.INFO, "Escaneando arquivos e lendo linhas...");

        List<String> aggregateLines = new ArrayList<>();
        for (File scannedFile : fileController.getFiles(clonedRepo)){
            for (String scannedLine : fileController.readContentFileAsList(scannedFile)){
                aggregateLines.add(scannedLine);
            }
        }

        log.log(Level.INFO, "Escaneamento finalizado!");

        log.log(Level.INFO, "Deletando repositório...");

        fileController.deleteDir(clonedRepo); //Deletando o arquivo clonado após o escaneamento

        log.log(Level.INFO, "Repositório deletado!");

        return aggregateLines;
    }

}
