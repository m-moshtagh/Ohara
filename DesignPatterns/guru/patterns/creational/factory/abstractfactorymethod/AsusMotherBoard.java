package com.dogigiri.designpatterns.guru.patterns.creational.factory.abstractfactorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsusMotherBoard implements MotherBoard {
    @Override
    public void assemble() {
        log.info("ASUS motherboard assembled");
    }
}
