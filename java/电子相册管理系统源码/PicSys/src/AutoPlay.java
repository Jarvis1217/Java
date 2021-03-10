
import java.util.Timer;
import java.util.TimerTask;


public class AutoPlay {
   private int second;
  FullFrame new1;
    private final Timer timer = new Timer();

    public AutoPlay(FullFrame new1){
        this.new1 =new1;
        second = 2;
    }

      public void start(){
       timer.schedule(new TimerTask() {
           public void run() {
                   new1.Forward();
                   new1.getJButton1().setEnabled(false);
            }
       },second * 1000 ,second * 1000);
    }
         public void stop(){
        timer.cancel();
    }
}
