package com.example.curriculumvitae.ResumeGenerator;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.File;
import java.nio.file.Path;

public class Generator {

    private String path = null;

    public Generator(String path){
        this.path = path;
    }

    public void initFile() throws Exception {
        WordprocessingMLPackage wordpackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordpackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Резюме");
        mainDocumentPart.addParagraphOfText("Будет инфа");
        File docxFile = new File(path);
        wordpackage.save(docxFile);
    }
/*
    public static void main(String[] args) throws Exception {
        String filename = "example.docx";
        initFile(
                "/Users/igorvasilcev/IdeaProjects" +
                        "/CurriculumVitae/src/main/resources/com/example/curriculumvitae/wordExamples/" + filename

        );
    }*/
}
