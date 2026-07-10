package strategy;

import models.Post;

import java.util.List;

public interface SortStrategy<T extends Post> {
    List<T> sort(List<T> items);
}
