package strategy;

import models.Post;

import java.util.List;

public class SortByDateStrategy<T extends Post> implements SortStrategy<T> {
    @Override
    public List<T> sort(List<T> items) {
        items.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return items;
    }
}
