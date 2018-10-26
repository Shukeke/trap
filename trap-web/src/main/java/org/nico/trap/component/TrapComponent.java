package org.nico.trap.component;

import org.nico.trap.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrapComponent {

	@Value("${trap.grammer.process.url}")
	private String trapGrammerProcessUrl;

	public String trapGrammerProcess(String text) {
		String result = HttpUtils.post(trapGrammerProcessUrl, text);
		return result;
	}
}
