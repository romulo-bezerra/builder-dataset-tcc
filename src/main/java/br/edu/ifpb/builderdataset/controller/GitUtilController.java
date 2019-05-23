package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.GitUtil;
import br.edu.ifpb.builderdataset.util.GitUtilImpl;

import java.io.File;

public class GitUtilController {

    private GitUtil gitUtil;

    public GitUtilController(GitUtilImpl gitUtil) {
        this.gitUtil = gitUtil;
    }

    public File doClone() {
        return this.gitUtil.doClone();
    }

}
