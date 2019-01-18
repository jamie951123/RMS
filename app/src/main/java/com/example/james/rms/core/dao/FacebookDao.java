package com.example.james.rms.core.dao;

import com.example.james.rms.core.model.Facebook;

import java.util.List;

/**
 * Created by Jamie on 11/7/2017.
 */

public interface FacebookDao {
    List<Facebook> findAll();
    Facebook findByFacebookId(String facebookSearch_json);
    Integer countFacebookId(String facebookSearch_json);
}
