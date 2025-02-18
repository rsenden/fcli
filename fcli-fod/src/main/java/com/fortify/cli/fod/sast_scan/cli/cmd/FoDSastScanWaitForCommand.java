package com.fortify.cli.fod.sast_scan.cli.cmd;

import com.fortify.cli.common.output.cli.mixin.BasicOutputHelperMixins;
import com.fortify.cli.fod.scan.cli.cmd.FoDScanWaitForCommand;

import io.micronaut.core.annotation.ReflectiveAccess;
import picocli.CommandLine.Command;

//TODO See comments in FoDDastScanCancelCommand
@ReflectiveAccess
@Command(name = BasicOutputHelperMixins.WaitFor.CMD_NAME)
public class FoDSastScanWaitForCommand extends FoDScanWaitForCommand {
}
