package br.edu.ifpb.builderdataset.abstraction;

import java.io.File;
import java.util.List;

public interface ContentExtractorService {

    List<String> extractRows(String linkHttpRepo);
    void writeContentFile(File file, String text);

}
