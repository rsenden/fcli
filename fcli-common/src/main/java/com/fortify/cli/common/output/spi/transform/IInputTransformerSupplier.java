package com.fortify.cli.common.output.spi.transform;

import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.JsonNode;

public interface IInputTransformerSupplier {
    UnaryOperator<JsonNode> getInputTransformer();
}
