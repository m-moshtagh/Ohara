package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.app;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.VesselType;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service.DischargedLoadedService;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service.LoadedVesselService;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service.ReadyToLoadService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws UnsupportedOperationException {
        var vessel = new Vessel(1L, "Cappuccino32", VesselType.LOADED);
        Vessel operation1 = VesselEngine.getInstance().execute(new LoadedVesselService(), vessel);
        Logger.getLogger(Main.class.getName()).log(Level.INFO, operation1::toString);

        Vessel operation2 = VesselEngine.getInstance().execute(new DischargedLoadedService(), operation1);
        Logger.getLogger(Main.class.getName()).log(Level.INFO, operation2::toString);

        Vessel operation3 = VesselEngine.getInstance().execute(new ReadyToLoadService(), operation2);
        Logger.getLogger(Main.class.getName()).log(Level.INFO, operation3::toString);
    }
}
