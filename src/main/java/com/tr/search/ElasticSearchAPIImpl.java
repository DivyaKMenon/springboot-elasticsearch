package com.tr.search;

import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tr.model.Product;

@Service
public class ElasticSearchAPIImpl implements ElasticSearchAPI {
	@Value("${elasticsearch.indexname}")
	private String index;

	// to see inserted data :
	// "http://localhost:9200/TRD/_search?q=*&pretty"
	@Override
	public IndexResponse insertSingleData(Client client, String indexType, String data, String id) {
		return client.prepareIndex(index, indexType, id).setSource(data).get();
	}

	@Override
	public GetResponse searchDataById(Client client, String indexType, String id) {
		return client.prepareGet(index, indexType, id).get();
	}

	@Override
	public SearchResponse searchDataLike(Client client, String indexType, String data) {
		return client.prepareSearch(index).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchPhrasePrefixQuery("Name", data)).setExplain(true).execute().actionGet();
	}

	@Override
	public SearchResponse searchData(Client client, String indexType) {
		return client.prepareSearch(index).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery()).setExplain(true).execute().actionGet();
	}

	@Override
	public <T> BulkResponse insertBulkData(Client client, String indexType, T[] entities) {
		BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
		for (T data : entities) {
			bulkRequestBuilder.add(client.prepareIndex(index, indexType).setSource(new Gson().toJson(data)));
		}
		return bulkRequestBuilder.execute().actionGet();
	}

	@Override
	public <T> BulkResponse updateBulkData(Client client, String indexType, List<Product> entities) {
		BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
		for (Product data : entities) {
			bulkRequestBuilder.add(client.prepareUpdate(index, indexType, data.getEsId())
					.setDoc(new Gson().toJson(data)).setDocAsUpsert(true));
		}
		return bulkRequestBuilder.execute().actionGet();
	}

	@Override
	public DeleteResponse deleteSingleEntry(Client client, String indexType, String id) {
		return client.prepareDelete(index, indexType, id).get();
	}

	@Override
	public SearchResponse searchByQuery(Client client, String indexType, String searchId) {
		return client.prepareSearch(index).setTypes(indexType).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("id", searchId)).setExplain(true).get();
	}

}
