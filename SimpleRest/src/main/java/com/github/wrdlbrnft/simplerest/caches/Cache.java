package com.github.wrdlbrnft.simplerest.caches;

/**
 * Created by kapeller on 08/01/16.
 */
public interface Cache<K, T> {
    void put(K key, T item);
    T get(K key);
    void evict(K key);
    void clear();
}
