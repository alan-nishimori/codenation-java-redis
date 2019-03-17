package challenge;

public interface RestaurantRedisService {

    String getNeighborhoodWithRestaurants(NeighborhoodMongo neighborhoodMongo, String key);
}
