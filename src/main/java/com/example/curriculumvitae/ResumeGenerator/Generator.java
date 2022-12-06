package com.example.curriculumvitae.ResumeGenerator;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import com.example.curriculumvitae.helper.ParagraphPreprocess;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.example.curriculumvitae.helper.ParagraphPreprocess.addTextToParagraph;

public class Generator {

    private final String path;

    public Generator(String path) {
        this.path = path;
    }

    private List<Object> addElementsFirstCol(Inline image) {
        List<Object> array = new ArrayList<>();
        array.add(ParagraphPreprocess.addImageToParagraph(image, JcEnumeration.CENTER));
        array.add(addTextToParagraph("Образование", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(addTextToParagraph("Дополнительное образование", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(addTextToParagraph("Профессиональные навыки", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(addTextToParagraph("Личные качества", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
        array.add(addTextToParagraph("Дополнительная информация", 12 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT));
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
                        simpleAddImageToP(
                                wordPackage, Files.readAllBytes(
                                        Path.of("src/main/resources/com/example/curriculumvitae/pic/shapka.png")),
                                "Logo", "Logo", 1, 2, false)

                ));
        mainDocumentPart.getContent().add(setTable(wordPackage, simpleAddImageToP(
                wordPackage, DataBaseConnect.getUserPicFromDataBase(id), "UserPhoto",
                "UserPhoto", 3, 4, false, 2360
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


    private void setCellStyleFirstCol(Tc tableCell, int width) {
        TcPr tableCellProperties = new TcPr();
        TcPrInner.TcBorders tcBorders = new TcPrInner.TcBorders();
        CTBorder borderTop = new CTBorder();
        CTBorder borderLeft = new CTBorder();
        CTBorder borderRight = new CTBorder();
        CTBorder borderBottom = new CTBorder();
        CTShd shd = new CTShd();
        TcMar tcmar = new TcMar();
        TblWidth tblwidth2 = new TblWidth();
        TblWidth tableWidth = new TblWidth();
        tableWidth.setW(BigInteger.valueOf(width));
        tcmar.setTop(tblwidth2);
        tblwidth2.setW(BigInteger.valueOf(215));
        tblwidth2.setType("dxa");
        TblWidth tblwidth3 = new TblWidth();
        tcmar.setLeft(tblwidth3);
        tblwidth3.setW(BigInteger.valueOf(215));
        tblwidth3.setType("dxa");
        TblWidth tblwidth4 = new TblWidth();
        tcmar.setBottom(tblwidth4);
        tblwidth4.setW(BigInteger.valueOf(215));
        tblwidth4.setType("dxa");
        TblWidth tblwidth5 = new TblWidth();
        tcmar.setRight(tblwidth5);
        tblwidth5.setW(BigInteger.valueOf(215));
        tblwidth5.setType("dxa");
        borderTop.setVal(org.docx4j.wml.STBorder.NIL);
        borderLeft.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderLeft.setColor("FFFFFF");
        borderLeft.setSz(BigInteger.valueOf(8));
        borderLeft.setSpace(BigInteger.valueOf(0));
        borderRight.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderRight.setColor("FFFFFF");
        borderRight.setSz(BigInteger.valueOf(8));
        borderRight.setSpace(BigInteger.valueOf(0));
        borderBottom.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderBottom.setColor("FFFFFF");
        borderBottom.setSz(BigInteger.valueOf(8));
        borderBottom.setSpace(BigInteger.valueOf(0));
        tcBorders.setTop(borderTop);
        tcBorders.setLeft(borderLeft);
        tcBorders.setRight(borderRight);
        tcBorders.setBottom(borderBottom);
        shd.setVal(org.docx4j.wml.STShd.CLEAR);
        shd.setColor("auto");
        shd.setFill("D6DBE1");
        shd.setThemeFill(org.docx4j.wml.STThemeColor.ACCENT_5);
        shd.setThemeFillTint("33");
        tableCellProperties.setShd(shd);
        tableCellProperties.setTcBorders(tcBorders);
        tableCellProperties.setTcMar(tcmar);
        tableCellProperties.setTcW(tableWidth);
        tableCell.setTcPr(tableCellProperties);
    }

    private void setCellStyleSecondColl(Tc tableCell, int width) {
        TcPr tableCellProperties = new TcPr();
        TcPrInner.TcBorders tcBorders = new TcPrInner.TcBorders();
        CTBorder borderTop = new CTBorder();
        CTBorder borderLeft = new CTBorder();
        CTBorder borderRight = new CTBorder();
        CTBorder borderBottom = new CTBorder();
        TblWidth tableWidth = new TblWidth();
        tableWidth.setW(BigInteger.valueOf(width));
        borderTop.setVal(org.docx4j.wml.STBorder.NIL);
        borderLeft.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderLeft.setColor("FFFFFF");
        borderLeft.setSz(BigInteger.valueOf(8));
        borderLeft.setSpace(BigInteger.valueOf(0));
        borderRight.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderRight.setColor("FFFFFF");
        borderRight.setSz(BigInteger.valueOf(8));
        borderRight.setSpace(BigInteger.valueOf(0));
        borderBottom.setVal(org.docx4j.wml.STBorder.SINGLE);
        borderBottom.setColor("FFFFFF");
        borderBottom.setSz(BigInteger.valueOf(8));
        borderBottom.setSpace(BigInteger.valueOf(0));
        tcBorders.setTop(borderTop);
        tcBorders.setLeft(borderLeft);
        tcBorders.setRight(borderRight);
        tcBorders.setBottom(borderBottom);
        tableCellProperties.setTcBorders(tcBorders);
        tableCellProperties.setTcW(tableWidth);
        tableCell.setTcPr(tableCellProperties);
    }

    private P setList(List<String> list) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        list.forEach(text::setValue);
        JAXBElement<Text> textWrapped = factory.createRT(text);
        r.getContent().add(textWrapped);
        p.getContent().add(r);
        return p;
    }

    private Tbl setTable(WordprocessingMLPackage wordpackage, Inline image) {
        int writableWidth = wordpackage
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
                    setCellStyleFirstCol(cell, 3105);
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
                    setCellStyleSecondColl(cell, 7145);
                    descriptionCells.add(cell);
                }
            }
        }
        for (int i = 0; i < descriptionCells.size(); i++) {
            Tc cell = null;
            switch (i){
                case 0:
                    cell = (Tc) descriptionCells.get(i);
                    cell.getContent().add(addTextToParagraph("Васильцев Игорь Игоревич",
                            36, new BooleanDefaultTrue(), JcEnumeration.LEFT));
                    break;
                case 1:
                    cell = (Tc) descriptionCells.get(i);
                    cell.getContent().add(setList(setSimpleData()));
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


    public static void main(String[] args) throws Exception {
    }
}