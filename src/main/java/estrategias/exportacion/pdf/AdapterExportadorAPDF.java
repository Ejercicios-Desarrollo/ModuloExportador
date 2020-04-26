package estrategias.exportacion.pdf;

import exportables.Exportable;

public interface AdapterExportadorAPDF {
    public String exportar(Exportable exportable, String rutaCompletaDelArchivo);
}
