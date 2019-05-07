package net.nenko.coco;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coco {
	private List<String> cfg = new ArrayList<>();
	private String covar = "";
	private char splitChar = '?';

	public Coco() {
	}

	public Coco(List<String> cfg, String covar) {
		this.cfg = cfg;
		this.covar = covar;
	}

	public String get(Map<String, String> dict) {
		if(cfg == null)		return "ERROR: no configuration defined !";
		if(cfg.isEmpty())	return "ERROR: empty configuration data !";
		String coval = dict.get(covar);
		for(String predicate: cfg) {
			int split = predicate.indexOf(splitChar);
			if(split == -1) {
				return substitute(predicate, dict);
			} else {
				if(coval != null && coval.equals(predicate.substring(0, split).trim())) {
					return substitute(predicate.substring(split + 1).trim(), dict);
				}
			}
		}
		return "ERROR: configuration failure for " + covar + '/' + coval;
	}

	private String substitute(String template, Map<String, String> dict) {
		for(Map.Entry<String, String> e: dict.entrySet()) {
			String key = "{" + e.getKey() + '}';
			String val = e.getValue() == null ? "" : e.getValue();
			template = template.replaceAll(Pattern.quote(key), Matcher.quoteReplacement(val));
		}
		return template;
	}

	//// Boilerplate crap

	public List<String> getCfg() {
		return cfg;
	}

	public void setCfg(List<String> cfg) {
		this.cfg = cfg;
	}

	public void setCovar(String covar) {
		this.covar = covar;
	}

	public void setSplitter(char splitter) {
		splitChar =  splitter;
	}

}
