package com.dogigiri.designpatterns.guru.patterns.creational.factory.factorymethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsiGpu implements Gpu {
    @Override
    public void assemble() {
        log.info("MSI gpu assembled");
    }
}
