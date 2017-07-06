package com.tr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.model.Product;
import com.tr.repository.ProductRepository;
import com.tr.search.ElasticSearchAPI;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ElasticSearchAPI elasticSearchAPI;

	private String indexType = "product";

	@Override
	public void insertBulkDataToES(Client client) {
		List<Product> products = productRepository.findAll();
		List<Product> productsToInsert = new ArrayList<>();
		List<Product> productsToUpdate = new ArrayList<>();
		products.forEach(p -> {
			SearchResponse response = elasticSearchAPI.searchByQuery(client, indexType, p.getId().toString());
			SearchHit[] searchHit = response.getHits().getHits();
			if (searchHit != null && searchHit.length > 0) {
				p.setEsId(searchHit[0].getId());
				productsToUpdate.add(p);
			} else {
				productsToInsert.add(p);
			}
		});
		System.out.println(products.size() + " i " + productsToInsert.size() + " u " + productsToUpdate.size());
		BulkResponse bulkResponse = null;
		if (productsToInsert.size() > 0)
			bulkResponse = elasticSearchAPI.insertBulkData(client, indexType, productsToInsert.toArray());
		if (productsToUpdate.size() > 0)
			bulkResponse = elasticSearchAPI.updateBulkData(client, indexType, productsToUpdate);

		System.out.println("======================INSERT/UPDATE=======================");
		// bulkResponse.forEach(x -> System.out.println(x));
	}

	@Override
	public void searchData(Client client) {
		SearchResponse searchResponse = elasticSearchAPI.searchData(client, indexType);
		System.out.println("======================GET======================");
		if (searchResponse.getHits().getHits().length > 0) {
			for (SearchHit searchData : searchResponse.getHits().getHits()) {
				JSONObject value = new JSONObject(searchData.getSource());
				// System.out.println(value.toString());
			}
		}
	}

	@Override
	public void searchDataById(Client client) {
		System.out.println("Product id to search in ES: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		DateTime startTime = DateTime.now();
		GetResponse getResponse = elasticSearchAPI.searchDataById(client, indexType, id);
		System.out.println("Time taken for ES : " + (DateTime.now().get(DateTimeFieldType.millisOfDay())
				- startTime.get(DateTimeFieldType.millisOfDay())));
		System.out.println("======================GET BY ID======================");
		JSONObject value = new JSONObject(getResponse.getSource());
		System.out.println(value.toString());
	}

	@Override
	public void searchInDB(Client client) {
		Scanner scanner = new Scanner(System.in);
		DateTime startTime = DateTime.now();
		System.out.println("Product id to search in DB: ");
		Integer idInt = scanner.nextInt();
		startTime = DateTime.now();
		productRepository.findById(idInt);
		System.out.println("Time taken for DB : " + (DateTime.now().get(DateTimeFieldType.millisOfDay())
				- startTime.get(DateTimeFieldType.millisOfDay())));
	}

	@Override
	public void deleteDataById(Client client) {
		System.out.println("Product id to delete: ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();

		DeleteResponse deleteResponse = elasticSearchAPI.deleteSingleEntry(client, indexType, id);
		System.out.println("======================DELETE======================");
		System.out.println(deleteResponse.getId() + " deleted");
		searchDataById(client);
		System.out.println("================APPLICATION - SEARCH AFTER DELETE DONE=============");
	}

	@Override
	public void likeSearchInES(Client client) {
		System.out.println("Product id to do like-search in ES: ");
		Scanner scanner = new Scanner(System.in);
		String search = scanner.nextLine();
		DateTime startTime = DateTime.now();
		SearchResponse searchResponse = elasticSearchAPI.searchDataLike(client, indexType, search);
		System.out.println("Time taken for ES : " + (DateTime.now().get(DateTimeFieldType.millisOfDay())
				- startTime.get(DateTimeFieldType.millisOfDay())));
		System.out.println("======================GET BY ID======================");
		System.out.println(searchResponse.getHits().getHits().length);

	}

	@Override
	public void likeSearchInDB(Client client) {
		Scanner scanner = new Scanner(System.in);
		DateTime startTime = DateTime.now();
		System.out.println("Product id to do like-search in DB: ");
		String search = scanner.nextLine();
		startTime = DateTime.now();
		List<Product> products = productRepository.findByNameLike("%" + search + "%");
		System.out.println("Time taken for DB : " + (DateTime.now().get(DateTimeFieldType.millisOfDay())
				- startTime.get(DateTimeFieldType.millisOfDay())));
		System.out.println(products.size());
	}

}