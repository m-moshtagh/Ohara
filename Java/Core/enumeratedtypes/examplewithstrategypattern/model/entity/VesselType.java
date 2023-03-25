package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity;

public enum VesselType {
    READY_TO_LOAD(85_356L),
    LOADED(85_357L),
    DISCHARGED(85_358L);

    private final Long vesselStatus;

    VesselType(Long statusId) {
        this.vesselStatus = statusId;
    }

    public Long getStatus() {
        return this.vesselStatus;
    }
}
