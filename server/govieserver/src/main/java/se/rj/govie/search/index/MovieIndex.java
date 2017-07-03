package se.rj.govie.search.index;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Movie;
import se.rj.govie.request.SearchMovieRequest;
import se.rj.govie.search.ElasticSearchAgent;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieIndex extends Index<Movie> {

    private static Logger logger = Logger.getLogger(MovieIndex.class);

    @Autowired
    private ElasticSearchAgent elasticSearch;

    public List<Movie> searchByTitle(SearchMovieRequest request) {
        List<Movie> res = new ArrayList<>();
        elasticSearch.prepareSearch(getIndex()).setTypes(Movie.MOVIE_TYPE)
                     .setQuery(QueryBuilders.wildcardQuery("title", "*" + request.getTerm() + "*"))
                     .setSize(20)
                     .get()
                     .getHits().forEach(hit -> res.add(Movie.fromJson(hit.getSourceAsString().getBytes(), Movie.class)));
        return res;
    }

    @Override
    public String getIndex() {
        return "movieindex";
    }

    public Movie findById(String movieId) {
        List<Movie> res = new ArrayList<>();
        elasticSearch.prepareSearch(getIndex()).setTypes(Movie.MOVIE_TYPE)
                     .setQuery(QueryBuilders.idsQuery().addIds(movieId))
                     .setSize(1)
                     .get()
                     .getHits().forEach(hit -> res.add(Movie.fromJson(hit.getSourceAsString().getBytes(), Movie.class)));
        return res.get(0);
    }
}
