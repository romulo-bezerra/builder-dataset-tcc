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
        log.log(Level.INFO, "Escaneando arquivos...");

        List<String> aggregateLines = new ArrayList<>();
        File[] arquivosParaAnalise = fileController.getFiles(clonedRepo);

        for (File scannedFile : arquivosParaAnalise){
            if (isFileLM(scannedFile)) {
                System.out.println("- - - - - - - - - - - - - - | NOME DO ARQUIVO: " + scannedFile.getName() + " | - - - - - - - - - - - - - -");
                String contentFile = fileController.readContentFile(scannedFile);
                aggregateLines.add(contentFile);
            }
        }

        log.log(Level.INFO, "Arquivos escaneados: " + arquivosParaAnalise.length);

        log.log(Level.INFO, "Escaneamento finalizado!");
        log.log(Level.INFO, "Deletando repositório...");

        fileController.deleteDir(clonedRepo); //Deletando o arquivo clonado após o escaneamento

        log.log(Level.INFO, "Repositório deletado!");

        return aggregateLines;
    }

    @Override
    public void writeContentFile(File file, String text) {
        fileController.writeContentFile(file, text);
    }

    private Boolean isFileJava(File file) {
        String nameFile = file.getName();
        return nameFile.endsWith("java");
    }

    private Boolean isFileLM(File file) {
        String nameFile = file.getName();
        return nameFile.endsWith("html")
                || nameFile.endsWith("css");
    }

        private Boolean isFileRead(File file) {
        String nameFile = file.getName();

        return (!nameFile.endsWith("jar") && !nameFile.endsWith("png")
                && !nameFile.endsWith("jpeg") && !nameFile.endsWith("gz") && !nameFile.endsWith("gif")
                && !nameFile.endsWith("tiff") && !nameFile.endsWith("bmp") && !nameFile.endsWith("psd")
                && !nameFile.endsWith("exif") && !nameFile.endsWith("zip") && !nameFile.endsWith("rar")
                && !nameFile.endsWith("tar") && !nameFile.endsWith("z") && !nameFile.endsWith("taz")
                && !nameFile.endsWith("tgz") && !nameFile.endsWith("arj") && !nameFile.endsWith("woff2")
                && !nameFile.endsWith("woff") && !nameFile.endsWith("md") && !nameFile.endsWith("jpg")
                && !nameFile.endsWith("svg") && !nameFile.endsWith("otf") && !nameFile.endsWith("tff")
                && !nameFile.endsWith("eot") && !nameFile.endsWith("exe") && !nameFile.endsWith("pdf")
                && !nameFile.endsWith("doc") && !nameFile.endsWith("dll") && !nameFile.endsWith("docx")
                && !nameFile.endsWith("ppt") && !nameFile.endsWith("tif") && !nameFile.endsWith("pps")
                && !nameFile.endsWith("bat") && !nameFile.endsWith("xls") && !nameFile.endsWith("bin")
                && !nameFile.endsWith("xlsx") && !nameFile.endsWith("cab") && !nameFile.endsWith("com")
                && !nameFile.endsWith("ico") && !nameFile.endsWith("ttf") && !nameFile.endsWith("tmp")
                && !nameFile.endsWith("epgz") && !nameFile.endsWith("properties") && !nameFile.endsWith("mf")
                && !nameFile.endsWith("rs") && !nameFile.endsWith("class") && !nameFile.endsWith("mp4")
                && !nameFile.endsWith("mp3") && !nameFile.endsWith("iml") && !nameFile.endsWith("xml"));
    }

}
