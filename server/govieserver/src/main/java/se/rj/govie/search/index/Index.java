package se.rj.govie.search.index;

import org.springframework.beans.factory.annotation.Autowired;
import se.rj.govie.model.IndexableObject;
import se.rj.govie.search.ElasticSearchAgent;

public abstract class Index<T extends IndexableObject> {

    @Autowired
    private ElasticSearchAgent elasticSearchAgent;

    public void add(T indexableObject) {
        elasticSearchAgent.addToIndex(this, indexableObject);
    }

    public abstract String getIndex();

    public void clear(){
        elasticSearchAgent.clear(this);
    }
}
