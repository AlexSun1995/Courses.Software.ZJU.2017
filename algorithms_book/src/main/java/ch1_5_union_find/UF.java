package ch1_5_union_find;

public interface UF {
    void union(int p1, int p2);
    int find(int p);
    boolean connected(int p1, int p2);
    /**
     * number of components
     * @return
     */
    int count();
}
