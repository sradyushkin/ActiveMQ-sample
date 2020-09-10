package ru.services;

import java.util.Collections;
import java.util.List;

import ru.consumers.MessageConsumer;
import ru.dto.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private MessageConsumer messageConsumer;

    public List<News> getLastNews() {
        List<News> news = messageConsumer.receiveAllFromQueue();
        if (news != null) {
            return news;
        } else {
            return Collections.emptyList();
        }
    }
}