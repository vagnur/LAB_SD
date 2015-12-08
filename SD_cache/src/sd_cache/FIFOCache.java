
package sd_cache;

import java.util.LinkedHashMap;

public class FIFOCache {
 
    int size;
    LinkedHashMap<String, String> cache;

    public String getEntryFromCache(String query) {
        String result = cache.get(query);
        return result;
    }
    
    public FIFOCache(int size) {
        this.size = size;
        this.cache = new LinkedHashMap<>();
    }
    
    public void addEntryToCache(String query, String answer) {
        
        if(cache.size()==size){
            String first_element = cache.entrySet().iterator().next().getKey();
            System.out.println("Removiendo: '" + first_element + "'");
            cache.remove(first_element);
        }
        
        cache.put(query,answer);
    }

    public void print() {
        System.out.println("===== My FIFO Cache =====");
        System.out.println("| "+ cache.keySet() +" | ");
        System.out.println("========================");
    }
    
}
