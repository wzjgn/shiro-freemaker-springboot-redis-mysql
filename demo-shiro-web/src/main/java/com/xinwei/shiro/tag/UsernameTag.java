package com.xinwei.shiro.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class UsernameTag extends SecureTag {
	static final Logger log = Logger.getLogger("UsernameTag");

	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
		String result = null;

		if (getSubject() != null) {
			PrincipalCollection pcpls = getSubject().getPrincipals();
			Set<String> set = pcpls.getRealmNames();
			Iterator<String> it = set.iterator();
			if (it.hasNext()) {
				result = it.next();
			}
		}
		// Print out the principal value if not null
		if (result != null) {
			try {
				env.getOut().write(result);
			} catch (IOException ex) {
				throw new TemplateException("Error writing [" + result + "] to Freemarker.", ex, env);
			}
		}
	}
}