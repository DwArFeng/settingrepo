package com.dwarfeng.settingrepo.impl.util;

/**
 * FTP 常量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public final class FtpConstants {

    public static final String[] PATH_IMAGE_NODE_FILE = new String[]{
            "settingrepo", "image-node-file"
    };

    public static final String[] PATH_IMAGE_NODE_THUMBNAIL = new String[]{
            "settingrepo", "image-node-thumbnail"
    };

    public static final String[] PATH_IMAGE_LIST_NODE_FILE = new String[]{
            "settingrepo", "image-list-node-file"
    };

    public static final String[] PATH_IMAGE_LIST_NODE_THUMBNAIL = new String[]{
            "settingrepo", "image-list-node-thumbnail"
    };

    private FtpConstants() {
        throw new IllegalStateException("禁止实例化");
    }
}
