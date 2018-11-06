/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;

public class SaveToPDF {

    /**
     ** Metoda, która tworzy plik pdf na podaną ścieżkę
     *
     * @param file ścieżka dostępu do pluku na którym ma być zapisaby plik pdf
     */
    public static void createPdf(File file) {
        try {
            Document document = new Document();
            String HTML = "src/projekt/HTML/Diagnoza/diagnoza.html";
            String CSS = "src/projekt/HTML/Diagnoza/styl.css";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            writer.setInitialLeading(12.5f);
            document.open();
            CSSResolver cssResolver = new StyleAttrCSSResolver();
            CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(CSS));
            cssResolver.addCss(cssFile);
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
            PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
            HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
            CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
            XMLWorker worker = new XMLWorker(css, true);
            XMLParser p = new XMLParser(worker);
            p.parse(new FileInputStream(HTML));
            document.close();
        } catch (Exception e) {
            //showOutputMessage("Zapis do pliku nie jest możliwy. Program zostanie zamknięty");
           // System.exit(1);
        }

    }

    /**
     ** wyświetla KOMUNIKAT O BŁĘDZIE
     *
     * @param message treść komunikatu o błędzie
     */
    public static void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Treść błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
