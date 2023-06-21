package com.github.ulwx.aka.admin.utils;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.ulwx.tool.FileUtils;
import com.ulwx.tool.Path;
import org.apache.log4j.Logger;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class Html2pdf {
    private static Logger log = Logger.getLogger(Html2pdf.class);
    public static void html2pdf(String url, String pdfFile) throws Exception {
        // step 2
        OutputStream os = null;
        try {
            log.debug("开始转换:"+pdfFile+"....");
            os=new FileOutputStream(pdfFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);

            // step 3 解决中文支持
            ITextFontResolver fontResolver = renderer.getFontResolver();
            boolean found=false;
            if("linux".equals(getCurrentOperatingSystem())){
                if(new File("/usr/share/fonts/chiness/simsun.ttc").exists()) {
                    fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                    found=true;
                }
            }else{
                if(new File("/usr/share/fonts/chiness/simsun.ttc").exists()) {
                    fontResolver.addFont("c:/Windows/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                    found=true;
                }
            }
            if(!found){
                File simsunFile=new File(FileUtils.getTempDirectory()+"/simsun.ttc");
                if(!simsunFile.exists()) {
                    InputStream inputStream = Path.getResource("/simsun.ttc");
                    FileUtils.copyInputStreamToFile(inputStream, simsunFile);
                }
                fontResolver.addFont(simsunFile.getAbsolutePath(),
                        BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
            renderer.layout();
            renderer.createPDF(os);
            log.debug("结束转换:"+pdfFile+"....");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os!=null){
                os.close();
            }
        }


    }

    public static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }


    public static void main(String[] args) {

        String pdfFile = "c:/abc.pdf";
        try {
            String s=Path.getClassPath();
            int i=0;
            html2pdf("http://localhost:8080/abc/jsp/ulwxframe/test/exportProtocolList.jsp", pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
