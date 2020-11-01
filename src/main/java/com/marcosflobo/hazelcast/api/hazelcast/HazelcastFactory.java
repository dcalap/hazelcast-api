package com.marcosflobo.hazelcast.api.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Factory
public class HazelcastFactory {
  private String hazelcastClusterName;

  @Value("${hazelcast.network.addresses}")
  private List<String> hazecastNetworkAddresses;

  @Value("${hazelcast.client.name}")
  private String hazelcastClientName;

  @Bean
  public HazelcastInstance getHazelcastClient () {
    NetworkConfig networkConfig = new NetworkConfig();
    networkConfig.setPublicAddress(hazecastNetworkAddresses.get(0));
    Config config = new Config();
    config.setInstanceName(hazelcastClientName);
    config.setClusterName("dev");
    config.setNetworkConfig(networkConfig);

    return Hazelcast.newHazelcastInstance(config);

  }
}