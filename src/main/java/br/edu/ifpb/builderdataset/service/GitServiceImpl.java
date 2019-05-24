package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.GitService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.util.logging.Logger;

public class GitServiceImpl implements GitService {

    private final Logger log = Logger.getLogger(GitServiceImpl.class.getName());
    private final String user = "romulo-soares";
    private final String password = "catalega135";
    private String linkHttpRepo;

    public GitServiceImpl(String linkHttpRepo){
        this.linkHttpRepo = linkHttpRepo;
    }

    public GitServiceImpl() {}

    public void setLinkHttpRepo(String linkHttpRepo) {
        this.linkHttpRepo = linkHttpRepo;
    }

    /**
     * Realiza clone (do comando: git clone <link_repo>) de um repositório do Github
     *
     * @return File file, referência para a raiz do diretório clonado
     */
    @Override
    public File doClone() {

        CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(user, password);

        File file = new File("src/main/resources/temp-repositories");

        Git git;
        try {
            git = Git.cloneRepository()
                    .setURI(linkHttpRepo).setDirectory(file)
                    .setCredentialsProvider(credentialsProvider)
                    .call();
            return file;
        } catch (GitAPIException e) {
            log.warning("Erro ao clonar o repositório. Cheque a consistência do link do repositório!\n" + e.getMessage());
            return null;
        }
    }

}
