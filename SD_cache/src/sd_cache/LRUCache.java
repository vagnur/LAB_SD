package sd_cache;

import java.util.LinkedHashMap;

public class LRUCache {

    int size;
    LinkedHashMap<String, String> cache;

    public String getEntryFromCache(String query) {
        String result = cache.get(query);
        if(result != null) {
            cache.remove(query);
            cache.put(query, result);
        }
        return result;
    }
    
    public LRUCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap<>();
    }
    
    public void addEntryToCache(String query, String answer) {
        
        if (cache.containsKey(query)) { // HIT
            // Bring to front
            cache.remove(query);
            cache.put(query, answer);
        } else { // MISS
            if(cache.size() == size) {
                String first_element = cache.entrySet().iterator().next().getKey();
                System.out.println("Removiendo: '" + first_element + "'");
                cache.remove(first_element);
            }
            cache.put(query, answer);
        }
    }

    public void print() {
        System.out.println("===== My LRU Cache =====");
        System.out.println("| "+ cache.keySet() +" | ");
        System.out.println("========================");
    }

}
