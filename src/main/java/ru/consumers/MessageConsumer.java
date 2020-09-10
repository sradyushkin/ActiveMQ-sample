package ru.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import ru.dto.News;

import javax.jms.Message;

import java.util.ArrayList;
import java.util.List;

import static ru.config.ActiveMQConfig.ORDER_QUEUE;

@Component
public class MessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageConverter messageConverter;

    public List<News> receiveAllFromQueue() {
        try {
            return jmsTemplate.execute(session -> {
                try (final javax.jms.MessageConsumer consumer = session.createConsumer(session.createQueue(ORDER_QUEUE))) {
                    List<News> newsList = new ArrayList<>();
                    Message message;
                    while ((message = consumer.receive(2000)) != null) {
                        newsList.add((News) messageConverter.fromMessage(message));
                    }
                    return newsList;
                }
            }, true);
        } catch (JmsException ex) {
            logger.error("Error! Couldn't receive messages " + ex.getMessage());
            return null;
        }
    }
}