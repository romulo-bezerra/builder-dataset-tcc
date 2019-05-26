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

    public ContentExtractorServiceImpl() {
        this.gitController = new GitController();
        this.fileController = new FileController();
    }

    @Override
    public List<String> extractRows(String linkHttpRepo) {

        log.log(Level.INFO, "Clonando repositório...");

        File clonedRepo = gitController.doClone(linkHttpRepo);

        log.log(Level.INFO, "Repositório clonado!");

        log.log(Level.INFO, "Escaneando arquivos e lendo linhas...");

        List<String> aggregateLines = new ArrayList<>();

        for (File scannedFile : fileController.getFiles(clonedRepo)){

            //Temporário, apenas para caso de debug
            aggregateLines.add("\n- - - - - - - - - - - - - - | NOME DO ARQUIVO: " + scannedFile.getName() + " | - - - - - - - - - - - - - -\n");

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
