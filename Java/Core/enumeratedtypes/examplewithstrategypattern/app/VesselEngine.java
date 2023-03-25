package com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.app;

import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.entity.Vessel;
import com.dogigiri.core.enumeratedtypes.examplewithstrategypattern.model.service.VesselService;

/**
 * In order to operate the vessel service base on vessel type using the strategy pattern
 *
 * @author mohammad moshtagh
 * @version 0.0.1-SNAPSHOT
 * @since 2022-AUG-09
 */
public class VesselEngine {
    private static VesselEngine vesselEngine = null;

    private VesselEngine() {
    }

    /**
     * Singleton pattern for creating only one instance from this stateless class.
     *
     * @return VesselEngine
     * If it is not initialized before, it will be initialized otherwise.
     */
    public static VesselEngine getInstance() {
        if (vesselEngine == null) vesselEngine = new VesselEngine();
        return vesselEngine;
    }

    /**
     * The vessel loading, discharging operations will be evaluated and done base on vesselType object
     *
     * @param vesselService vessel service base on the service we want to operate on vessel
     * @param vessel        vessel which the operation will be done on depending on its type status
     * @return a new instance of vessel with fresh status if the operation is successful
     * @throws UnsupportedOperationException If the operation can not be done base on the vessel type this will be
     *                                       thrown
     */
    public Vessel execute(VesselService vesselService, Vessel vessel) throws UnsupportedOperationException {
        return vesselService.operate(vessel);
    }
}