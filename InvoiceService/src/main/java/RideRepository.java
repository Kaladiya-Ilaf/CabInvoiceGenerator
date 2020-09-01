import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRide(String userId, Ride[] rides) {
        ArrayList<Ride> rideArrayList = this.userRides.get(userId);
        if(rideArrayList == null){
            this.userRides.put(userId, new ArrayList<>(Arrays.asList(rides)));
        }
    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
