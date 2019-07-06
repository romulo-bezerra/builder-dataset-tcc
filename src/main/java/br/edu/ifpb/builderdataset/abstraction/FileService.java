package br.edu.ifpb.builderdataset.abstraction;

import java.io.File;
import java.util.Set;

public interface FileService {

    File[] getFiles(File dir);
    String readContentFile(File file);
    Set<String> readContentFileAsList(File file);
    void deleteDir(File dir);
    void writeContentFile(File file, String text);

}
