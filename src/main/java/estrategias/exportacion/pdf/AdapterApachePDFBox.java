package estrategias.exportacion.pdf;

import config.Config;
import exportables.Exportable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdapterApachePDFBox implements AdapterExportadorAPDF {

    public String exportar(Exportable exportable, String rutaCompletaDelArchivo) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            // add page to the PDF document
            document.addPage(page);
            // For writing to a page content stream
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            //Setting the position for the line
            contentStream.newLineAtOffset(25, 750);
            String text = "";

            Map<String, List<String>> datos = exportable.datos();

            Set<String> keyset = datos.keySet();
            for (String key : keyset) {
                Object[] objArr = datos.get(key).toArray();
                text = "Clave: " + (String) key + ". Datos: ";
                contentStream.showText(text);
                for (Object obj : objArr) {
                    text = (String) obj + ", ";
                    //Adding text in the form of string
                    contentStream.showText(text);
                }
            }

            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();

            //Saving the document
            document.save(new File(rutaCompletaDelArchivo));

            //Closing the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rutaCompletaDelArchivo;
    }
}
