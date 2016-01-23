package proope.purchase.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import proope.purchase.to.entity.PurchaseApplyDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseApplyHistoryEntity;

/**
 * 采购报表导出
 * @author 
 *
 */
public class PurchaseReport {
	
	public static String getClassPathPath() {
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
//        System.out.println(path);
        path=path.replace('/', '\\'); // 将/换成\
        path=path.replace("file:", ""); //去掉file:
//        path=path.replace("classes\\", ""); //去掉class\
        path=path.substring(1); //去掉第一个\
        return path;  
    }  

	public static void purchaseHistoryReport(OutputStream os, PurchaseApplyHistoryEntity purchaseApplyHistoryEntity,
			List<PurchaseApplyDetailHistoryEntity> purchaseApplyDetailHistoryEntityList){
		
		Document document = new Document(PageSize.A4, 50, 50, 100, 10);
        try {  
            PdfWriter.getInstance(document, os);
            document.addTitle("采购申请历史报表");

            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font docTitleFont = new Font(baseFont, 14, Font.BOLD);
            Font tableTitleFont = new Font(baseFont, 10, Font.BOLD);
            Font tableFont = new Font(baseFont, 10, Font.NORMAL);
            
            document.open();
            
//            Image img = Image.getInstance(getClassPathPath()+"logo.jpg");//选择图片
            Image img = Image.getInstance(getClassPathPath()+"logo.png");//选择图片
            img.setAlignment(1);
            img.scaleAbsolute(40,30);//控制图片大小
            img.setAbsolutePosition(200,720);//
            document.add(img);
            
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            titleTable.setSpacingBefore(10f);
            titleTable.getDefaultCell().setBorder(0);
            titleTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell titleCell = new PdfPCell(new Paragraph(purchaseApplyHistoryEntity.getBmmc()+"采购申请", docTitleFont));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setFixedHeight(30);
            titleCell.setBorder(0);
            titleTable.addCell(titleCell);
            document.add(titleTable);
            
            
            PdfPTable purchaseHistoryDetailtable = new PdfPTable(2);
            purchaseHistoryDetailtable.setWidthPercentage(100);
            purchaseHistoryDetailtable.setSpacingBefore(10f);
            purchaseHistoryDetailtable.getDefaultCell().setBorder(0);
            purchaseHistoryDetailtable.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cell1 = null;
            cell1 = new PdfPCell(new Paragraph("物料名称", tableTitleFont));
        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell1.setFixedHeight(25);
            purchaseHistoryDetailtable.addCell(cell1);
            cell1 = new PdfPCell(new Paragraph("申请数量", tableTitleFont));
        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell1.setFixedHeight(25);
            purchaseHistoryDetailtable.addCell(cell1);
            for(PurchaseApplyDetailHistoryEntity purchaseApplyDetailHistoryEntity : purchaseApplyDetailHistoryEntityList){
            	cell1 = new PdfPCell(new Paragraph(purchaseApplyDetailHistoryEntity.getWlmc(), tableFont));
            	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            	cell1.setFixedHeight(25);
                purchaseHistoryDetailtable.addCell(cell1);
                cell1 = new PdfPCell(new Paragraph(purchaseApplyDetailHistoryEntity.getSqsl().toString(), tableFont));
            	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            	cell1.setFixedHeight(25);
                purchaseHistoryDetailtable.addCell(cell1);
            }
            
            document.add(purchaseHistoryDetailtable);

            document.add(new Paragraph("创建人："+purchaseApplyHistoryEntity.getCjr() + "    创建日期：" + purchaseApplyHistoryEntity.getCjrq(), tableFont));
            
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
	}
}
