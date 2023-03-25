package com.dogigiri.designpatterns.mosh.templatemethod.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Window {
    private static final Logger LOGGER = LoggerFactory.getLogger(Window.class);

    public void close() {
        LOGGER.info("Removing the window from the screen");
        onClosing();
        onClosed();
    }

    protected abstract void onClosing();

    protected abstract void onClosed();
}
