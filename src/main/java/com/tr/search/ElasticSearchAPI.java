package com.tr.search;

import java.util.List;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;

import com.tr.model.Product;

public interface ElasticSearchAPI {

	IndexResponse insertSingleData(Client client, String indexType, String data, String id);

	SearchResponse searchData(Client client, String indexType);

	<T> BulkResponse insertBulkData(Client client, String indexType, T[] entities);

	GetResponse searchDataById(Client client, String indexType, String id);

	DeleteResponse deleteSingleEntry(Client client, String indexType, String id);

	SearchResponse searchByQuery(Client client, String indexType, String searchId);

	<T> BulkResponse updateBulkData(Client client, String indexType, List<Product> entities);

}