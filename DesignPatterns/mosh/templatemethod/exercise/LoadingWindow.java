package com.dogigiri.designpatterns.mosh.templatemethod.exercise;

import org.slf4j.LoggerFactory;

public class LoadingWindow extends Window {
    @Override
    protected void onClosing() {
        LoggerFactory.getLogger(LoadingWindow.class).info("saving state");
    }

    @Override
    protected void onClosed() {
        LoggerFactory.getLogger(LoadingWindow.class).info("removing loading window");
    }
}
