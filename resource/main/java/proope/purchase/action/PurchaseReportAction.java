package proope.purchase.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
//import java.util.HashMap;
import java.util.Map;

import proope.purchase.service.PurchaseApplyService;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.struts.BaseAction;

/**
 * 采购部分报表Action
 * @author 
 *
 */
public class PurchaseReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PurchaseApplyService purchaseApplyService;
	
	private String purchaseApplyHistoryId;
	
	/**
	 * 导出采购申请历史
	 * @return
	 */
	public String purchaseHistoryReportExport(){
		
		OutputStream oStream=null;
        try {
			oStream=this.getHttpServletResponse().getOutputStream();
			PurchaseReport.purchaseHistoryReport(oStream, 
					purchaseApplyService.findPurchaseApplyHistoryEntityById(purchaseApplyHistoryId), 
					purchaseApplyService.findPurchaseApplyDetailHistoryListByPadhId(purchaseApplyHistoryId));
//			Map<String, String> params = new HashMap<String, String>();
//		    params.put("userName", "iteye.com");  
//		    params.put("userDept", "java");  
//		    params.put("borrowTime", "11-07-24 10:00");  
//		    params.put("borrowRemark", "c#");  
//		    params.put("borrowDate", "2011年");  
//		    params.put("assetName", "KJ-AA-0001");  
//		    params.put("assetModel", "ThinkPad T61");  
//		    params.put("assetConfig", "ai5df");  
//		    params.put("assetSequence", "1111-2222-3333");  
//		    createPDF(oStream, params); 
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} //
		catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			try {
				oStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	private static void createPDF(OutputStream os, Map<String, String> params) {
        Document document = new Document(PageSize.A4, 50, 50, 100, 10);//
        
        try {  
            PdfWriter.getInstance(document, os);  
            // 
            document.addTitle("Hello World Example"); //
//            document.addSubject("This example explains how to add metadata."); // 
//            document.addAuthor("xuxb"); // 
//            document.addCreator("My program using iText"); // 
//            document.addKeywords("iText");  //
            
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
   
            Font simsun = new Font(baseFont, 10, Font.NORMAL);
            
            document.open();  
              
            float[] f = {40,60};  
            PdfPTable header = new PdfPTable(f);   
            header.setSpacingBefore(5f);  
            header.setWidthPercentage(100);  
            header.getDefaultCell().setBorder(0);  
//          LOGO KJLink  
//            PdfPCell h1 = new PdfPCell(Image.getInstance(params.get("imgPath")));  
//            h1.setBorder(0);  
//            h1.setBorderWidthBottom(0.2f);  
//            h1.setPaddingTop(25f);  
//          h1.setFixedHeight(100);  
//            header.addCell(h1);  
            PdfPCell h2 = new PdfPCell(new Paragraph("行"));  
            h2.setBorder(0);  
            h2.setBorderWidthBottom(0.2f);  
            h2.setPaddingTop(20f);  
            h2.setHorizontalAlignment(Element.ALIGN_RIGHT);  
            header.addCell(h2);  
              
            document.add(header);  
              
            PdfPTable table = new PdfPTable(6); // Code 1  
            table.setWidthPercentage(100);  
            table.setSpacingBefore(10f);  
//          table.getDefaultCell().setBorder(0);  
            table.setHorizontalAlignment(Element.ALIGN_CENTER);//
              
//          
            PdfPCell row1 = new PdfPCell(new Paragraph("KJLINK International Co.fgff"));  
            row1.setColspan(6);  
//          cell0.setHorizontalAlignment(Element.ALIGN_CENTER); //  
            row1.setFixedHeight(30);  
            row1.setPadding(5);  
            row1.setHorizontalAlignment(Element.ALIGN_CENTER); // 
            table.addCell(row1);  
              
//            
            PdfPCell hCell1 = new PdfPCell(new Paragraph("kkkkkk中文", simsun));  
            hCell1.setHorizontalAlignment(Element.ALIGN_CENTER);  
            hCell1.setFixedHeight(25);  
            table.addCell(hCell1);  
            table.addCell(new Paragraph(params.get("userName")));  
  
            PdfPCell hCell2 = new PdfPCell(new Paragraph("hhg"));  
            hCell2.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(hCell2);  
            table.addCell(new Paragraph(params.get("userDept")));  
            PdfPCell hCell3 = new PdfPCell(new Paragraph("rtrt"));  
            hCell3.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(hCell3);  
            table.addCell(new Paragraph(params.get("borrowTime")));  
              
//           
            PdfPCell row3 = new PdfPCell(new Paragraph("rtr"));  
            row3.setColspan(6);  
            row3.setFixedHeight(20);  
            row3.setPadding(5);  
            row3.setBorder(0);  
            row3.setBorderWidthLeft(0.2f);  
            row3.setBorderWidthRight(0.2f);  
//          row3.setBorderColorBottom(BaseColor.WHITE);  
            row3.setVerticalAlignment(Element.ALIGN_CENTER);  
            table.addCell(row3);  
              
//          
            PdfPCell row3_2 = new PdfPCell(new Paragraph(params.get("borrowRemark")));  
            row3_2.setColspan(6);  
            row3_2.setBorder(0);  
            row3_2.setFixedHeight(80);  
            row3_2.setPaddingRight(10f);  
            row3_2.setBorderWidthLeft(0.2f);  
            row3_2.setBorderWidthRight(0.2f);  
//          row3_1.setBorderColorTop(BaseColor.WHITE);//ColorTop(BaseColor.RED);  
            row3_2.setHorizontalAlignment(Element.ALIGN_LEFT);  
            row3_2.setPaddingLeft(20f);  
            table.addCell(row3_2);  
              
//          
            PdfPCell row3_1 = new PdfPCell(new Paragraph("jhjk" + params.get("borrowDate")));  
            row3_1.setColspan(6);  
            row3_1.setBorder(0);  
            row3_1.setPaddingRight(10f);  
            row3_1.setBorderWidthLeft(0.2f);  
            row3_1.setBorderWidthRight(0.2f);  
//          row3_1.setBorderColorTop(BaseColor.WHITE);//ColorTop(BaseColor.RED);  
            row3_1.setFixedHeight(20);  
            row3_2.setPaddingBottom(6f);  
            row3_1.setHorizontalAlignment(Element.ALIGN_RIGHT);  
            table.addCell(row3_1);  
          
              
//         
            PdfPCell row5 = new PdfPCell(new Paragraph("hg"));  
            row5.setColspan(6);  
            row5.setFixedHeight(25);  
            row5.setPadding(5);  
            row5.setHorizontalAlignment(Element.ALIGN_CENTER); //
            table.addCell(row5);  
              
//         
            PdfPCell cell1 = new PdfPCell(new Paragraph("jk"));  
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell1.setFixedHeight(25);  
            table.addCell(cell1);  
            PdfPCell cell2 = new PdfPCell(new Paragraph("jg"));  
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell2);  
            PdfPCell cell3 = new PdfPCell(new Paragraph("fsd"));  
            cell3.setColspan(2);  
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell3);  
            PdfPCell cell4 = new PdfPCell(new Paragraph("csd"));  
            cell4.setColspan(2);  
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell4);  
              
//         
            PdfPCell cell21 = new PdfPCell(new Paragraph(params.get("assetName")));  
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);  
            cell21.setFixedHeight(25);  
            table.addCell(cell21);  
            PdfPCell cell22 = new PdfPCell(new Paragraph(params.get("assetModel")));  
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell22);  
            PdfPCell cell23 = new PdfPCell(new Paragraph(params.get("assetConfig")));  
            cell23.setColspan(2);  
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell23);  
            PdfPCell cell24 = new PdfPCell(new Paragraph(params.get("assetSequence")));  
            cell24.setColspan(2);  
            cell24.setHorizontalAlignment(Element.ALIGN_CENTER);  
            table.addCell(cell24);  
              
              
//          
            table.addCell("");  
            table.addCell("");  
            PdfPCell blank11 = new PdfPCell(new Paragraph(""));  
            blank11.setFixedHeight(25);  
            blank11.setColspan(2);  
            table.addCell(blank11);  
            PdfPCell blank12 = new PdfPCell(new Paragraph(""));  
            blank12.setColspan(2);  
            table.addCell(blank12);  
              
//         
            table.addCell("");  
            table.addCell("");  
            PdfPCell blank21 = new PdfPCell(new Paragraph(""));  
            blank21.setFixedHeight(25);  
            blank21.setColspan(2);  
            table.addCell(blank21);  
            PdfPCell blank22 = new PdfPCell(new Paragraph(""));  
            blank22.setColspan(2);  
            table.addCell(blank22);  
//         
            table.addCell("");  
            table.addCell("");  
            PdfPCell blank31 = new PdfPCell(new Paragraph(""));  
            blank31.setColspan(2);  
            blank31.setFixedHeight(25);  
            table.addCell(blank31);  
            PdfPCell blank32 = new PdfPCell(new Paragraph(""));  
            blank32.setColspan(2);  
            table.addCell(blank32);  
              
//          
            PdfPCell row6 = new PdfPCell(new Paragraph(""));  
            row6.setColspan(6);  
            row6.setFixedHeight(120);  
            row6.setPadding(5);  
            row6.setVerticalAlignment(Element.ALIGN_CENTER);  
            table.addCell(row6);  
              
//          
            PdfPCell row7 = new PdfPCell(new Paragraph(""));  
            row7.setColspan(6);  
            row7.setFixedHeight(120);  
            row7.setPadding(5);  
            row7.setVerticalAlignment(Element.ALIGN_CENTER);  
            table.addCell(row7);  
              
//        
            document.add(table);  
              
            document.add(new Paragraph("KJLink International, Inc"));  
            document.add(new Paragraph("(c)Copyright 2005")); 
              
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
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

	public void setPurchaseApplyService(PurchaseApplyService purchaseApplyService) {
		this.purchaseApplyService = purchaseApplyService;
	}
	
	public String getPurchaseApplyHistoryId() {
		return purchaseApplyHistoryId;
	}

	public void setPurchaseApplyHistoryId(String purchaseApplyHistoryId) {
		this.purchaseApplyHistoryId = purchaseApplyHistoryId;
	}

}
