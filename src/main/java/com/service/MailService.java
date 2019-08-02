package com.service;

import com.model.Order;

public interface MailService {

    void sendConfirmCode(Order order);
}
