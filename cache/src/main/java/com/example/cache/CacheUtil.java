package com.example.cache;

import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
public class CacheUtil {
    private static LoadingCache<String, Properties> empCache;
    static {
	empCache = CacheBuilder.newBuilder()
	       .maximumSize(100)
	       .expireAfterWrite(10, TimeUnit.MINUTES)
	       .build(
	           new CacheLoader<String, Properties>() {
        		@Override
			public Properties load(String key)   {
				return getallProperties();
			}
	           }
	       );
    }
    public static LoadingCache<String, Properties> getLoadingCache() {
	return empCache;
    }
    public static Properties getallProperties() {

	Properties emp1 = new Properties("filePath", "C:\\\\Users\\\\rajendra.Patil\\\\Downloads\\\\cache (1)\\\\cache\\\\src\\\\main\\\\resources\\\\poc\\\\schema-07.json");
	return emp1;
	
   }
}