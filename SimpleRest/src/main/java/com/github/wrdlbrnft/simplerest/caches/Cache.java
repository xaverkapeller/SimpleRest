package com.github.wrdlbrnft.simplerest.caches;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Cache<K, T> {
    void put(K key, T item);
    T get(K key);
    void evict(K key);
    void clear();
}
