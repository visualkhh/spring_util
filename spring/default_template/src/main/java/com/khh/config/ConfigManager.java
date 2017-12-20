package com.khh.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.omnicns.java.property.PropertyUtil;
import com.omnicns.java.string.StringUtil;
import com.omnicns.java.xml.XMLparser;


public class ConfigManager{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	static ConfigManager instance = null;
	Map<String,String> param = null;
	String configVarName = null;//"of.config.path";
	
	
	static public ConfigManager getInstance(){
        if(instance==null)
            instance = new ConfigManager();
        return instance;
    }
	private ConfigManager() {
		this.param = new HashMap<String, String>();
	}
	public Map<String, String> getParam() {
		return param;
	}

	public void setParam(Map<String, String> param) {
		this.param = param;
		loadSetConfigFile();
	}
	
	public void loadSetConfigFile(){
		if(null!=getConfigVarName()&&getConfigVarName().length()>0){
			final String configPath = PropertyUtil.getProperty(getConfigVarName());
			if(null!=configPath&&configPath.length()>0){
				try {
					FileSystemResource file = new FileSystemResource(configPath);
					XMLparser xml = new XMLparser(file.getFile());
					NodeList nodelist = xml.getNodes("//bean[@id='configManager']/property[@name='putParam']/map/entry");
					for(int i=0 ; i<nodelist.getLength() ; i++){
						Node atNode = nodelist.item(i);
						Map<String,String> at = xml.getAttribute(atNode);
						String key = at.get("key");
						String value = at.get("value");
						putParam(key, value);
					}
					xml.close();
					
				} catch (SAXException e) {
					log.error("import config file load fail",e);
				} catch (IOException e) {
					log.error("import config file load fail",e);
				} catch (XPathExpressionException e) {
					log.error("import config file load fail",e);
				} catch (NoClassDefFoundError e) {
					log.error("import config file load fail",e);
				}
			}
		}
	}
	public void putParam(String key, String value) {
		param.put(key, value);
	}
	public void putParam(Entry<String,String> param) {
		this.param.put(param.getKey(), param.getValue());
	}
	public void putParam(HashMap<String, String> param) {
		param.entrySet().forEach(at->{putParam(at);});
	}
	public String getParam(String name){
		return getParam().get(name);
	}
	
	public String getConfigVarName() {
		return configVarName;
	}
	public void setConfigVarName(String configVarName) {
		this.configVarName = configVarName;
	}
	public String getBindParam(String name) {
		String paramStr = getParam(name);
		Map<String,String> env = PropertyUtil.getSystemEnv();
		HashMap<String, String> map = new HashMap<String, String>();
        for (String envName : env.keySet()) {
        	map.put("env:"+envName, env.get(envName));
        }
        Map<String,String> param = getParam();
        for (String envName : param.keySet()) {
        	map.put("param:"+envName, param.get(envName));
        }
		return StringUtil.inJection(paramStr,map);
	}
}

	
	
	
	
	
	
