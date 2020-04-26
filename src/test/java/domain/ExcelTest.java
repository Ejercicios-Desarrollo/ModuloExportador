package domain;

import estrategias.exportacion.EstrategiaDeExportacion;
import estrategias.exportacion.excel.ExportarAExcel;
import estrategias.exportacion.pdf.AdapterApachePDFBox;
import estrategias.exportacion.pdf.AdapterExportadorAPDF;
import estrategias.exportacion.pdf.ExportarAPDF;
import exportables.Documento;
import exportador.Exportador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcelTest {

    private Documento documento;
    private Exportador exportador;
    private EstrategiaDeExportacion estrategiaExcel;
    private EstrategiaDeExportacion estrategiaPDF;
    private AdapterApachePDFBox adapterApachePDFBox;

    @Before
    public void init() {
        this.documento = new Documento();
        this.documento.agregarDato("Tomi", "Tomas", "Merencio", "22", "Sistema Dual");
    }

    @Test
    public void exportarYCambiarEstrategia(){
        estrategiaExcel = new ExportarAExcel();
        estrategiaExcel.setNombreDelArchivo("datosExcel");

        adapterApachePDFBox = new AdapterApachePDFBox();
        estrategiaPDF = new ExportarAPDF(adapterApachePDFBox);
        estrategiaPDF.setNombreDelArchivo("datosPDF");

        exportador = new Exportador(estrategiaExcel);
        exportador.setExportable(documento);
        exportador.exportar();

        exportador.cambiarEstrategia(estrategiaPDF);
        exportador.exportar();

    }
}
