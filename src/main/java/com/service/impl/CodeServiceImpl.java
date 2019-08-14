package com.service.impl;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import com.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeDao codeDao;

    @Autowired
    public CodeServiceImpl(CodeDao codeDao) {
        this.codeDao = codeDao;
    }

    @Override
    @Transactional
    public void add(Code code) {
        codeDao.add(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Code> getById(Long id) {
        return codeDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Code> getLastCodeForUser(User user) {
        return codeDao.getLastCodeForUser(user);
    }
}
