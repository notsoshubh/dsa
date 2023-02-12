import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTable {
    
    private class Entry {
        public int key;
        public String value;

        public Entry(int key, String value){
            this.key = key;
            this.value = value;
        }

        public String toString(){
            return "{"+ key + ":" + value + "}";
        }
    }

    private LinkedList<Entry>[] map = new LinkedList[5];

    public int hash(int key){
        return key % map.length;
    }

    public void put(int key, String value){
        var entry = getEntry(key);
        if (entry != null){
            entry.value = value;
            return;
        }
        
        getOrCreateList(key).addLast(new Entry(key, value));

    }

    public String get(int key){
        var entry = getEntry(key);
        return (entry==null) ? null : entry.value;    
    }

    public void remove(int key){
        var entry = getEntry(key);
        
        if (entry == null)
            throw new NoSuchElementException();
        
        getList(key).remove(entry);
    }

    private Entry getEntry(int key){
        var list = getList(key);

        if (list!=null){
            for (var entry : list){
                if (entry.key == key)
                    return entry;
            }
        }
        return null;
    }

    private LinkedList<Entry> getList(int key){
        return map[hash(key)];
    }

    private LinkedList<Entry> getOrCreateList(int key){
        var index = hash(key);
        var list = map[index];
        if (list == null)
            map[index] = new LinkedList<>();
        
        return map[index];
    }

    public void print(){
        for (int i = 0; i < map.length; i++){
            if (map[i] == null){
                System.out.println("List - " + i + ":" + "null");
            }
            else
            System.out.println("List - " + i + ":" + map[i].toString());
        }
    }

}
