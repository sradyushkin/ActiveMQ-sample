package ru.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import ru.dto.News;

import static ru.config.ActiveMQConfig.ORDER_QUEUE;

@Component
public class MessageProducer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(News news) {
        logger.info("sending news to queue with id: <" + news.getId() + ">");
        try {
            jmsTemplate.convertAndSend(ORDER_QUEUE, news);
        } catch (JmsException ex) {
            logger.error("Error! Couldn't send messages " + ex.getMessage());
        }
    }
}