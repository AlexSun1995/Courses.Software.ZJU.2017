package ch1_3_stack_queue_bags.creative_problems;

import ch1_3_stack_queue_bags.mQueue;

public class GeneralizedQueue<Item> extends mQueue<Item> {

    public GeneralizedQueue() {
        super();
    }

    public GeneralizedQueue insert(Item item) {
        return (GeneralizedQueue) this.enqueue(item);
    }

    /**
     * delete and return the Kth recently inserted item
     * @param k (k >= 1)
     * @return Kth recently inserted item
     */
    public Item delete(int k) {
        if( k <= 0){
            throw new NullPointerException("K too small!");
        }
        if(this.first == null) throw new NullPointerException("node empty!");
        Node p1;
        Node p2;
        p1 = p2 = this.first;
        Node markBefore_p2 = p2;
        boolean first = false;
        int cnt = 0;
        while(cnt < k){
            if(p1 == null){
                throw new NullPointerException("K too large!");
            }
            cnt++;
            p1 = p1.next;
        }

        while(p1 != null){
            p1 = p1.next;
            if(!first) {
                p2 = p2.next;
                first = true;
            }
            else{
                p2 = p2.next;
                markBefore_p2 = markBefore_p2.next;
            }
        }
        if(markBefore_p2 == p2){
            this.first = p2.next;
        }
        else{
            markBefore_p2.next = p2.next;
        }
        return p2.item;
    }

}