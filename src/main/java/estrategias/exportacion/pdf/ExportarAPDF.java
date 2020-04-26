package estrategias.exportacion.pdf;

import config.Config;
import estrategias.exportacion.EstrategiaDeExportacion;
import exportables.Exportable;

public class ExportarAPDF implements EstrategiaDeExportacion {

    private String nombreDelArchivo = "datos";
    private String extension = ".pdf";

    private String rutaCompletaDelArchivo() {
        return Config.RUTA_EXPORTACION + this.nombreDelArchivo + this.extension;
    }

    public void setNombreDelArchivo(String nombreDelArchivo) {
        this.nombreDelArchivo = nombreDelArchivo;
    }

    private AdapterExportadorAPDF adapter;

    public ExportarAPDF(AdapterExportadorAPDF adapter){
        this.adapter = adapter;
    }

    public String exportar(Exportable exportable) {
        return this.adapter.exportar(exportable, this.rutaCompletaDelArchivo());
    }
}
