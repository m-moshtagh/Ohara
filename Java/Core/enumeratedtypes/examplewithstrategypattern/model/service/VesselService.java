package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;

public interface VesselService {
    Vessel operate(Vessel vessel) throws UnsupportedOperationException;
}
