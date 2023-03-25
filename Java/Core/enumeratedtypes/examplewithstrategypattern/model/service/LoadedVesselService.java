package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.VesselType;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadedVesselService implements VesselService {
    @Override
    public Vessel operate(Vessel vessel) throws UnsupportedOperationException {
        if (Objects.equals(vessel.getVesselType().getStatus(), VesselType.LOADED.getStatus())) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Vessel is loaded and is going to get discharged");
            vessel.setVesselType(VesselType.DISCHARGED);
            return vessel;
        }
        throw new UnsupportedOperationException();
    }
}
