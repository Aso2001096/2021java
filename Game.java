import java.util.Scanner;

public class Game {
    private final int SHIP_NUM = 3;
    private final int MAP_SIZE = 5;
    private Ship[] ships = new Ship[SHIP_NUM];
    private Map map = new Map();

    //コンストラクタ
    public Game(){
        for(int i = 0;i < SHIP_NUM; i++){
            extracted(i);
        }
    }

    private void extracted(int i) {
        ships[i] = new Ship();
    }

    //初期化
    public void init(){
        map.init(MAP_SIZE);
        for(int i = 0; i < SHIP_NUM; i++){
            do{
                ships[i].init(MAP_SIZE);
            }while(!map.deployShip(ships[i]));
        }
    }

    public void execute(){
        int attackX;
        int attackY;

        Scanner scanner = new Scanner(System.in);
        int turn = 1;

        title();
        while(!isAllSink()){
            System.out.printf("---------[ターン%d]---------\n",turn);
            displaySituation();
            System.out.printf("爆弾のx座標を入力してください(1-%d)\n",MAP_SIZE);
            attackX = scanner.nextInt();
            System.out.printf("爆弾のy座標を入力してください(1-%d)\n",MAP_SIZE);
            attackY = scanner.nextInt();

            for (int i = 0; i < SHIP_NUM; i++){
                int result = ships[i].check(attackX-1,attackY-1);
                //結果によって動きを変える
                doByResult(ships[i],i+1,result);
            }
            turn++;
        }
        System.out.printf("すべて撃沈！おめでとう\n");

        scanner.close();
    }

    private void title(){
        System.out.printf("***************************\n 　　　　戦艦ゲーム　　　　 \n***************************\n");
    }

    private boolean isAllSink(){
        boolean allSink = true;//すべて撃沈
        for(int i = 0; i < SHIP_NUM; i++){
            if( ships[i].isAlive() ){
                allSink = false;
                break;
            }
        }

        return allSink;
    }

    private void displaySituation(){
        for(int i = 0; i < SHIP_NUM; i++){
            if( ships[i].isAlive() ){
                System.out.printf("船%d生きてる\n",i+1);
            }else{
                System.out.printf("船%d撃沈済み\n",i+1);
            }
        }
    }

    private void doByResult(Ship ship,int no,int result){
        if(result ==Ship.NO_HIT){
            System.out.printf("船%d:はずれ！\n",no);
        }else if(result == Ship.NEAR){
            System.out.printf("船%d:波高し！\n",no);
        }else if(result == Ship.HIT){
            System.out.printf("船%d:爆弾が当たった！しかし船はまだ沈まない！船は移動します\n",no);
            moveShip(ship);
        }else{
            System.out.printf("船%d:爆弾が当たった！撃沈しました！\n",no);
            map.clear(ship.getPosX(),ship.getPosY());
        }
    }

    private void moveShip(Ship ship){

        //一旦今の場所をクリアする
        map.clear(ship.getPosX(),ship.getPosY());
        do{
            ship.move(MAP_SIZE);
        }while(!map.deployShip(ship));
    }
}