package com.tr.service;

import org.elasticsearch.client.Client;

public interface ProductService {
	void insertBulkDataToES(Client client);

	void searchData(Client client);

	void searchDataById(Client client);

	void deleteDataById(Client client);

	void searchInDB(Client client);

	void likeSearchInES(Client client);

	void likeSearchInDB(Client client);
}