package se.rj.govie.search;

import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;
import se.rj.govie.model.IndexableObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ElasticSearchAgent {

    final static Logger logger = Logger.getLogger(ElasticSearchAgent.class);

    private TransportClient client;

    public void connect() {
        try {
            InetSocketTransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
            client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(address);
            logger.info(client.connectedNodes().size() + " connected elastic search nodes");
            client.connectedNodes().forEach(client -> logger.info("node address: " + client.getHostAddress()));
        } catch (UnknownHostException e) {
            throw new RuntimeException("Failed to connect to elastic search cluster!", e);
        }
    }

    public void disconnect() {
        logger.info("Closing elastic search client");
        client.close();
    }

    public void addToIndex(IndexableObject obj) {
        client.prepareIndex(obj.getIndex(), obj.getType(), obj.getId())
              .setSource(obj.toJson())
              .get();
    }
}
