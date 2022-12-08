package com.example.curriculumvitae.helper;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.util.List;

public class ParagraphPreprocess {

    public static Inline simpleAddImageToP(WordprocessingMLPackage wordPackage,
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

    public static Inline simpleAddImageToP(WordprocessingMLPackage wordPackage,
                                     byte[] fileContent,
                                     String fileNameHint,
                                     String altText,
                                     int id1,
                                     int id2,
                                     boolean link) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
        return imagePart.createImageInline(fileNameHint, altText, id1, id2, link);
    }

    public static P addImageToParagraph(Inline inline) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }

    public static P addImageToParagraph(Inline inline, JcEnumeration alligment) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        PPr paragraphProperties = new PPr();
        Jc justification = factory.createJc();
        justification.setVal(alligment);
        paragraphProperties.setJc(justification);
        p.setPPr(paragraphProperties);
        p.getContent().add(r);
        Drawing drawing = factory.createDrawing();
        r.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return p;
    }

    public static P addTextToParagraph(String string, int size, BooleanDefaultTrue style, JcEnumeration alligment) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        text.setValue(string);
        RPr rPr = factory.createRPr();
        HpsMeasure hpsMeasure = new HpsMeasure();
        hpsMeasure.setVal(BigInteger.valueOf(size));
        U u = new U();
        PPr paragraphProperties = new PPr();
        Jc justification = factory.createJc();
        justification.setVal(alligment);
        paragraphProperties.setJc(justification);
        u.setVal(UnderlineEnumeration.SINGLE);
        rPr.setU(u);
        rPr.setSz(hpsMeasure);
        rPr.setB(style);
        r.setRPr(rPr);
        r.getContent().add(text);
        p.setPPr(paragraphProperties);
        p.getContent().add(r);
        return p;
    }

    public static P addTextToParagraph(String string, int size) {
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

    public static P addTextToParagraph(String string) {
        ObjectFactory factory = new ObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text text = factory.createText();
        text.setValue(string);
        r.getContent().add(text);
        p.getContent().add(r);
        return p;
    }

    public static P setList(List<String> list) {
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
}
