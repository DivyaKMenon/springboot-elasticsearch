# Spring-ElasticSearch
Use added zip file for elastic search

To view all data in ES

Goto: http://localhost:9200/indexName/_search?q=*&pretty

To insert single data into ES:

public IndexResponse insertSingleData(Client client, String indexType, T entity, String id) {
		return client.prepareIndex(index, indexType, id).setSource(new Gson().toJson(data)).get();
	}
To insert bulk data into ES:

public <T> BulkResponse insertBulkData(Client client, String indexType, T[] entities) {
		BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
		for (T data : entities) {
			bulkRequestBuilder.add(client.prepareIndex(index, indexType).setSource(new Gson().toJson(data)));
		}
		return bulkRequestBuilder.execute().actionGet();
	}
  
  To search for any data in ES:
  
  public SearchResponse searchData(Client client, String indexType) {
		return client.prepareSearch(index).setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(indexType, ""))).setExplain(true)
				.execute().actionGet();
	}
