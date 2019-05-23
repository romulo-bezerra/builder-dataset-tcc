package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.ContentExtractorService;
import br.edu.ifpb.builderdataset.util.FileUtilImpl;
import br.edu.ifpb.builderdataset.util.GitUtilImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContentExtractorServiceImpl implements ContentExtractorService {

    private GitUtilImpl gitUtil;
    private final FileUtilImpl fileUtil;

    public ContentExtractorServiceImpl() {
        this.fileUtil = new FileUtilImpl();
    }

    @Override
    public List<String> extractRows(String linkHttpRepo) {

        this.gitUtil = new GitUtilImpl(linkHttpRepo);

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
