package com.vitecsoftware.app;

public enum EdgeType {
    INWARD((byte) -1),
    OUTWARD((byte) 1),
    STRAIGHT((byte) 0);

    final byte value;

    EdgeType(byte value) {
        this.value = value;
    }
}
