package se.rj.govie.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.rj.govie.request.RateRequest;
import se.rj.govie.search.index.UserIndex;

import java.util.Date;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static se.rj.govie.model.EventType.mention;

@Component
public class EventFactory {

    @Autowired
    private UserIndex userIndex;

    public List<GovieEvent> fromRateReuqest(RateRequest rateRequest) {
        return rateRequest.getFriends().stream().map(uid -> {
            String rater = rateRequest.getUid();
            User user = userIndex.searchByIds(singletonList(rater)).get(0);
            return new GovieEvent(uid, user.getName(), user.getAvatarUrl(), mention, new Date());
        }).collect(toList());
    }
}
