package com.github.wrdlbrnft.simplerest.util;

import android.text.TextUtils;

import com.github.wrdlbrnft.simplerest.SimpleRestConfig;
import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kapeller on 02/06/16.
 */
public class LogUtils {

    public interface Block {
        Block append(String text);
        Block newLine();
        String build();
    }

    public static Block newBlock() {
        return new BlockImpl();
    }

    public static void logRequest(Block block, Request request) {
        if (SimpleRestConfig.Debug.ENABLED) {
            if (SimpleRestConfig.Debug.SHOW_HEADERS) {
                final Map<String, String> headers = request.getHeaders();
                final Set<String> keySet = headers.keySet();
                block.append("\t" + keySet.size() + " Headers: ").newLine();
                for (String key : keySet) {
                    final String value = headers.get(key);
                    block.append("\t- " + key + ": " + value).newLine();
                }
                block.newLine();
            }
            block.append(request.getMethod() + " " + request.getRelativeUrl()).newLine();
            final String data = request.getData();
            if (!TextUtils.isEmpty(data)) {
                block.append(data).newLine();
            }
        }
    }

    public static void logResponse(Block block, Response response) {
        if (SimpleRestConfig.Debug.ENABLED) {
            block.append("--> " + response.getStatus()).newLine();
            if (SimpleRestConfig.Debug.SHOW_HEADERS) {
                final Map<String, String> headers = response.getHeaders();
                final Set<String> keySet = headers.keySet();
                block.append("\t" + keySet.size() + " Headers: ").newLine();
                for (String key : keySet) {
                    final String value = headers.get(key);
                    block.append("\t- " + key + ": " + value).newLine();
                }
                block.newLine();
            }

            final String data = response.getData();
            if (!TextUtils.isEmpty(data)) {
                block.append(data).newLine();
            }

        }
    }

    private static class BlockImpl implements Block {

        private final List<StringBuilder> mLines = new ArrayList<>();
        private StringBuilder mCurrent;

        public BlockImpl() {
            newLine();
        }

        @Override
        public Block append(String text) {
            mCurrent.append(text);
            return this;
        }

        @Override
        public Block newLine() {
            mCurrent = new StringBuilder();
            mLines.add(mCurrent);
            return this;
        }

        @Override
        public String build() {
            final StringBuilder builder = new StringBuilder();

            final int maxLineLength = determineMaxLineLength();
            final int blockLength = maxLineLength + 4;

            appendRepeatedChar(builder, '#', blockLength);
            builder.append("\n");
            for (int i = 0, count = mLines.size(); i < count; i++) {
                final StringBuilder line = mLines.get(i);

                if(i > 0) {
                    builder.append("\n");
                }

                builder.append("# ");
                final int length = line.length();
                final int differenceToMaxLength = maxLineLength - length;
                builder.append(line);
                appendRepeatedChar(builder, ' ', differenceToMaxLength);
                builder.append(" #");
            }
            builder.append("\n");
            appendRepeatedChar(builder, '#', blockLength);

            return builder.toString();
        }

        private static void appendRepeatedChar(StringBuilder builder, char c, int count) {
            for (int i = 0; i < count; i++) {
                builder.append(c);
            }
        }

        private int determineMaxLineLength() {
            int maxLineLength = 0;
            for (StringBuilder line : mLines) {
                final int length = line.length();
                if (length > maxLineLength) {
                    maxLineLength = length;
                }
            }
            return maxLineLength;
        }
    }
}
