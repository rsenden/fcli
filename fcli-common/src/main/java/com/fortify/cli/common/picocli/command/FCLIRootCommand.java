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
package com.fortify.cli.common.picocli.command;

import com.fortify.cli.common.picocli.component.log.LogOptionsHandler;

import io.micronaut.core.annotation.ReflectiveAccess;
import jakarta.inject.Singleton;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.ScopeType;

/**
 * This is the root command for the fcli application, defining common properties
 * like help options mixin and help footer that will be inherited by all
 * sub-commands. Other than that, it also includes the {@link LogOptionsHandler}
 * to avoid picocli from complaining about not recognizing those options.
 * Actual logging setup will already have been completed before this command
 * is even loaded, so the logging options themselves are not being processed here. 
 * 
 * @author Ruud Senden
 */
@Singleton
@ReflectiveAccess
@Command(name = "fcli", 
	scope = ScopeType.INHERIT, 
	mixinStandardHelpOptions = true,
	usageHelpAutoWidth = true,
	sortOptions = false,
	footer = "%n(c) Copyright 2021 Micro Focus", 
	showAtFileInUsageHelp = false,
    optionListHeading = "%nGeneric options:%n", // This is supposed to hold only standard help, version, and logging options; command implementations should always use appropriate ArgGroups with headings
	description = "Command-line interface for working with various Fortify products")
public class FCLIRootCommand {
	// Setting up logging is handled in the main class by a separate Picocli instance, to allow
	// for setting up logging early in the process. In order to have our main command structure
	// not complain about any logging options, we define them here even though we don't actually
	// do anything with these options here.
	@Mixin LogOptionsHandler logOptions;
}
