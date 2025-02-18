package com.fortify.cli.ssc.rest.cli.mixin;

import com.fortify.cli.common.rest.cli.mixin.AbstractSimpleUnirestRunnerMixin;
import com.fortify.cli.ssc.session.manager.ISSCSessionData;
import com.fortify.cli.ssc.session.manager.SSCSessionDataManager;
import com.fortify.cli.ssc.token.helper.SSCTokenHelper;

import io.micronaut.core.annotation.ReflectiveAccess;
import jakarta.inject.Inject;
import kong.unirest.UnirestInstance;

@ReflectiveAccess
public class SSCUnirestRunnerMixin extends AbstractSimpleUnirestRunnerMixin<ISSCSessionData> {
    @Inject private SSCSessionDataManager sessionDataManager;
    @Inject private SSCTokenHelper tokenHelper;

    @Override
    protected final ISSCSessionData getSessionData(String sessionName) {
        return sessionDataManager.get(sessionName, true);
    }
    
    @Override
    protected void configure(UnirestInstance unirest, ISSCSessionData sessionData) {
        SSCTokenHelper.configureUnirest(unirest, sessionData.getUrlConfig(), sessionData.getActiveToken());
    }
}
