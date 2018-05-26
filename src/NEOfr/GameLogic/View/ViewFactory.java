package NEOfr.GameLogic.View;

import NEOfr.GameLogic.Unit.Unit;
import NEOfr.GameLogic.View.ViewElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class ViewFactory {
    public ViewFactory(String configName){
        Properties props =new Properties();
        try {
            props.load(getClass().getResourceAsStream(configName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Object> keys =props.keySet();
        for(Object o : keys){
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResourceAsStream(props.getProperty((String) o)));
            } catch (IOException e) {
                System.out.println("wake up you obosralsya");
                e.printStackTrace();
            }
            viewElementHashMap.put((String) o, image);
        }
    }
    public ViewElement getViewElement(Unit unit){
        return new ViewElement(viewElementHashMap.get(unit.getId()),unit.getWidth(),unit.getHeight(),unit.getPos(),unit.getY() - unit.getHeight());
    }
    HashMap<String,BufferedImage> viewElementHashMap = new HashMap<>();
}
