package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContentExtractorServiceImpl implements ContentExtractorService {

    private GitServiceImpl gitUtil;
    private final FileServiceImpl fileUtil;

    public ContentExtractorServiceImpl() {
        this.fileUtil = new FileServiceImpl();
    }

    @Override
    public List<String> extractRows(String linkHttpRepo) {

        this.gitUtil = new GitServiceImpl(linkHttpRepo);

        File clonedRepo = gitUtil.doClone();

        List<String> aggregateLines = new ArrayList<>();
        for (File scannedFile : fileUtil.getFiles(clonedRepo)){
            for (String scannedLine : fileUtil.readContentFileAsList(scannedFile)){
                aggregateLines.add(scannedLine);
            }
        }

        //Deletando o arquivo clonado após o escaneamento
        fileUtil.deleteDir(clonedRepo);

        return aggregateLines;
    }

}
