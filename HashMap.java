public class HashMap {
    
    private String[] values = new String[5];
    private int[] keys = new int[5];
    private int size = 0;

    public void put(int key, String value){
        var index = hash(key);
        values[index] = value;
        keys[index] = key;
        size++;
    }

    public int hash(int key){

        var count = 0;
        var temp = key;

        while (count++ != 5){
            var hashValue = temp++ % 5;
            if (keys[hashValue] == key || values[hashValue] == null)
                return hashValue;
        }
        throw new IllegalStateException();
    }

    public String get(int key){
        return values[hash(key)];
    }

    public void remove(int key){
        var index = hash(key);
        values[index] = null;
        keys[index] = 0;
        size--;
    }

    public int size(){
        return size;
    }

    public void print(){
        int count = 0;
        while (count != 5){
            System.out.println("[" + keys[count] + "," + values[count] + "]");
            count++;
        }
    }


}
