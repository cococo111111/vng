package services;

import com.vng.jcore.common.Config;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

//118.102.6.131
public class JmxtransFileGeneration {

    protected static Template template = null;
    protected static TemplateLoader templateLoader = null;
    private static final TemplateDataDictionary dict = TemplateDictionary.create();
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZooKeeperZMonitor.class);

    private void JmxtransReloadConfig() {
        Runtime r = Runtime.getRuntime();
        Process p;
        try {
            p = r.exec(Config.getParam("jmxtrans", "restart_cmd"));
            p.waitFor();
            logger.info("Restart jmxtrans sucessfully");
        } catch (IOException ex) {
            logger.error("Cannot restart jmxtrans. " + ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            logger.error("Cannot restart jmxtrans. " + ex.getMessage(), ex);
        }

    }

    public void createFileJmxJson(String host, String port, String node) {
        String content = "";
        FileOutputStream fop = null;
        String configDir = Config.getParam("jmxtrans", "config_dir");
        File file = new File(configDir + "/" + node.replace("/", ".").replaceFirst(".", "/") + "_" + host + "-" + port + ".json");

        templateLoader = TemplateResourceLoader.create("templates/");
        try {
            template = templateLoader.getTemplate("jmxtrans.xtm");
            dict.setVariable("JMX_HOST", host);
            dict.setVariable("JMX_PORT", port);
            dict.setVariable("GRAPHITE_HOST", Config.getParam("jmxtrans", "graphite_host"));
            dict.setVariable("GRAPHITE_PORT", Config.getParam("jmxtrans", "graphite_port"));
            content = template.renderToString(dict);
        } catch (TemplateException ex) {
            java.util.logging.Logger.getLogger(JmxtransFileGeneration.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Cannot generate json configuration file.");
        }
        try {
            fop = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            logger.info("Generate json configuration file " + file.toString() + " successfully.");
            JmxtransReloadConfig();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(JmxtransFileGeneration.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Cannnot write to file." + ex.getMessage(), ex);
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(JmxtransFileGeneration.class.getName()).log(Level.SEVERE, null, ex);
                logger.error("Cannot create file for writing.");
            }
        }
    }

//    public static void main(String[] args) throws TemplateException {
//        templateLoader = TemplateResourceLoader.create("templates/");
//        template = templateLoader.getTemplate("jmxtrans_1.xtm");
//        TemplateDataDictionary dict = TemplateDictionary.create();
//        for (int i = 0; i < 10; i++) {
////            System.out.println(i);
//            TemplateDataDictionary d = dict.addSection("city_list");
////            dict.addSection("city_list");
//            d.setVariable("city_name", "locth2" + Integer.toString(i));
//        }
////        dict.addSection("city_list");
////        dict.setVariable("city_name", "locth2");
//        String content = template.renderToString(dict);
//        System.out.println(content);
//
//    }
}
