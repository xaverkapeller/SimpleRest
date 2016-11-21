package com.github.wrdlbrnft.simplerest.certificates;

import android.content.Context;
import android.content.res.Resources;

import java.io.InputStream;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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

