import java.awt.Image;

public class Dot {
	public int x;
	public int y;
	public int width;
	public int height;
	private int jumpDelay;
	// public boolean dead;
	public double yvel;
	public double gravity;
	//private double rotation;
	private Image image;

	public Dot() {
        x = 100;
        y = 150;	
        yvel = 0;
        width = 45;
        image = null;
        height = 32;
        gravity = 0.5;
        jumpDelay = 0;
        // rotation = 0.0;
	}

	public void update() {
		x = 100;
		y = Game.getSafeY();
		System.out.println("this is y: " + y);
		System.out.println("queue size: " + Game.safeQueue.size());
	}

	public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Loader.loadImage("lib/bird.png");     
        }
        r.image = image;
        return r;
	}
}
