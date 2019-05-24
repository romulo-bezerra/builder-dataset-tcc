package br.edu.ifpb.builderdataset.loader;

import br.edu.ifpb.builderdataset.controller.ContentExtractorController;
import br.edu.ifpb.builderdataset.controller.FileController;
import br.edu.ifpb.builderdataset.controller.GitController;
import br.edu.ifpb.builderdataset.service.ContentExtractorServiceImpl;
import br.edu.ifpb.builderdataset.service.FileServiceImpl;
import br.edu.ifpb.builderdataset.service.GitServiceImpl;

import java.util.List;

public class Loader {

    public static void main(String[] args) {

        GitServiceImpl gitService = new GitServiceImpl("https://github.com/alvaromagnum/jsNota3.git");
        GitController gitController = new GitController(gitService);
        FileServiceImpl fileService = new FileServiceImpl();
        FileController fileController = new FileController(fileService);
        ContentExtractorServiceImpl contentExtractorService = new ContentExtractorServiceImpl(gitController, fileController);
        ContentExtractorController contentExtractor = new ContentExtractorController(contentExtractorService);

        List<String> contents = contentExtractor.extractRows();

        for (String s : contents) {
            System.out.println(s + "\n");
        }

    }

}
