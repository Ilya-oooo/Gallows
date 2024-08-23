
public class Main {
    public static void main(String[] args) {
        while(Gallows.startAndLeave() == 1){
            new Gallows().startGame();
        }
    }
}
