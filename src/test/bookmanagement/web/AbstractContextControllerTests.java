package bookmanagement.web;

import bookmanagement.config.RootConfig;
import bookmanagement.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class,WebConfig.class})
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;
}