
package prijavaradnika;


public abstract class Kartica {
        protected int ID;

    public Kartica() {
        ID=0;
    }

    public Kartica(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    
    
}
