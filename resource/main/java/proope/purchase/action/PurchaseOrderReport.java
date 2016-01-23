package proope.purchase.action;

import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 报表导出
 * @author 
 *
 */
public class PurchaseOrderReport {
	
	public static String getClassPathPath() {
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
//        System.out.println(path);
        path=path.replace('/', '\\'); // 将/换成\
        path=path.replace("file:", ""); //去掉file:
//        path=path.replace("classes\\", ""); //去掉class\
        path=path.substring(1); //去掉第一个\
        return path;  
    }  

	public static void purchaseHistoryReport(OutputStream os){
		int pdfPTablecont= 48;
		Document document = new Document(PageSize.A4, 50, 50, 100, 10);
        try {  
            PdfWriter.getInstance(document, os);
            document.addTitle("上海帝润有限公司 采购订单");

            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font docTitleFont = new Font(baseFont, 14, Font.BOLD);
            Font tableTitleFont = new Font(baseFont, 10, Font.BOLD);
            Font tableFont = new Font(baseFont, 10, Font.NORMAL);
            Font docTitleFont02 = new Font(baseFont, 14, Font.NORMAL);
            
            document.open();
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            titleTable.setSpacingBefore(10f);
            titleTable.getDefaultCell().setBorder(0);
            titleTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            /*=========PDF标题============*/
            PdfPCell titleCell = new PdfPCell(new Paragraph("上海帝润电子有限公司", docTitleFont));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setFixedHeight(28);
            titleCell.setBorder(0);
            titleTable.addCell(titleCell);
            titleCell = new PdfPCell(new Paragraph("Shanghai   Dirun   Electric   Co .  Ltd", docTitleFont02));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setFixedHeight(25);
            titleCell.setBorder(0);
            titleTable.addCell(titleCell);
            titleCell = new PdfPCell(new Paragraph("采购订单(PO)", docTitleFont));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setFixedHeight(28);
            titleCell.setBorder(0);
            titleTable.addCell(titleCell);
            document.add(titleTable);
            /*=========input填写============*/
            
            /*=========填写表格============*/
            //第二行
            String[] pdfPTable02arr07 = new String[]{
           		 "日期:","xxx",""
            };
            int[] pdfPTable02arrint07 = {4,10,34};  
            
            String[] pdfPTable02arr06 = new String[]{
           		 "编号:","xxx",""
            };
            int[] pdfPTable02arrint06 = {4,10,34};  
            
            String[] pdfPTable02arr05 = new String[]{
           		 "","From NO:","xxx"
            };
            int[] pdfPTable02arrint05 = {34,7,7};  
            
            String[][] pdfPTable02arr = new String[][]{
        			{"供应商","xxx","联系人","xxx","电话","xxxx"},
        			{"收货方","xxx","联系人","xxx","电话","xxxx"}	
            };
            int[] pdfPTable02arrint = {5,11}; 
            
            String[] tableContentGrade = new String[]
        			{"序号","姓名","型号规格","数量","单位","单价(含税)","总金额(含税)"};
            int[] pdfPTable02arrint02 = {5,7,10,5,4,7,10}; 
            
            String[] pdfPTable02arr09 = new String[]{
           		 "采购方:","xxx","供方","xxxxxx"
            };
            int[] pdfPTable02arrint09 = {4,20,4,20};  
            
            String[] pdfPTable02arr08 = new String[]{
           		 "地址:","xxx",""
            };
            int[] pdfPTable02arrint08 = {4,10,34};  
            
            String[] pdfPTable02arr10 = new String[]{
           		 "(签字盖章)","xxx","(签字盖章)","xxxxxx"
            };
            int[] pdfPTable02arrint10 = {5,19,5,19};  
            
            PdfPTable pdfPTable02 = new PdfPTable(pdfPTablecont);
            pdfPTable02.setWidthPercentage(100);
            pdfPTable02.setSpacingBefore(10f);
            pdfPTable02.getDefaultCell().setBorder(0);
            pdfPTable02.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            //填写框
   			for (int i = 0; i < pdfPTable02arr06.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr06[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint06[i]);
   				if(i == 1){
   					pdfPTable02cell13.setBorderWidthLeft(0);
   					pdfPTable02cell13.setBorderWidthRight(0);
   					pdfPTable02cell13.setBorderWidthTop(0);
   				}else{
   					pdfPTable02cell13.setBorder(0);
   				}
   				pdfPTable02.addCell(pdfPTable02cell13);
   			}  
   			//填写框
   			for (int i = 0; i < pdfPTable02arr07.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr07[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint07[i]);
   				if(i == 1){
   					pdfPTable02cell13.setBorderWidthLeft(0);
   					pdfPTable02cell13.setBorderWidthRight(0);
   					pdfPTable02cell13.setBorderWidthTop(0);
   				}else{
   					pdfPTable02cell13.setBorder(0);
   				}
   				pdfPTable02.addCell(pdfPTable02cell13);
   			}  
          //填写框
   			for (int i = 0; i < pdfPTable02arr05.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr05[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setBorder(0);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint05[i]);
   				pdfPTable02.addCell(pdfPTable02cell13);
   			}   
            
   		for (int j = 0; j < pdfPTable02arr.length; j++) {
   			for (int i = 0; i < pdfPTable02arr[0].length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr[j][i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint[i % 2]);
   				pdfPTable02.addCell(pdfPTable02cell13);
   			}
   		}
   		//表格头部
   		PdfPCell pdfPCellORDERMsg = new PdfPCell(
   				new Paragraph("订单信息", tableTitleFont));
   		pdfPCellORDERMsg.setHorizontalAlignment(Element.ALIGN_CENTER);
   		pdfPCellORDERMsg.setVerticalAlignment(Element.ALIGN_MIDDLE);
   		pdfPCellORDERMsg.setFixedHeight(25);
   		pdfPCellORDERMsg.setColspan(pdfPTablecont);
   		pdfPTable02.addCell(pdfPCellORDERMsg);
   		//表格标题
   		pdfPTable02.setWidthPercentage(100);
   		pdfPTable02.setSpacingBefore(10f);
   		pdfPTable02.getDefaultCell().setBorder(0);
   		pdfPTable02.setHorizontalAlignment(Element.ALIGN_CENTER);
   		  PdfPCell pdfTableHeaderCell = new PdfPCell();
            for (int j = 0; j < tableContentGrade.length; j++) {
             	pdfTableHeaderCell.setPhrase(new Paragraph(tableContentGrade[j], tableTitleFont));
             	pdfTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
             	pdfTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
             	pdfTableHeaderCell.setFixedHeight(25);
             	pdfTableHeaderCell.setColspan(pdfPTable02arrint02[j]);
             	pdfPTable02.addCell(pdfTableHeaderCell);
             }
            
          //表身
            PdfPCell pdfTableContentCell = new PdfPCell();
            pdfTableContentCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            pdfTableContentCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            for (int j = 0; j < tableContentGrade.length; j++) {
   				for (int i = 0; i < tableContentGrade.length; i++) {
   					pdfTableContentCell.setPhrase(new Paragraph("saaaa",tableFont));
   					pdfTableContentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
   					pdfTableContentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   					pdfTableContentCell.setFixedHeight(25);
   					pdfTableContentCell.setColspan(pdfPTable02arrint02[i]);
   					pdfPTable02.addCell(pdfTableContentCell);
   				}
   			}
            //合同条款
            //头部
   		PdfPCell  heyueTilteCell = new PdfPCell(new Paragraph("合同条款",tableTitleFont));
   		heyueTilteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
   		heyueTilteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
   		heyueTilteCell.setFixedHeight(25);
   		heyueTilteCell.setColspan(pdfPTablecont);
   		pdfPTable02.addCell(heyueTilteCell);
   		
   		//合同条款表格
   		 int[] heyueints = new int[]{3,12,33};
   		 String[] heyueintsGradeto = new String[3];
   		 String[] heyueintsGrade = new String[]{"序号","项目","具体内容"};
   		 String[][] heyueintsGrade01 = new String[][] {
   				 {"1","质量要求及技术标准","按图纸要求或符合行业规格的标准条件，RoHS&REACH对应"}
   		 ,{"2","包装标准及方式","合理的标准包装，保证通常的运输条件下的产品安全"}
   		 ,{"3","运输方式及费用负担","选择合理的运输方式，执行到厂价"}
   		 ,{"4","交货期及交货地点","交货期参考”交货要求“，如无指定，交货至本厂"}
   		 ,{"5","验收标准及方法","按图纸或技术文件验收，根据要求提供出货检验单等"}
   		 ,{"6","合同变更及解除","预留出合理的时间供地方调整"}
   		 ,{"7","违约责任","依据通用合同条款及规则"}
   		 ,{"8","其他约定事项","按约定执行，三天内回传，如不回传视默认"}
   		 
   		 };
   		 float lineHeight1 = (float)20.0;
   		 for (int j = 0; j <= heyueintsGrade01.length; j++) {
   			 if(j == 0){
   				 heyueintsGradeto = heyueintsGrade;
   			 }else{
   				 heyueintsGradeto = heyueintsGrade01[j-1];
   			 }
   				for (int i = 0; i < heyueints.length; i++) {
   					PdfPCell  heyueCell01 = new PdfPCell(new Paragraph(heyueintsGradeto[i],tableTitleFont));
   					heyueCell01.setHorizontalAlignment(Element.ALIGN_LEFT);
   					heyueCell01.setVerticalAlignment(Element.ALIGN_MIDDLE);
   					heyueCell01.setFixedHeight(lineHeight1);
   					//merge column
   					heyueCell01.setColspan(heyueints[i]);
   					pdfPTable02.addCell(heyueCell01);
   				}
   			}
   		//填写框
   			for (int i = 0; i < pdfPTable02arr09.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr09[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint09[i]);
   				pdfPTable02cell13.setBorder(0);
   				pdfPTable02.addCell(pdfPTable02cell13);
   			} 
   		//填写框
   			for (int i = 0; i < pdfPTable02arr08.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr08[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint08[i]);
   				pdfPTable02cell13.setBorder(0);
   				pdfPTable02.addCell(pdfPTable02cell13);
   			} 
   			//填写框
   			for (int i = 0; i < pdfPTable02arr10.length; i++) {
   				PdfPCell pdfPTable02cell13 = new PdfPCell(
   						new Paragraph(pdfPTable02arr10[i], tableFont));
   				pdfPTable02cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
   				pdfPTable02cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
   				pdfPTable02cell13.setFixedHeight(25);
   				pdfPTable02cell13.setColspan(pdfPTable02arrint10[i]);
   				pdfPTable02cell13.setBorder(0);
   				pdfPTable02.addCell(pdfPTable02cell13);
   			} 
   		 document.add(pdfPTable02);

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
