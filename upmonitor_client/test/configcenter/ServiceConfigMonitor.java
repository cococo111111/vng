/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configcenter;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
//import tup_passportservice.PassportBusinessServiceClient;

/**
 *
 * @author taitt
 */
public class ServiceConfigMonitor implements ZKClient.Monitor {

    private static final Logger _logger = Logger.getLogger(ServiceConfigMonitor.class);
    private static final String nodeConfig = "/config";
    private static final String nodeConfigPPS = "/config/passportservice";

    @Override
    public List<String> getNodes() {
        List<String> nodes = new LinkedList<String>();
        nodes.add(nodeConfig);
        nodes.add(nodeConfigPPS);
        return nodes;
    }

    @Override
    public void onCreate(ZKClient.Data zkdata) {
        _logger.info("ServiceConfigMonitor.onCreate: ");
        _logger.info("   zkdata.node: " + zkdata.node);
        _logger.info("   zkdata.nodeData: " + new String(zkdata.nodeData));
        _logger.info("   zkdata.childrenData: " + zkdata.childrenData);

        // parse zkdata de lay config...
        if (zkdata.node.equals(ServiceConfigMonitor.nodeConfigPPS)) {
            // parse json, set Config.serviceAhost, Config.serviceAport
            // doan xu ly nay tuy thuoc vao cach minh luu config trong zookeeper
            _logger.info("BANG! PPS SERVICE CHANGE CONFIG");

//            PassportBusinessServiceClient.changeConfig(zkdata.nodeData);
        }
    }

    @Override
    public void onChange(ZKClient.Data zkdata) {
        onCreate(zkdata);
    }

    @Override
    public void onDelete() {
    }

    @Override
    public void onClose() {
    }
}
