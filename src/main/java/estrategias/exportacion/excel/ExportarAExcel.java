package estrategias.exportacion.excel;

import config.Config;
import estrategias.exportacion.EstrategiaDeExportacion;
import exportables.Exportable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExportarAExcel implements EstrategiaDeExportacion {

    private String nombreDelArchivo = "datos";
    private String extension = ".xlsx";

    private String rutaCompletaDelArchivo(){
        return Config.RUTA_EXPORTACION + this.nombreDelArchivo + this.extension;
    }

    public void setNombreDelArchivo(String nombreDelArchivo) {
        this.nombreDelArchivo = nombreDelArchivo;
    }

    public String exportar(Exportable exportable) {
        Map<String, List<String>> datos = exportable.datos();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sample sheet");
        Set<String> keyset = datos.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = datos.get(key).toArray();
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }
        try {
            FileOutputStream out
                    = new FileOutputStream(new File(rutaCompletaDelArchivo()));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rutaCompletaDelArchivo();
    }

}
