import java.util.*;

public class HashMapCode {
    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n;
        private int N;
        private LinkedList<Node> buckest[];

        public HashMap() {
            this.N = 4;
            this.buckest = new LinkedList[4];
            CLL(buckest.length);
        }

        private void CLL(int size) {
            for (int i = 0; i < size; i++) {
                buckest[i] = new LinkedList<>();
            }
        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> list = buckest[bi];

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key == key) {
                    return i;
                }
            }

            return -1;
        }

        private void rehashing() {
            LinkedList<Node> oldBuckst[] = buckest;
            buckest = new LinkedList[N + 4];
            CLL(buckest.length);

            for (int i = 0; i < oldBuckst.length; i++) {
                LinkedList<Node> ll = oldBuckst[i];
                for (int index = 0; index < ll.size(); index++) {
                    Node node = ll.get(index);
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value) {
            int bi = Math.abs(key.hashCode()) % N;
            int di = searchInLL(key, bi);

            if (di == -1) { // Key Desno`t exits
                buckest[bi].add(new Node(key, value));
                n++;
            } else { // key exits
                Node node = buckest[bi].get(di);
                node.value = value;
            }

            double lemda = (double) n / N;

            if (lemda > 2.0) {
                rehashing();
            }

        }

        public V get(K key) {
            int bi = Math.abs(key.hashCode()) % N;
            int di = searchInLL(key, bi);

            if (di == -1) { // Key Desno`t exits
                return null;
            } else { // key exits
                Node node = buckest[bi].get(di);
                return node.value;
            }
        }

        public boolean contianskey(K key) {
            int bi = Math.abs(key.hashCode()) % N;
            int di = searchInLL(key, bi);

            if (di == -1) { // Key Desno`t exits
                return false;
            } else { // key exits
                return true;
            }
        }

        public V Remove(K key) {
            int bi = Math.abs(key.hashCode()) % N;
            int di = searchInLL(key, bi);

            if (di == -1) { // Key Desno`t exits
                return null;
            } else { // key exits
                Node node = buckest[bi].remove(di);
                n--;
                return node.value;
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckest.length; i++) {
                LinkedList<Node> ll = buckest[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return n == 0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("IND", 100);
        map.put("US", 400);
        ArrayList<String> keys = map.keySet();

        for (int index = 0; index < keys.size(); index++) {
            System.out.println(keys.get(index) + " => " + map.get(keys.get(index)));
        }
        System.out.println(map.isEmpty());
        System.out.println(map.contianskey("US"));
        System.out.println(map.Remove("US"));


    }
}
