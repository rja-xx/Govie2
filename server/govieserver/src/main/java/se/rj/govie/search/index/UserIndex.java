package se.rj.govie.search.index;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.User;
import se.rj.govie.request.SearchUserRequest;
import se.rj.govie.search.ElasticSearchAgent;

import java.util.ArrayList;
import java.util.List;

import static se.rj.govie.model.User.USER_TYPE;

@Component
public class UserIndex extends Index<User> {

    private static Logger logger = Logger.getLogger(UserIndex.class);

    @Autowired
    private ElasticSearchAgent elasticSearch;

    public List<User> searchByName(SearchUserRequest request) {
        List<User> res = new ArrayList<>();
        elasticSearch.prepareSearch().setTypes(USER_TYPE)
                     .setQuery(QueryBuilders.wildcardQuery("name", "*" + request.getTerm() + "*"))
                     .setSize(20)
                     .get()
                     .getHits().forEach(hit -> res.add(User.fromJson(hit.getSourceAsString().getBytes(), User.class)));
        return res;
    }

    @Override
    public String getIndex() {
        return "userindex";
    }
}
