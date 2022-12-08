package com.example.curriculumvitae.helper;

import org.docx4j.wml.*;

import java.math.BigInteger;

public class TablePreprocess {

    public static void setCellStyleFirstCol(Tc tableCell, int width) {
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

    public static void setCellStyleSecondColl(Tc tableCell, int width) {
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
}
