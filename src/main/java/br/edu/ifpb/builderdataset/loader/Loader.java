package br.edu.ifpb.builderdataset.loader;

import br.edu.ifpb.builderdataset.controller.ContentExtractorController;

import java.util.List;

public class Loader {

    public static void main(String[] args) {

        ContentExtractorController contentExtractor = new ContentExtractorController();

        String linkRepoHttp = "https://github.com/romulo-soares/atv-av-listatelefonica.git";
        List<String> contents = contentExtractor.extractRows(linkRepoHttp);

        for (String s : contents) {
            System.out.println(s);
        }
    }

}
