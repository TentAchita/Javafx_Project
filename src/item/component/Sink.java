package item.component;

import Interface.activeAble;
import Interface.taskAble;
import item.GroupObjectActivable;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sink extends GroupObjectActivable implements taskAble, activeAble {
    private ImageView alert;
    private boolean isAlert = false;
    private long lastUpdateTime;
    private int holdAction = 0;
    private int currentWaitFrameIndex = 0;
    private int currentActiveFrameIndex = 0;
    private final String[] waitRedImage = {"UI/Wait/WaitRed/WaitRed1.png", "UI/Wait/WaitRed/WaitRed2.png", "UI/Wait/WaitRed/WaitRed3.png", "UI/Wait/WaitRed/WaitRed4.png", "UI/Wait/WaitRed/WaitRed5.png", "UI/Wait/WaitRed/WaitRed6.png", "UI/Wait/WaitRed/WaitRed7.png", "UI/Wait/WaitRed/WaitRed8.png", "UI/Wait/WaitRed/WaitRed9.png", "UI/Wait/WaitRed/WaitRed10.png", "UI/Wait/WaitRed/WaitRed11.png", "UI/Wait/WaitRed/WaitRed12.png", "UI/Wait/WaitRed/WaitRed13.png", "UI/Wait/WaitRed/WaitRed14.png", "UI/Wait/WaitRed/WaitRed15.png", "UI/Wait/WaitRed/WaitRed16.png", "UI/Wait/WaitRed/WaitRed17.png"};
    private final String[] waitBlueImage = {"UI/Wait/WaitBlue/WaitBlue1.png", "UI/Wait/WaitBlue/WaitBlue2.png", "UI/Wait/WaitBlue/WaitBlue3.png", "UI/Wait/WaitBlue/WaitBlue4.png", "UI/Wait/WaitBlue/WaitBlue5.png", "UI/Wait/WaitBlue/WaitBlue6.png", "UI/Wait/WaitBlue/WaitBlue7.png", "UI/Wait/WaitBlue/WaitBlue8.png", "UI/Wait/WaitBlue/WaitBlue9.png", "UI/Wait/WaitBlue/WaitBlue10.png", "UI/Wait/WaitBlue/WaitBlue11.png", "UI/Wait/WaitBlue/WaitBlue12.png", "UI/Wait/WaitBlue/WaitBlue13.png", "UI/Wait/WaitBlue/WaitBlue14.png", "UI/Wait/WaitBlue/WaitBlue15.png", "UI/Wait/WaitBlue/WaitBlue16.png", "UI/Wait/WaitBlue/WaitBlue17.png"};
    public Sink() {
        super("Component/Sink/sink_withoutbowl.png");

        alert = new ImageView("UI/Wait/WaitRed/WaitRed1.png");
        alert.setFitWidth(200);
        alert.setFitHeight(200);
        alert.setTranslateX(200);
        alert.setTranslateY(-100);
        getChildren().add(alert);
        alert.setVisible(false);

        lastUpdateTime = System.nanoTime();
    }

    @Override
    public void taskAlert() {
        isAlert = true;
        this.instance.setImage(new Image("Component/Sink/sink_withbowl.png"));
        alert.setVisible(true);
        alert.setImage(new Image("UI/Wait/WaitRed/WaitRed1.png"));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(isAlert){
                AlertAnimation(now);}
                else {
                ActiveAnimation();}
            }
        };
        timer.start();
    }

    public void AlertAnimation(long now){

        long elapsedTime = now - lastUpdateTime;
        if (elapsedTime >= 500_000_000) { // 100 milliseconds in nanoseconds
            alert.setImage(new Image(waitRedImage[currentWaitFrameIndex % 17]));
            currentWaitFrameIndex++;
            if (currentWaitFrameIndex >= 17) {
                currentWaitFrameIndex = 0;
                alert.setVisible(false);

            }
            lastUpdateTime = now; // Reset the last update time for timing
        }

    }

    public void ActiveAnimation(){

            if(holdAction >= 20){
                currentActiveFrameIndex++;
                holdAction = 0;
            }
            alert.setImage(new Image(waitBlueImage[currentActiveFrameIndex % 17]));
            isAlert = true;
            if (currentActiveFrameIndex >= 17) {
                currentActiveFrameIndex = 0;
                currentWaitFrameIndex = 0;
                alert.setVisible(false);
                isAlert = false;
                instance.setImage(new Image("Component/Sink/sink_withoutbowl.png"));
//                stop(); // Stop the AnimationTimer
            }


    }

    @Override
    public void Active() {
        isAlert = false;
        holdAction++;
    }
}
