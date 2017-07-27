import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {
	  public static final int DELAY = 100;
	  private Dot dot;
	  private ArrayList<Barrier> barriers;
	  private int barrierDelay;
	  private static int safeY = 0;
	  public static Queue<Integer> safeQueue = new LinkedList<Integer>();
	  public void GameInit(){
		  dot = new Dot();
		  barrierDelay = 0;
		  barriers = new ArrayList<Barrier>();
	  }
	  
      public Game() {
    	  GameInit();
      }
      
      public ArrayList<Render> getRenders(){
    	  ArrayList<Render> renders = new ArrayList<Render>();
    	  for(Barrier b: barriers) {
    		  renders.add(b.getRender());
    	  }
    	  renders.add(dot.getRender());
    	  return renders;
      }
      
      public void update(){
    	  moveBarriers();
    	  dot.update();
      }
       
      private void moveBarriers()
      {
    	  barrierDelay--;
    	  if(barrierDelay < 0)
    	  {
    		  barrierDelay = DELAY;
    		  for(Barrier b : barriers) {
    			  if(b.x - b.width < 0) {
    				  if(!safeQueue.isEmpty())
    					  safeQueue.poll();
    				  barriers.remove(b);
    				  break;
    			  }
    		  }
    		  Barrier b = new Barrier("south");
    		  barriers.add(b);
    		  Barrier b1 = new Barrier("north");
    		  b1.y = b.y + b.height + 175;
    		  safeQueue.add(b1.y - 87);
    		  barriers.add(b1);  
    	  }
    	  
    	  for(Barrier b : barriers) {
			  b.update();
		  }
      }
      
      public static int getSafeY() {
    	  if(safeQueue.isEmpty()) {
    		  return 150;
    	  }
    	  else
    	  {
    		  return safeQueue.peek();
    	  }
      }
      
      
}
