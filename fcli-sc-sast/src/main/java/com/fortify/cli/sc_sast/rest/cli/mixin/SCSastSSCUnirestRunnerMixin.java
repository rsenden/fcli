package com.fortify.cli.sc_sast.rest.cli.mixin;

import java.util.function.BiFunction;

import com.fortify.cli.common.util.FixInjection;
import com.fortify.cli.sc_sast.session.manager.SCSastSessionData;

import io.micronaut.core.annotation.ReflectiveAccess;
import kong.unirest.UnirestInstance;

@ReflectiveAccess @FixInjection
public class SCSastSSCUnirestRunnerMixin extends AbstractSCSastUnirestRunnerMixin {
    @Override
    public <R> R run(BiFunction<UnirestInstance, SCSastSessionData, R> f) {
        return runOnSSC(f);
    }
}
