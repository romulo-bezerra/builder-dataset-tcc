package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;
import br.edu.ifpb.builderdataset.service.ContentExtractorServiceImpl;

import java.io.File;
import java.util.List;

public class ContentExtractorController {

    private ContentExtractorService contentExtractorService;

    public ContentExtractorController() {
        this.contentExtractorService = new ContentExtractorServiceImpl();
    }

    public List<String> extractRows(String linkHttpRepo) {
        return this.contentExtractorService.extractRows(linkHttpRepo);
    }

    public void writeContentFile(File file, String text) {
        contentExtractorService.writeContentFile(file, text);
    }

}
