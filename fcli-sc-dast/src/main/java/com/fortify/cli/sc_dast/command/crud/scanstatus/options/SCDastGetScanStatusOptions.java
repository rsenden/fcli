package com.fortify.cli.sc_dast.command.crud.scanstatus.options;

import io.micronaut.core.annotation.ReflectiveAccess;
import lombok.Getter;
import picocli.CommandLine.Option;

/**
 * Configure options for retrieving SC DAST Scan settings list
 *
 * @author Ruud Senden
 */
@ReflectiveAccess
public class SCDastGetScanStatusOptions {

    @Option(names = {"-i","--id", "--scan-id"}, description = "The scan id")
    @Getter private int scanId;


}
