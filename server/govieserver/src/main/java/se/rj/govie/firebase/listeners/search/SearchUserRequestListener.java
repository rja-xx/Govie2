package se.rj.govie.firebase.listeners.search;

import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.firebase.RequestType;
import se.rj.govie.model.User;
import se.rj.govie.request.SearchUserRequest;
import se.rj.govie.search.index.UserIndex;

import java.util.List;

@Component
public class SearchUserRequestListener extends SearchRequestListener<SearchUserRequest, User> {

    @Autowired
    private UserIndex userIndex;

    @Override
    protected RequestType getSearchType() {
        return RequestType.USER_SEARCH;
    }

    @Override
    protected List<User> search(DataSnapshot dataSnapshot) {
        return userIndex.searchByName(getSearchRequest(dataSnapshot));
    }
}
