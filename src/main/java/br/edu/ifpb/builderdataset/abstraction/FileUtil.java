package br.edu.ifpb.builderdataset.abstraction;

import java.io.File;
import java.util.List;

public interface FileUtil {

    File[] getFiles(File dir);
    String readContentFile(File file);
    List<String> readContentFileAsList(File file);
    void deleteDir(File dir);

}
