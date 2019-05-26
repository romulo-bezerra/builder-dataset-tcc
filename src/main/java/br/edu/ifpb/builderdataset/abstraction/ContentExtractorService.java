package br.edu.ifpb.builderdataset.abstraction;

import java.util.List;

public interface ContentExtractorService {

    List<String> extractRows(String linkHttpRepo);

}
