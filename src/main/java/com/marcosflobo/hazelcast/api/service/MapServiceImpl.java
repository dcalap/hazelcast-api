package com.marcosflobo.hazelcast.api.service;


import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.marcosflobo.hazelcast.api.domain.MapGetRequest;
import com.marcosflobo.hazelcast.api.domain.MapPutRequest;
import com.marcosflobo.hazelcast.api.hazelcast.HazelcastFactory;
import io.micronaut.http.HttpResponse;
import javax.inject.Singleton;

@Singleton
public class MapServiceImpl implements MapService {

  private final HazelcastInstance hazelcastInstance;
  private IMap<String, String> hazelcastMap;

  public MapServiceImpl(HazelcastFactory hazelcastFactory) {
    hazelcastInstance = hazelcastFactory.getHazelcastClient();
  }

  @Override
  public HttpResponse<String> get(MapGetRequest mapGetRequest) {
    hazelcastMap = hazelcastInstance.getMap(mapGetRequest.getMapName());
    String valueResponse = hazelcastMap.get(mapGetRequest.getKey());
    return HttpResponse.ok(valueResponse);
  }

  @Override
  public HttpResponse<String> write(MapPutRequest mapPutRequest) {

    hazelcastMap = hazelcastInstance.getMap(mapPutRequest.getMapName());
    hazelcastMap.put(mapPutRequest.getKey(), mapPutRequest.getValue());

    return HttpResponse.ok();
  }
}
