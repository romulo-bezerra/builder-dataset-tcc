package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;
import br.edu.ifpb.builderdataset.service.ContentExtractorServiceImpl;

import java.util.List;

public class ContentExtractorController {

    private ContentExtractorService contentExtractorService;

    public ContentExtractorController(ContentExtractorServiceImpl contentExtractorService) {
        this.contentExtractorService = contentExtractorService;
    }

    public List<String> extractRows() {
        return this.contentExtractorService.extractRows();
    }

}
