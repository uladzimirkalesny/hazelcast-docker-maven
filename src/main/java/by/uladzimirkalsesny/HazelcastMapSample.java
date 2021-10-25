package by.uladzimirkalsesny;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastMapSample {

    private static final String CLUSTER_NAME = "hello-world";
    private static final String DISTRIBUTED_MAP_NAME = "my-distributed-map";

    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
        // The name of the cluster that you want to connect to.
        clientConfig.setClusterName(CLUSTER_NAME);

        // Create a client instance, using your configuration.
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        // Create a map called my-distributed-map.
        IMap<String, String> map = client.getMap(DISTRIBUTED_MAP_NAME);

        // Write some keys and values to the map.
        map.put("1", "John");
        map.put("2", "Mary");
        map.put("3", "Jane");
        map.put("4", "John");

        // Request all data in the map and print it to the console
        IMap<String, String> distributedIMap = client.getMap(DISTRIBUTED_MAP_NAME);
        distributedIMap.forEach((k, v) -> System.out.println(k + " = " + v));

        client.getCluster().getMembers().forEach(m -> System.out.println(m.getAddress()));

        // Disconnect from the member.
        client.shutdown();
    }

}
