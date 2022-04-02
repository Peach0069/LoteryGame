package Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(77), node);
        tt.setFromX(0f);
        tt.setByX(7f);
        tt.setCycleCount(8);//number of animations
        tt.setAutoReverse(true);
    }

    public void playAnimation() {
        tt.playFromStart();
    }
}
