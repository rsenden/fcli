package com.fortify.cli.ssc.appversion_attribute.cli.mixin;

import java.util.Map;

import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.Getter;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class SSCAppVersionAttributeUpdateMixin {
    private static final String PARAM_LABEL = "[CATEGORY:]ATTR=VALUE[,VALUE...]";
    @ReflectiveAccess
    public static abstract class AbstractSSCAppVersionAttributeUpdateMixin {
        public abstract Map<String,String> getAttributes();

    }
    
    @ReflectiveAccess
    public static class OptionalAttrOption extends AbstractSSCAppVersionAttributeUpdateMixin {
        @Option(names = {"--attr", "--attribute"}, required = false, arity="0..", paramLabel = PARAM_LABEL)
        @Getter private Map<String,String> attributes;
    }
    
    @ReflectiveAccess
    public static class RequiredPositionalParameter extends AbstractSSCAppVersionAttributeUpdateMixin {
        @Parameters(index = "0..*", arity = "1..*", paramLabel = PARAM_LABEL)
        @Getter private Map<String,String> attributes;
    }
    
    
}
