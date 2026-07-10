package services;

import models.Post;
import strategy.SortByDateStrategy;
import strategy.SortStrategy;

import java.util.List;

public class PostSortService<T extends Post> {
    private SortStrategy<T> strategy = new SortByDateStrategy<>();

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public List<T> sort(List<T> items) {
        return strategy.sort(items);
    }
}