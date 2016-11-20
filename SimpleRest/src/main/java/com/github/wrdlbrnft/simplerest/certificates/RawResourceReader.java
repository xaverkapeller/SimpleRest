package com.github.wrdlbrnft.simplerest.certificates;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;

/**
 * Created by kapeller on 22/05/15.
 */
public class RawResourceReader {

    private final Resources mResources;
    private final String mPackageName;

    public RawResourceReader(Context context) {
        mResources = context.getResources();
        mPackageName = context.getPackageName();
    }

    public InputStream openStream(String fileName) {
        final int resourceId = getResourceIdFromFileName(fileName);
        return getStreamFromResourceId(resourceId);
    }

    private int getResourceIdFromFileName(String fileName) {
        return mResources.getIdentifier(fileName, "raw", mPackageName);
    }

    private InputStream getStreamFromResourceId(int resourceId) {
        return mResources.openRawResource(resourceId);
    }
}

