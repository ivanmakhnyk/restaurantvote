package org.imakhnyk.interview.menuvoting.frontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.concurrent.atomic.AtomicLong;

import org.imakhnyk.interview.menuvoting.frontend.model.EchoTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController should be used to test access to application
 * 
 * @author Ivan Makhnyk
 *
 */
@Api(value = "/echo", description = "can be used to test access")
@RestController
public class TestController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@ApiOperation(value = "Call to test access")
	@RequestMapping(value = "/echo", method = RequestMethod.GET)
	public EchoTest echo(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return new EchoTest(counter.incrementAndGet(), String.format(template,
				name));
	}
}