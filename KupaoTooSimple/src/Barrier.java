import java.awt.Image;

public class Barrier {
	public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;
    public String orientation;
    private Image image;
    public Barrier(String orientation) {
    	this.orientation = orientation;
    	reset();
    }
    
    public void reset(){
        width = 66;
        height = 400;
        x = App.WIDTH + 2;
        if (orientation.equals("south")) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
     }
    
    public void update() {
    	x -= speed;
    }
    
    public boolean collides(int _x, int _y, int _width, int _height) {
       //TODO when update this 
        return false;
    }
    
    public Render getRender() {
    	Render r = new Render();
        r.x = x;
        r.y = y;
        if (image == null) {
            image = Loader.loadImage("lib/pipe-" + orientation + ".png");
        }
        r.image = image;
        return r;
    }
    
    
}
