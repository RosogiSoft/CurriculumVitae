package com.example.curriculumvitae.ResumeGenerator;

import com.example.curriculumvitae.MainController;
import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.scene.chart.PieChart;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Generator {

    private final String path;

    public Generator(String path) {
        this.path = path;
    }

    public List<Object> addElementsFirstCol(Inline image) {
        List<Object> array = new ArrayList<>();
        array.add(addImageToParagraph(image));
        array.add(addTextToParagraph("Образование", 12 * 2, new BooleanDefaultTrue()));
        array.add(addTextToParagraph("Дополнительное образование", 12 * 2, new BooleanDefaultTrue()));
        array.add(addTextToParagraph("Профессиональные навыки", 12 * 2, new BooleanDefaultTrue()));
        array.add(addTextToParagraph("Личные качества", 12 * 2, new BooleanDefaultTrue()));
        array.add(addTextToParagraph("Дополнительная информация", 12 * 2, new BooleanDefaultTrue()));
        return array;
    }

    public void initFile(int id) throws Exception {
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.getContent().add(
                addImageToParagraph(
                    simpleAddImageToP(
                        wordPackage,Files.readAllBytes(Path.of("src/main/resources/com/example/curriculumvitae/pic/img.png")),
                "Logo", "Logo", 1, 2, false, 1200)

        ));
        mainDocumentPart.getContent().add(setTable(wordPackage, simpleAddImageToP(
                wordPackage, DataBaseConnect.getUserPicFromDataBase(id), "UserPhoto",
                "UserPhoto", 3,4, false, 2360
        )));
        File docxFile = new File(path);
        wordPackage.save(docxFile);
    }

    private Inline simpleAddImageToP(WordprocessingMLPackage wordPackage,
                                     byte[] fileContent,
                                     String fileNameHint,
                                     String altText,
                                     int id1,
                                     int id2,
                                     boolean link,
                                     int maxWidth) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
        return imagePart.createImageInline(fileNameHint, altText, id1, id2, link, maxWidth);
    }

    private Inline simpleAddImageToP(WordprocessingMLPackage wordPackage,
                                     byte[] fileContent,
                                     String fileNameHint,
                                     String altText,
                                     int id1,
                                     int id2,
                                     boolean link) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
        return imagePart.createImageInline(fileNameHint, altText, id1, id2, link);
    }

    private P addImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }

    private P addTextToParagraph(String string, int size, BooleanDefaultTrue style) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        text.setValue(string);
        RPr rPr = factory.createRPr();
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(BigInteger.valueOf(size));
        U u = new U();
        u.setVal(UnderlineEnumeration.SINGLE);
        rPr.setU(u);
        rPr.setSz(hpsMeasure);
        rPr.setB(style);
        r.setRPr(rPr);
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    private P addTextToParagraph(String string, int size) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        text.setValue(string);
        RPr rPr = new RPr();
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(BigInteger.valueOf(size));
        rPr.setSz(hpsMeasure);
        r.setRPr(rPr);
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    private P addTextToParagraph(String string) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        text.setValue(string);
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    private void setCellWidth(Tc tableCell, int width) {
        TcPr tableCellProperties = new TcPr();
        TblWidth tableWidth = new TblWidth();
        tableWidth.setW(BigInteger.valueOf(width));
        tableCellProperties.setTcW(tableWidth);
        tableCell.setTcPr(tableCellProperties);
    }

    private Tbl setTable(WordprocessingMLPackage wordpackage, Inline image) {
        int writableWidth = wordpackage
                .getDocumentModel()
                .getSections()
                .get(0)
                .getPageDimensions()
                .getWritableWidthTwips();
        List<Object> tableItems = addElementsFirstCol(image);
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
                    setCellWidth(cell, 100);
                    cell.getContent().add(tableItems.get(j));
                    j++;
                }
            }
        }
        j = 0;
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (int i = 0; i < cells.size(); i++) {
                if (i % 2 != 0) {
                    Tc cell = (Tc) cells.get(i);
                    cell.getContent().add(addTextToParagraph("Cock"));
                    j++;
                }
            }
        }
        return table;
    }


    public static void main(String[] args) throws Exception {
        String filename = "example.docx";
        Generator generator = new Generator("/Users/igorvasilcev/IdeaProjects" +
                "/CurriculumVitae/src/main/resources/com/example/curriculumvitae/wordExamples/" + filename);
        generator.initFile(1);
    }
}
