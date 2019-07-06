package br.edu.ifpb.builderdataset.abstraction;

import java.io.File;

public interface GitService {
    File doClone(String linkHttpRepo);
}
