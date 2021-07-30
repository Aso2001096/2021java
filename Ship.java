public class Ship {
    private int x;
    private int y;
    private int hp;
    private final int MAX_HP= 3;
    public final static int NO_HIT = 0;
    public final static int NEAR = 1;
    public final static int HIT = 2;
    public final static int SINK = 3;

    public int getPosX(){
        return x;
    }

    public int getPosY(){
        return y;
    }
    
    public Ship(){
        x = 0;
        y = 0;
    }

    public void init(int mapsize){
        move(mapsize);
        hp = MAX_HP;
    }

    public void move(int mapsize){
        x = (int)(Math.random()*mapsize);
        y = (int)(Math.random()*mapsize);
    }

    public int check(int x,int y){
        int result = NO_HIT;
        //hit?
        if(x == this.x && y == this.y){
            hp--;
            if(hp == 0){
                result = SINK;
            }else{
                result = HIT;
            }
        }
        //near?
        else if(
            (this.x-1 <= x && x <= this.x+1) &&
            (this.y-1 <= y && y <= this.y+1)
        ){
            result = NEAR;
        }

        return result;
    }

    public boolean isAlive(){
        return (hp>0);
    }
}
