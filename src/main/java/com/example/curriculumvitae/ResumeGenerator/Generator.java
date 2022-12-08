package com.example.curriculumvitae.ResumeGenerator;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import com.example.curriculumvitae.helper.GeneratorFilling;
import com.example.curriculumvitae.helper.ParagraphPreprocess;
import com.example.curriculumvitae.helper.TablePreprocess;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private final String path;
    private final GeneratorFilling generatorFilling = new GeneratorFilling();

    public Generator(String path) {
        this.path = path;
    }

    private List<Object> addElementsFirstCol(Inline image) {
        List<Object> array = new ArrayList<>();
        array.add(ParagraphPreprocess.addImageToParagraph(image, JcEnumeration.CENTER));
        array.add(ParagraphPreprocess.addTextToParagraph("Образование", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Дополнительное образование", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Профессиональные навыки", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Личные качества", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Дополнительная информация", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        return array;
    }

    private List<String> setSimpleData() {
        ArrayList<String> array = new ArrayList<>();
        array.add("c");
        array.add("f");
        return array;
    }

    public void initFile(int id) throws Exception {
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.getContent().add(
                ParagraphPreprocess.addImageToParagraph(
                        ParagraphPreprocess.simpleAddImageToP(
                                wordPackage, Files.readAllBytes(
                                        Path.of("src/main/resources/com/example/curriculumvitae/pic/shapka.png")),
                                "Logo", "Logo", 1, 2, false)
                ));
        mainDocumentPart.getContent().add(setTable(wordPackage, ParagraphPreprocess.simpleAddImageToP(
                wordPackage, DataBaseConnect.getUserPicFromDataBase(id), "UserPhoto",
                "UserPhoto", 3, 4, false, 2360
        )));
        File docxFile = new File(path);
        wordPackage.save(docxFile);
    }


    private Tbl setTable(WordprocessingMLPackage wordPackage, Inline image) {
        int writableWidth = wordPackage
                .getDocumentModel()
                .getSections()
                .get(0)
                .getPageDimensions()
                .getWritableWidthTwips();
        List<Object> tableItems = addElementsFirstCol(image);
        List<Object> descriptionCells = new ArrayList<>();
        int columnNumber = 2;
        Tbl table = TblFactory.createTable(6, columnNumber, writableWidth / columnNumber);
        int j = 0;
        List<Object> rows = table.getContent();
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (int i = 0; i < cells.size(); i++) {
                if (i % 2 == 0) {
                    Tc cell = (Tc) cells.get(i);
                    TablePreprocess.setCellStyleFirstCol(cell, 3105);
                    cell.getContent().add(tableItems.get(j));
                    j++;
                }
            }
        }
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (int i = 0; i < cells.size(); i++) {
                if (i % 2 != 0) {
                    Tc cell = (Tc) cells.get(i);
                    TablePreprocess.setCellStyleSecondColl(cell, 7145);
                    descriptionCells.add(cell);
                }
            }
        }
        for (int i = 0; i < descriptionCells.size(); i++) {
            Tc cell = null;
            switch (i) {
                case 0:
                    cell = (Tc) descriptionCells.get(i);
                    for (int c = 0; c < generatorFilling.mainInfo().size(); c++) {
                        cell.getContent().add(generatorFilling.mainInfo().get(c));
                    }
                    break;
                case 1:
                    cell = (Tc) descriptionCells.get(i);
                    cell.getContent().add(ParagraphPreprocess.setList(setSimpleData()));
                    break;
                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;
            }
        }
        return table;
    }
}