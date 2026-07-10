package strategy;

import models.Post;

import java.util.List;

public class SortByVotesStrategy<T extends Post> implements SortStrategy<T> {
    @Override
    public List<T> sort(List<T> items) {
        items.sort((a, b) -> Long.compare(b.getUpvotes(), a.getUpvotes()));
        return items;
    }
}