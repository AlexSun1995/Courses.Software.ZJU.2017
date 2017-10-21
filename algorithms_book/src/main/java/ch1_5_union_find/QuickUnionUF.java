package ch1_5_union_find;

/**
 * A Union-Find set
 * by quick union without path compression
 */
public class QuickUnionUF implements UF {

    private int[] id;
    private int count;

    public QuickUnionUF(int N){
        id = new int[N];
        count = N;
        for(int i=0;i<N;i++){
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int uid = find(q);
        if(pid == uid) return;
        else id[p] = uid;
        count--;
    }

    @Override
    public int find(int p) {
        while(p != id[p]) p = id[p];
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
