package br.edu.ifpb.builderdataset.controller;

import br.edu.ifpb.builderdataset.abstraction.GitService;
import br.edu.ifpb.builderdataset.service.GitServiceImpl;

import java.io.File;

public class GitController {

    private GitService gitService;

    public GitController() {
        this.gitService = new GitServiceImpl();
    }

    public File doClone(String linkHttpRepo) {
        return this.gitService.doClone(linkHttpRepo);
    }

}
