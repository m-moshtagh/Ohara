package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsiMotherBoard implements MotherBoard {
    @Override
    public void assemble() {
        log.info("Msi motherboard assembled");
    }
}
