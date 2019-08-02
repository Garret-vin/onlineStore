package com.dao;

import com.model.Code;
import com.model.User;

import java.util.Optional;

public interface CodeDao {

    void add(Code code);

    Optional<Code> getById(Long id);

    Optional<Code> getLastCodeForUser(User user);
}
