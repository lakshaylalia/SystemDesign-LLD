package repository;

import models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PostRepository<T extends Post> {

    protected Map<Long, T> idToPost = new ConcurrentHashMap<>();

    public T save(T post) {
        idToPost.put(post.getId(), post);
        return post;
    }

    public T getById(Long id) {
        return idToPost.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(idToPost.values());
    }

    public T update(T post) {
        idToPost.put(post.getId(), post);
        return post;
    }

    public void deleteById(Long id) {
        idToPost.remove(id);
    }
}