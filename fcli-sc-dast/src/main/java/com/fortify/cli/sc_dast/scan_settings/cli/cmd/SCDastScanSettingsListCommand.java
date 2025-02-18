/*******************************************************************************
 * (c) Copyright 2021 Micro Focus or one of its affiliates
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.cli.sc_dast.scan_settings.cli.cmd;

import com.fortify.cli.common.output.cli.cmd.unirest.IUnirestBaseRequestSupplier;
import com.fortify.cli.common.output.query.OutputQueryHelper;
import com.fortify.cli.common.output.query.OutputQueryOperator;
import com.fortify.cli.common.util.StringUtils;
import com.fortify.cli.sc_dast.output.cli.cmd.AbstractSCDastOutputCommand;
import com.fortify.cli.sc_dast.output.cli.mixin.SCDastOutputHelperMixins;
import com.fortify.cli.sc_dast.rest.cli.mixin.SCDastSearchTextMixin;

import io.micronaut.core.annotation.ReflectiveAccess;
import kong.unirest.HttpRequest;
import kong.unirest.UnirestInstance;
import lombok.Getter;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

@ReflectiveAccess
@Command(name = SCDastOutputHelperMixins.List.CMD_NAME)
public class SCDastScanSettingsListCommand extends AbstractSCDastOutputCommand implements IUnirestBaseRequestSupplier {
    @Getter @Mixin private SCDastOutputHelperMixins.List outputHelper;
    @Mixin SCDastSearchTextMixin searchTextMixin;
    // TODO Once we support date-based less-than/greater-than operators for -q,
    //      deprecate these options and update #updateRequest() to get the appropriate
    //      query values.
    @Option(names = {"-a", "--modified-after"}) private String modifiedAfter;
    @Option(names = {"-b", "--modified-before"}) private String modifiedBefore;
    
    public HttpRequest<?> getBaseRequest(UnirestInstance unirest) {
        return updateRequest(
             unirest.get("/api/v2/application-version-scan-settings/scan-settings-summary-list")
        );
    };

    private HttpRequest<?> updateRequest(HttpRequest<?> request) {
        OutputQueryHelper queryHelper = new OutputQueryHelper(outputHelper);
        request = searchTextMixin.updateRequest(request);
        request = StringUtils.isBlank(modifiedAfter) 
                ? request
                : request.queryString("modifiedStartDate", modifiedAfter);
        request = StringUtils.isBlank(modifiedBefore) 
                ? request
                : request.queryString("modifiedEndDate", modifiedBefore);
        String scanType = queryHelper.getQueryValue("scanType", OutputQueryOperator.EQUALS);
        request = StringUtils.isBlank(scanType) 
                ? request
                : request.queryString("scanType", scanType);
        return request;
    }
    
    @Override
    public boolean isSingular() {
        return false;
    }
}
