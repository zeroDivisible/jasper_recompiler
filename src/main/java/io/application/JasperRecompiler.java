package io.application;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple class wrapping JasperReports jrxml recompiler
 * @author zerodi
 */
public class JasperRecompiler {
    private static final Logger log = LoggerFactory.getLogger(JasperRecompiler.class);

    public static void main(String[] args) {
        log.debug("working!");
        JasperRecompiler jasperRecompiler = new JasperRecompiler();
        jasperRecompiler.recompileFile("path", "dest");
    }

    /**
     * Compiles input .jrxml file to output destination file
     * @param path source file location
     * @param destination destination file location
     * @throws JRException
     */
    public void recompileFile(String path, String destination) throws JRException {
        JasperCompileManager.compileReportToFile(path, destination);
    }
}
