package se.rj.govie.firebase.listeners.search;

import com.google.firebase.database.DataSnapshot;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import se.rj.govie.firebase.FirebaseAgent;
import se.rj.govie.firebase.RequestType;
import se.rj.govie.firebase.listeners.ChildEventAdapter;
import se.rj.govie.model.GovieObject;
import se.rj.govie.request.SearchRequest;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class SearchRequestListener<T extends SearchRequest, R extends GovieObject> extends ChildEventAdapter {

    private static Logger logger = Logger.getLogger(SearchRequestListener.class);

    @Autowired
    private FirebaseAgent firebaseAgent;

    private Class<T> requestClass;

    public SearchRequestListener() {
        findActualTypeArgs();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        logger.info("Executing search for " + requestClass.getSimpleName());
        dataSnapshot.getRef().removeValue();
        new Thread() {
            @Override
            public void run() {
                SearchRequest searchRequest = getSearchRequest(dataSnapshot);
                firebaseAgent.pushResponse(searchRequest.getUser(), getSearchType(), search(dataSnapshot));
            }
        }.start();
    }

    protected abstract RequestType getSearchType();

    protected abstract List<R> search(DataSnapshot dataSnapshot);

    protected T getSearchRequest(DataSnapshot dataSnapshot) {
        return SearchRequest.fromDataSnapshot(dataSnapshot, requestClass);
    }

    private void findActualTypeArgs() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.requestClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}
