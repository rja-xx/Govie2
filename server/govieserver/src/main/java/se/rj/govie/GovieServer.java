package se.rj.govie;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.search.ElasticSearchAgent;

import java.io.File;

@Component
public class GovieServer {

    private static Logger logger = Logger.getLogger(GovieServer.class);

    @Autowired
    private ElasticSearchAgent searchAgent;

    @Autowired
    private FirebaseAgent firebaseAgent;

    void start() {
        logger.info("Starting govie server at " + new File(".").getAbsolutePath());
        try {
            searchAgent.connect();

            firebaseAgent.connect();
            firebaseAgent.setupListeners();
            stayAlive();
        } catch (Throwable t) {
            logger.warn("Error occurred: " + t.getMessage(), t);
            System.exit(-1);
        } finally {
            searchAgent.disconnect();
            firebaseAgent.disconnect();
        }
    }

    private void stayAlive() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

