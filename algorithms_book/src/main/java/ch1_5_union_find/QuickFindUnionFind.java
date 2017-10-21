package ch1_5_union_find;

public class QuickFindUnionFind implements UF {

    private int id[];
    private int count;

    public QuickFindUnionFind(int N){
        count = N;
        id = new int[N + 1];
        for(int i = 1;i<N;i++){
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if(pid == qid) return;
        id[p] = qid;
        for(int i=1;i<id.length;i++){
            if(id[i] == pid){
                id[i] = qid;
            }
        }
        count--;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public boolean connected(int p, int q) {
        if(find(p) == find(q)) return true;
        else return false;
    }

    @Override
    public int count() {
        return count;
    }

    public int[] getId(){
        return id;
    }
}
