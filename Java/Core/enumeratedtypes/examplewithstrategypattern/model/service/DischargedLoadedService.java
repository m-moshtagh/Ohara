package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.VesselType;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DischargedLoadedService implements VesselService {
    @Override
    public Vessel operate(Vessel vessel) throws UnsupportedOperationException {
        if (Objects.equals(vessel.getVesselType().getStatus(), VesselType.DISCHARGED.getStatus())) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Vessel is discharged and is going to get ready");
            vessel.setVesselType(VesselType.READY_TO_LOAD);
            return vessel;
        }
        throw new UnsupportedOperationException();
    }
}
