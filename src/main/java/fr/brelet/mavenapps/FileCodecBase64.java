package fr.brelet.mavenapps;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileCodecBase64 {

    public static void main(String args[]){
        ArrayList list = new ArrayList();
        BarCode barCode = new BarCode();

        try
        {
            String dir2 = "C:\\Users\\E.LOISEL\\Desktop\\PdfToTxt\\Work";
            String dir3 = "C:\\Users\\E.LOISEL\\Desktop\\PdfToTxt\\Old";
            File dir = new File("C:\\Users\\E.LOISEL\\Desktop\\PdfToTxt\\Input");
            File[] fich = dir.listFiles();
            PdfReader pdf = new PdfReader(dir+"\\"+fich[0].getName());

            int nbrPages = pdf.getNumberOfPages();

            for(int i=1; i <= nbrPages; i++)
            {
                String content = PdfTextExtractor.getTextFromPage(pdf, i);
                PrintWriter writer = new PrintWriter(dir2+"\\retour.txt");
                writer.println(content);
                writer.close();
            }

            pdf.close();

            File file = new File(dir2+"\\retour.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null)
            {
                list.add(line);
                sb.append(line);
                sb.append("\n");
            }
            fr.close();

            if (list.size() == 18){
                list.add(11, "");
            }
            System.out.println(list);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Object str : list){
                bw.write((String) str);
                bw.write("\n");
            }

            bw.close();

            Document doc = new Document(new Rectangle(306.10f, 455.84f).rotate());
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(dir2+"\\"+fich[0].getName()));
            doc.open();
            doc.setMargins(20,15,60,15);

            doc.newPage();

            Font font4ptB = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
            Font font5ptB = new Font(Font.FontFamily.TIMES_ROMAN, 28);
            Font font4pt = new Font(Font.FontFamily.TIMES_ROMAN, 14);
            Font font2pt = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            Font font1pt = new Font(Font.FontFamily.TIMES_ROMAN, 6);

            Font font0pt = new Font(Font.FontFamily.TIMES_ROMAN, 10);
            PdfPTable table = new PdfPTable(3);

            table.setWidthPercentage(100);

            PdfPCell cell1 = new PdfPCell(new Phrase(list.get(1).toString(), font4pt));
            cell1.setBorder(0);
            cell1.setHorizontalAlignment(Element.ALIGN_TOP);
            table.addCell(cell1);


            PdfPCell cell2 = new PdfPCell(new Phrase(list.get(2).toString(), font0pt));

            int location = 160;
            PdfContentByte cb = writer.getDirectContent();
            String oui = barCode.code(list.get(2).toString());

            for ( int pos=1; pos <= oui.length(); pos++ ){
                float curSize = Float.parseFloat(oui.substring(pos-1, pos));
                Rectangle rectangle = new Rectangle(curSize+location, 280, location, 245);
                rectangle.setBorder(0);
                if (pos %2 == 0){
                    rectangle.setBackgroundColor(BaseColor.WHITE);
                }else{
                    rectangle.setBackgroundColor(BaseColor.BLACK);
                }
                cb.rectangle(rectangle);
                cb.stroke();
                location += curSize;
            }


            cell2.setBorder(0);
            cell2.setHorizontalAlignment(Element.ALIGN_BOTTOM);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2); //p.setSpacingAfter(20);
            Phrase ph = new Phrase("DACHSER", font4ptB);
            PdfPCell cell3 = new PdfPCell(ph);
            cell3.setBorder(0);
            cell3.setHorizontalAlignment(Element.ALIGN_TOP);
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell3);
            doc.add(table);

            String split[] = list.get(3).toString().split(" ");
            String split2[] = list.get(4).toString().split(" ");

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(100);
            PdfPCell cell21 = new PdfPCell(new Phrase("", font1pt));
            cell21.addElement(new Paragraph(split[0]+" | "+split[2], font1pt));
            cell21.addElement(new Paragraph(list.get(5).toString(), font2pt));
            cell21.addElement(new Paragraph(list.get(7).toString(), font2pt));
            cell21.addElement(new Paragraph(list.get(9).toString(), font2pt));
            cell21.setBorder(0);
            cell21.setBorderWidthRight(0.2f);
            cell21.setBorderWidthTop(0.2f);
            table2.addCell(cell21);
            PdfPCell cell22 = new PdfPCell(new Phrase("", font1pt));
            PdfPTable tabi = new PdfPTable(2);
            tabi.setWidthPercentage(100);
            PdfPCell cell221 = new PdfPCell(new Phrase("", font1pt));
            cell221.addElement(new Paragraph(split[3]+" | "+split[5], font1pt));
            cell221.addElement(new Paragraph(split2[0], font2pt));
            cell221.addElement(new Paragraph(list.get(6).toString(), font1pt));
            cell221.addElement(new Paragraph(list.get(8).toString(), font2pt));
            cell221.addElement(new Paragraph(list.get(10).toString(), font1pt));
            cell221.addElement(new Paragraph(list.get(11).toString(), font2pt));
            cell221.setBorder(0);
            tabi.addCell(cell221);

            PdfPCell cell222 = new PdfPCell(new Phrase("", font1pt));
            cell222.addElement(new Paragraph(split[6]+" | "+split[8], font1pt));
            cell222.addElement(new Paragraph(split2[1], font2pt));
            cell222.setBorder(0);
            tabi.addCell(cell222);

            cell22.addElement(tabi);
            cell22.setBorder(0);
            cell22.setBorderWidthTop(0.2f);
            table2.addCell(cell22);

            PdfPCell cell23 = new PdfPCell(new Phrase("", font1pt));
            cell23.addElement(new Paragraph(list.get(12).toString(), font1pt));
            cell23.addElement(new Paragraph(list.get(14).toString(), font2pt));
            cell23.addElement(new Paragraph(list.get(15).toString(), font2pt));
            cell23.addElement(new Paragraph(list.get(18).toString(), font2pt));
            cell23.setBorder(0);

            cell23.setBorderWidthRight(0.2f);
            cell23.setBorderWidthTop(0.2f);
            table2.addCell(cell23);

            PdfPCell cell24 = new PdfPCell(new Phrase("", font1pt));
            PdfPTable tabT = new PdfPTable(3);
            tabT.setWidthPercentage(100);
            String fy[] = list.get(17).toString().split(" ");
            String f = fy[0];
            String y = fy[1];
            PdfPCell cell241 = new PdfPCell(new Phrase("", font4pt));
            cell241.addElement(new Paragraph("", font4pt));
            cell241.addElement(new Paragraph(f, font4pt));
            cell241.setBorder(0);
            cell241.setMinimumHeight(65);
            cell241.setPaddingLeft(20);
            cell241.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell241.setVerticalAlignment(Element.ALIGN_BOTTOM);
            tabT.addCell(cell241);

            PdfPCell cell242 = new PdfPCell(new Paragraph("", font4ptB));//1346  list.get(16).toString()
            cell242.addElement(new Paragraph(list.get(16).toString(), font5ptB));
            cell242.setPaddingLeft(2);
            cell242.setPaddingTop(10);
            cell242.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell242.setVerticalAlignment(Element.ALIGN_CENTER);
            cell242.setBorder(0);
            tabT.addCell(cell242);

            PdfPCell cell243 = new PdfPCell(new Paragraph("", font2pt));
            Paragraph p = new Paragraph(list.get(13).toString(), font2pt);
            p.setSpacingAfter(20);
            cell243.addElement(p);//001/001
            cell243.addElement(new Paragraph(y, font2pt));
            cell243.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell243.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell243.setPaddingLeft(20);
            cell243.setBorder(0);

            tabT.addCell(cell243);

            cell24.addElement(tabT);
            cell24.setHorizontalAlignment(Element.ALIGN_BOTTOM);

            cell24.setBorder(0);
            cell24.setBorderWidthTop(0.2f);
            table2.addCell(cell24);

            PdfPCell cell25 = new PdfPCell(new Phrase("", font1pt));
            cell25.setBorder(0);
            cell25.setBorderWidthRight(0.2f);
            table2.addCell(cell25);
            PdfPCell cell26 = new PdfPCell(new Phrase("", font1pt));
            cell26.setBorder(0);
            cell26.setBorderWidthTop(0.2f);
            table2.addCell(cell26);

            doc.add(table2);
            doc.close();

            Path tmp = Files.move(Paths.get(dir+"\\"+fich[0].getName()), Paths.get(dir3+"\\"+fich[0].getName()));

        }catch(IOException e)
        {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }







    }




}