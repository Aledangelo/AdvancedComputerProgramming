package reading;

import java.io.Serializable;

public class Reading implements Serializable {
    private String type;
    private int value;

    public Reading(String type, int value) {
        this.type = type;
        this.value = value;
    } 

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public int getValue() {
        return this.value;
    }
}
