package Models;

public class Celular {
    private static Celular instance;
    private Celular(){};
    public static Celular getInstance(){
        if(instance==null){
            instance = new Celular();
        }
        return instance;
    }
}
