package print.capau.relatorio;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class CompilaJrxmlParaJasper {

	public static void main(String[] args) throws JRException {
		JasperCompileManager.compileReportToFile(
				"/home/clesio/eclipse-workspace/impressao/WebContent/resources/relatorio/usuarios.jrxml");
	}
}