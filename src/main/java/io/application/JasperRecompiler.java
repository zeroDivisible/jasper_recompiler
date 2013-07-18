package io.application;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Simple class wrapping JasperReports jrxml recompiler
 * @author zerodi
 */
public class JasperRecompiler {
    private static final Logger log = LoggerFactory.getLogger(JasperRecompiler.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            log.error("Please provide one argument, being path to a folder with templates");
            return;
        }

        JasperRecompiler jasperRecompiler = new JasperRecompiler();
        String path = args[0];
        for (File f : jasperRecompiler.findTemplates(path)) {
            log.debug("trying to compile {}", f.getPath());
            try {
                jasperRecompiler.recompileFile(f.getPath(), jasperRecompiler.findOutputPath(f));
            } catch (Exception e) {
                log.error("while compiling templates", e);
            }
        }
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


    /**
     * Finds all .jrxml files in specified directory. <b>NON-RECURSIVE as of now, can be easily changed.</b>
     * @param directory
     * @return
     */
    public Collection<File> findTemplates(String directory) {
        Collection<File> files = new ArrayList<>();

        File directoryToSearch = new File(directory);
        files.addAll(Arrays.asList(directoryToSearch.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".jrxml");
            }
        })));

        return files;
    }

    /**
     * Will save output file in the same directory in which the input was, just with .jasper extension
     * @param input
     * @return
     */
    public String findOutputPath(File input) {
        String outPath = "";
        outPath = String.format("%s%s%s%s",
                input.getParentFile().getPath(),
                File.separator,
                input.getName().replace(".jrxml", ""),
                ".jasper");

        return outPath;
    }
}
