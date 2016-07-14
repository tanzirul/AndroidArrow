package org.ucr.androidarrow;

import java.util.LinkedHashMap;
import java.util.Map;

public class Mapped {
	String _filePath;
	Map <String, String> _map=new LinkedHashMap<String, String>();
	
	IUtilities.mode _mode;
	
	void setFilePath(String filePath)
	{
		_filePath=filePath;
	}
	
	
	
	void setMode(IUtilities.mode m)
	{
		_mode=m;
	}
	
	void addData(String key, String val)
	{
		_map.put(key, val);
	}
	
	void dump()
	{
		
	}
	
	void save(String fileToSave)
	{
		
	}
	
}
