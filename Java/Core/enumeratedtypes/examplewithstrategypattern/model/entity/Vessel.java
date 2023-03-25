package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity;

import java.util.UUID;

public class Vessel {
    private final UUID uuid;
    private Long id;
    private String vesselName;
    private VesselType vesselType;

    public Vessel() {
        uuid = UUID.randomUUID();
    }

    public Vessel(Long id, String vesselName, VesselType vesselType) {
        uuid = UUID.randomUUID();
        this.id = id;
        this.vesselName = vesselName;
        this.vesselType = vesselType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public Vessel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getVesselName() {
        return vesselName;
    }

    public Vessel setVesselName(String vesselName) {
        this.vesselName = vesselName;
        return this;
    }

    public VesselType getVesselType() {
        return vesselType;
    }

    public Vessel setVesselType(VesselType vesselType) {
        this.vesselType = vesselType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vessel vessel = (Vessel) o;

        if (!getUuid().equals(vessel.getUuid())) return false;
        return getId().equals(vessel.getId());
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vessel{" +
                "uuid=" + uuid +
                ", id=" + id +
                ", vesselName='" + vesselName + '\'' +
                ", vesselType=" + vesselType +
                '}';
    }
}
