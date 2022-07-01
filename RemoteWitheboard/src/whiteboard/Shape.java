package whiteboard;

import java.io.Serializable;


public interface Shape extends Serializable {   // I need to serialize objects to send them on the Network
    public void draw();
}
