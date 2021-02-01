package com.example.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.google.common.cache.LoadingCache;

@SpringBootApplication
@EnableCaching
public class CacheApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
		try {
			System.out.println(CacheApplication.getProperties("name").getValue());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Properties getProperties(String key) throws ExecutionException {
		LoadingCache<String, Properties> empCache = CacheUtil.getLoadingCache();
		//System.out.println(empCache.stats());
		System.out.println(empCache.get(key));
		return empCache.get(key);
	}
}
