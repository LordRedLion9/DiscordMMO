package mmoGame;

public class Exit {

    private String exitName = "Generic exit gate";

    private Location loc1;
    private Location loc2;


    public Exit(Location L1, Location L2){

        loc1 = L1;
        loc2 = L2;

    }

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }

    public Location getOtherLocation(Location loc){
        if (loc.equals(loc1)) {
            return getLoc2();
        }else if(loc.equals(loc2)){
            return getLoc1();
        }else{
            System.out.println("ERROR: that location is not paired in this exit");
            return null;
        }
    }

    public Location getLoc1() {
        return loc1;
    }

    public void setLoc1(Location loc1) {
        this.loc1 = loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public void setLoc2(Location loc2) {
        this.loc2 = loc2;
    }


}
