package com.tr;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.tr.service.ProductService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ElasticsearchOperations es;

	@Autowired
	private ProductService productService;

	@Value("${elasticsearch.indexname}")
	private String EsIndexName;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Client client = printElasticSearchInfo();
		// client.admin().indices().prepareCreate(EsIndexName).get();// run only
		// once

		productService.insertBulkDataToES(client);
		System.out.println("================APPLICATION - INSERT DONE=============");
		productService.searchData(client);
		System.out.println("================APPLICATION - SEARCH DONE=============");
		productService.searchDataById(client);
		System.out.println("================APPLICATION - SEARCH BY ID DONE=============");
		productService.deleteDataById(client);
		System.out.println("================APPLICATION - DELETE DONE=============");

	}

	private Client printElasticSearchInfo() {

		System.out.println("--ElasticSearch-->");
		Client client = es.getClient();
		Map<String, String> asMap = client.settings().getAsMap();

		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("<--ElasticSearch--");
		return client;
	}
}