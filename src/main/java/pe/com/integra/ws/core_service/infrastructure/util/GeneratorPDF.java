package pe.com.integra.ws.core_service.infrastructure.util;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.io.File;
import java.io.FileNotFoundException;

public class GeneratorPDF {
    public JasperReport getCompiledReport1(String reportPath, String reportFileName) throws FileNotFoundException {
        try {
            // Verify that the file exists
            File reportFile = new File(reportPath + reportFileName + ".jrxml");
            if (!reportFile.exists()) {
                throw new FileNotFoundException("Report file not found: " + reportFile.getPath());
            }

            // Compile the .jrxml file
            return JasperCompileManager.compileReport(reportFile.getPath());
        } catch (Exception e) {
            // Handle exceptions and wrap them in a runtime exception if necessary
            throw new RuntimeException("Error compiling report: " + e.getMessage(), e);
        }
    }
}
