package com.github.wrdlbrnft.simplerest.connection.request;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class QueryParameterImpl implements QueryParameter {

    private final String mKey;
    private final String mValue;

    public QueryParameterImpl(String key, String value) {
        mKey = key;
        mValue = value;
    }

    @Override
    public String getKey() {
        return mKey;
    }

    @Override
    public String getValue() {
        return mValue;
    }
}
