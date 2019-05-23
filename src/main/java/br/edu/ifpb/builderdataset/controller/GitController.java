package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.GitService;
import br.edu.ifpb.builderdataset.service.GitServiceImpl;

import java.io.File;

public class GitController {

    private GitService gitUtil;

    public GitController(GitServiceImpl gitUtil) {
        this.gitUtil = gitUtil;
    }

    public File doClone() {
        return this.gitUtil.doClone();
    }

}
