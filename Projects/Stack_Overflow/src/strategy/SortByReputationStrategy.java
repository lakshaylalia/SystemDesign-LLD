package strategy;

import models.Post;

import java.util.List;

public class SortByReputationStrategy<T extends Post> implements SortStrategy<T> {
    @Override
    public List<T> sort(List<T> items) {
        items.sort((a, b) -> Long.compare(
                b.getAuthor().getReputation(),
                a.getAuthor().getReputation()
        ));
        return items;
    }
}