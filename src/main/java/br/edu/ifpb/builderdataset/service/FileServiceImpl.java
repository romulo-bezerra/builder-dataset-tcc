package br.edu.ifpb.builderdataset.service;

import br.edu.ifpb.builderdataset.abstraction.FileService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

public class FileServiceImpl implements FileService {

    private final java.util.logging.Logger log = Logger.getLogger(FileServiceImpl.class.getName());

    /**
     * Retorna uma array de arquivos de texto contidos no diretório
     *
     * @param dir representa o diretório raiz do repositório
     * @return File[] filesEncontrados
     */
    @Override
    public File[] getFiles(File dir) {
        Vector enc = new Vector();
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory() && !f.isHidden()) {
                //Adiciona no Vector os arquivos encontrados dentro de 'files[i]':
                File[] recFiles = getFiles(f);
                for (File rf : recFiles) {
                    enc.addElement(rf);
                }
            } else {
                //Adiciona no Vector o arquivo encontrado dentro de 'dir':
                if(!f.isHidden()){
                    enc.addElement(f);
                }
            }
        }
        //Transforma um Vector em um File[]:
        File[] filesEncontrados = new File[enc.size()];
        for (int i = 0; i < enc.size(); i++) {
            filesEncontrados[i] = (File)enc.elementAt(i);
        }
        return filesEncontrados;
    }

    @Override
    public String readContentFile(File file) {
        Path path = Paths.get(file.toURI());
        try {
//            String longText = formmaterString(Files.readAllLines(path).toString());
            String longText = Files.readAllLines(path).toString();
            return longText;
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        return null;
    }

    @Override
    public List<String> readContentFileAsList(File file){

        List<String> retorno = new ArrayList<>();
        String nameFile = file.getName();

        if(!nameFile.endsWith("jar") && !nameFile.endsWith("png")
                && !nameFile.endsWith("jpeg") && !nameFile.endsWith("gz")
                && !nameFile.endsWith("gif") && !nameFile.endsWith("tiff")
                && !nameFile.endsWith("bmp") && !nameFile.endsWith("psd")
                && !nameFile.endsWith("exif") && !nameFile.endsWith("zip")
                && !nameFile.endsWith("rar") && !nameFile.endsWith("tar")
                && !nameFile.endsWith("z") && !nameFile.endsWith("taz")
                && !nameFile.endsWith("tgz") && !nameFile.endsWith("arj")
                && !nameFile.endsWith("woff2") && !nameFile.endsWith("woff")) {

            BufferedReader conteudoFile = null;
            String linha = "";

            try {
                conteudoFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                final int qntMinCaracter = 5; //definida aleatorialmente, mas precisamente
                while ((linha = conteudoFile.readLine()) != null && !"".equalsIgnoreCase(linha) && (linha.length() > qntMinCaracter)) {
//                    retorno.add(formmaterString(linha));
                    retorno.add(linha);
                }
                return retorno;

            } catch (FileNotFoundException e) {
                log.warning("Arquivo não encontrado: \n" + e.getMessage());
                return retorno;
            } catch (IOException e) {
                log.warning("IO erro: \n" + e.getMessage());
                return retorno;
            } finally {
                if (conteudoFile != null){
                    try {
                        conteudoFile.close();
                    } catch (IOException e) {
                        log.warning("IO erro: \n" + e.getMessage());
                    }
                }
            }
        }
        return retorno;
    }

//    private String formmaterString(String s){
//        return s.replace(",", "")
//                .replace("[", "")
//                .replace("]", "")
//                .replace("\"", "")
//                .replace("  ", " ")
//                .replace("   ", " ")
//                .replace("    ", " ")
//                .replace("//", "");
//    }

    /**
     * Exclui o Diretório dado com todos seus sub-diretórios e arquivos:
     *
     * @param dir representa o arquivo raiz do repositório
     */
    @Override
    public void deleteDir(File dir) {
        String[] arqs;
        File arq;
        int i;

        arqs = dir.list();
        for(i = 0; i < arqs.length; i++){
            arq = new File(dir.getPath(), arqs[i]);
            if(arq.isDirectory()) {
                deleteDir(arq);
            } else {
                arq.delete();
            }
        }
        dir.delete();
    }

    public void writeContentFile(File file, String text) {

        BufferedWriter writer = null;

        try {
            //escrevendo no arquivo
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            log.warning("Erro: Não foi possível escrever no arquivo.\n" + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.warning("Erro: Não foi possivel fechar conexão com o arquivo.\n" + e.getMessage());
                }
            }
        }
    }

}
