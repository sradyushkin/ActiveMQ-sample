package ru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.dto.News;
import ru.producers.MessageProducer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private MessageProducer messageProducer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        List<News> newsList = new ArrayList<>();
        News news = new News(1, new Date(), "title1", "some news1 body");
        News news1 = new News(2, new Date(), "title2", "some news2 body");
        News news2 = new News(3, new Date(), "title3", "some news3 body");

        newsList.add(news);
        newsList.add(news1);
        newsList.add(news2);

        newsList.forEach(messageProducer::send);
    }
}