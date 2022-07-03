package alert;

import java.io.Serializable;

public class AlertNotification implements Serializable {
    private static final long serialVersionUID = 104;

    private int componentID;
    private int criticality;

    public AlertNotification(int componentID, int criticality) {
        this.componentID = componentID;
        this.criticality = criticality;
    }

    public int getComponentID() {
        return this.componentID;
    }

    public int getCriticality() {
        return this.criticality;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public void setCriticality(int criticality) {
        this.criticality = criticality;
    }
}
