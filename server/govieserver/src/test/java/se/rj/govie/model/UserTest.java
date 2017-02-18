package se.rj.govie.model;

import com.google.firebase.database.DataSnapshot;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserTest {
    @Mock
    private DataSnapshot dataSnapshot;

    @BeforeMethod
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void givenUser_whenMapping_thenUserIsMapped() throws Exception {
        User u1 = new User("id", "sdkjsdf", "sdkjfhsdkjfsd", "sdfjkhsdfhusdufs");
        byte[] json = u1.toJson();
        User u2 = User.fromJson(json, User.class);
        Assert.assertEquals(u1, u2);
    }

    @Test
    public void givenUserHashMap_whenMapping_thenUserIsProduced() throws Exception {
        User u1 = new User("id", "sdkjsdf", "sdkjfhsdkjfsd", "sdfjkhsdfhusdufs");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", u1.getId());
        map.put("uid", u1.getUid());
        map.put("avatarUrl", u1.getAvatarUrl());
        map.put("name", u1.getName());
        when(dataSnapshot.getValue()).thenReturn(map);
        User u2 = User.fromDataSnapshot(dataSnapshot, User.class);
        Assert.assertEquals(u1, u2);
    }
}

