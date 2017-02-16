package se.rj.govie.search.index;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.User;
import se.rj.govie.search.ElasticSearchAgent;

@Component
public class UserIndex {

    private static Logger logger = Logger.getLogger(UserIndex.class);

    @Autowired
    private ElasticSearchAgent elasticSearchAgent;

    public void add(User user) {
        logger.info("added user " + user);
    }
}
