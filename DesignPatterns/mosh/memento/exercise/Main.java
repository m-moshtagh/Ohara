package com.dogigiri.designpatterns.mosh.memento.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var history = new History<DocumentMemento>();
        var doc = new Document().setContent("Ibrahim").setFontName("BNarges").setFontSize(17);
        history.push(doc.createMemento());
        LOGGER.info("{}", doc);

        doc.setFontSize(15);
        history.push(doc.createMemento());
        LOGGER.info("{}", doc);

        doc.restore(history.pop());
        LOGGER.info("{}", doc);

        doc.setFontName("BNazanin");
        history.push(doc.createMemento());
        LOGGER.info("{}", doc);

        doc.restore(history.pop());
        LOGGER.info("{}", doc);

        doc.restore(history.pop());
        LOGGER.info("{}", doc);
    }
}
