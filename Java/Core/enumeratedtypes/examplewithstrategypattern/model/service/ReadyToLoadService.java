package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.VesselType;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadyToLoadService implements VesselService {
    @Override
    public Vessel operate(Vessel vessel) throws UnsupportedOperationException {
        if (Objects.equals(vessel.getVesselType().getStatus(), VesselType.READY_TO_LOAD.getStatus())) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Vessel is ready and is going to get loaded");
            vessel.setVesselType(VesselType.LOADED);
            return vessel;
        }
        throw new UnsupportedOperationException();
    }
}
