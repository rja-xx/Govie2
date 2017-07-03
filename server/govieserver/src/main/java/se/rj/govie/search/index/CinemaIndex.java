package se.rj.govie.search.index;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.model.Cinema;
import se.rj.govie.search.ElasticSearchAgent;

import java.util.ArrayList;
import java.util.List;

import static se.rj.govie.model.Cinema.CINEMA_TYPE;

@Component
public class CinemaIndex extends Index<Cinema> {

    private static Logger logger = Logger.getLogger(CinemaIndex.class);

    @Autowired
    private ElasticSearchAgent elasticSearch;

    public Cinema findById(String id) {
        List<Cinema> res = new ArrayList<>();
        elasticSearch.prepareSearch(getIndex()).setTypes(CINEMA_TYPE)
                     .setQuery(QueryBuilders.commonTermsQuery("id", id))
                     .setSize(1)
                     .get()
                     .getHits().forEach(hit -> res.add(Cinema.fromJson(hit.getSourceAsString().getBytes(), Cinema.class)));
        return res.get(0);
    }

    public List<Cinema> searchByName(String term) {
        List<Cinema> res = new ArrayList<>();
        elasticSearch.prepareSearch(getIndex()).setTypes(CINEMA_TYPE)
                     .setQuery(QueryBuilders.wildcardQuery("name", "*" + term + "*"))
                     .setSize(20)
                     .get()
                     .getHits().forEach(hit -> res.add(Cinema.fromJson(hit.getSourceAsString().getBytes(), Cinema.class)));
        return res;
    }

    @Override
    public String getIndex() {
        return "cinemaindex";
    }
}
