public class Map {
    private final int NO_SHIP=0;
    private final int SHIP=1;
    private int[][] map;

    public Map(){
        ;//nothing
    }

    public void init(int mapsize){
        map = new int[mapsize][mapsize];
        for(int i = 0; i < mapsize; i++){
            for(int j = 0; j < mapsize; j++){
                map[i][j] = NO_SHIP;
            }
        }
    }

    public boolean deployShip(Ship ship){
        int x = ship.getPosX();
        int y = ship.getPosY();

        if(x >= map.length || y >= map.length){
            return false;
        }

        if(map[x][y] != NO_SHIP){
            return false;
        }

        map[x][y] = SHIP;
        
        return true;
    }

    public void clear(int x,int y){

        if(x >= map.length || y >= map.length){
            return;
        }

        map[x][y] = NO_SHIP;
    }
}