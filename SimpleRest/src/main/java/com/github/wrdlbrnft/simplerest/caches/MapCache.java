package com.github.wrdlbrnft.simplerest.caches;

import android.support.v4.util.ArrayMap;

import java.util.Collections;
import java.util.Map;

/**
 * Created by kapeller on 08/01/16.
 */
public class MapCache<K, T> implements Cache<K, T> {

    private final Map<K, T> mMap = Collections.synchronizedMap(new ArrayMap<K, T>());

    @Override
    public void put(K key, T item) {
        mMap.put(key, item);
    }

    @Override
    public T get(K key) {
        return mMap.get(key);
    }

    @Override
    public void evict(K key) {
        mMap.remove(key);
    }

    @Override
    public void clear() {
        mMap.clear();
    }
}
