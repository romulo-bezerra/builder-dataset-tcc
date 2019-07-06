package br.edu.ifpb.builderdataset.loader;

import br.edu.ifpb.builderdataset.controller.ContentExtractorController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static void main(String[] args) {

        ContentExtractorController contentExtractor = new ContentExtractorController();

        //POO
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/Exemplo-Colecoes.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/Generics.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/ExemploInterface.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/Exemplo-Interface.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/Exemplo-CRUD.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/Exemplo-Heran-a.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/ExemploDependencia.git";
//        String linkRepoHttp = "https://github.com/ifpb-poo-2019-1/ClasseEObjeto.git";
//        String linkRepoHttp = "https://github.com/Matheus-Santos-Araujo/CursoEmVideoJavaPOO.git";
//        String linkRepoHttp = "https://github.com/cjamorim/JAVA-BASICO.git";
//        String linkRepoHttp = "https://github.com/rafaelign/ipoo-sin-2014-2.git";
//        String linkRepoHttp = "https://github.com/rafaeljordaojardim/POO---JAVA.git";
//        String linkRepoHttp = "https://github.com/MarcusBeladona/Java-POO.git";
//        String linkRepoHttp = "https://github.com/italonolasco/POO.git";
//        String linkRepoHttp = "https://github.com/adrianoasg/FATEC---POO.git";
//        String linkRepoHttp = "https://github.com/douglasddf/poo.git";
//        String linkRepoHttp = "https://github.com/Diegobodyn/AplicacaoDesktopMVC.git";
//        String linkRepoHttp = "https://github.com/rodolfoADS/AulaHeranca.git";
//        String linkRepoHttp = "https://github.com/tiagocmendes/poo.git";
//        String linkRepoHttp = "https://github.com/Andriuslima/Programacao-Orientada-a-Objetos---PUCRS.git";
//        String linkRepoHttp = "https://github.com/josemalcher/udemy-Java_COMPLETO_2018_Programacao_Orientada_a_Objetos_Projetos.git";
//        String linkRepoHttp = "https://github.com/uchihaisaac/Aplica-es-Java-Orientada-a-Objetos.git";
//        String linkRepoHttp = "https://github.com/MacedoTor/Java-POO.git";
//        String linkRepoHttp = "https://github.com/rafaellx10/POO-viotti.git";
//        String linkRepoHttp = "https://github.com/ronansenati/course_nelioalves.git";


        //Linguagem de Marcação
//        String linkRepoHttp = "https://github.com/ifpb/lm";
//        String linkRepoHttp = "https://github.com/andycaruso/lme201701.git";
//        String linkRepoHttp = "https://github.com/elileal/lm.git";
//        String linkRepoHttp = "https://github.com/elileal/Estudando_Musica.git";
//        String linkRepoHttp = "https://github.com/lucas-salles/D20-Podcast.git";
//        String linkRepoHttp = "https://github.com/andre-ez/projetocervejaria.git";
//        String linkRepoHttp = "https://github.com/brunof85/imperio-veiculos.git";
//        String linkRepoHttp = "https://github.com/Tales313/So_Jesus_Salva.git";
//        String linkRepoHttp = "https://github.com/kingbomer/LMW-turma-51506.git";
//        String linkRepoHttp = "https://github.com/kgcoelho/Trabalho-2-HTML-E-CSS.git";
//        String linkRepoHttp = "https://github.com/acsapassos/site_cibele_carneiro1.git";
//        String linkRepoHttp = "https://github.com/MateusPinheiro/LMS.git";
//        String linkRepoHttp = "https://github.com/samyralara/LMeLS.git";
//        String linkRepoHttp = "https://github.com/Eudalio/atividades-lms.git";
//        String linkRepoHttp = "https://github.com/erickluan/atividades-lms.git";
//        String linkRepoHttp = "https://github.com/caiocosta08/chiara-luce.git";
//        String linkRepoHttp = "https://github.com/natansevero/responsive-site-prefeitura.git";
//        String linkRepoHttp = "https://github.com/augustoliks/HTML.git";
//        String linkRepoHttp = "https://github.com/beyond-z/canvas-lms-js-css.git";
        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";
//        String linkRepoHttp = "";

        List<String> contents = contentExtractor.extractRows(linkRepoHttp);

        File file = new File("src/main/resources/output.txt");

        for (String s : contents) {

            if (isBiggerThanLimitMax(s)) {
                List<String> slicesText = cutSlices(s);
                for (String sliceText : slicesText) {
                    contentExtractor.writeContentFile(file, sliceText);
                }
            } else {
                contentExtractor.writeContentFile(file, s);
            }
            System.out.println(s);
        }
    }

    private static List<String> cutSlices(String text) {
        List<String> slicesText = new ArrayList<>();
        int qntMediaCaracter = 25000;
        int lengthText = text.length();
        int slices = lengthText/qntMediaCaracter;

        int init, finish;
        for (int i = 0; i <= slices; i++) {
            init = qntMediaCaracter * i;

            if (i == slices) finish = lengthText;
            else finish = qntMediaCaracter * (i+1);

            String sliceText = text.substring(init, finish);
            slicesText.add(sliceText);
        }
        return slicesText;
    }

    private static Boolean isBiggerThanLimitMax(String text) {
        final int limitMaxCaracters = 50000;
        return text.length() > limitMaxCaracters;
    }

}
