package se.rj.govie.search.index;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.model.User;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.search.ElasticSearchAgent;

import java.util.ArrayList;
import java.util.List;

import static se.rj.govie.model.User.USER_TYPE;

@Component
public class MovieIndex extends Index<Movie> {

    private static Logger logger = Logger.getLogger(MovieIndex.class);

    @Autowired
    private ElasticSearchAgent elasticSearch;

    public List<Movie> searchByTitle(SearchMovieRequest request) {
        List<Movie> res = new ArrayList<>();
        elasticSearch.prepareSearch().setTypes(USER_TYPE)
                     .setQuery(QueryBuilders.wildcardQuery("title", "*" + request.getTerm() + "*"))
                     .setSize(20)
                     .get()
                     .getHits().forEach(hit -> res.add(User.fromJson(hit.getSourceAsString().getBytes(), Movie.class)));
        return res;
    }

    @Override
    public String getIndex() {
        return "movieindex";
    }
}
