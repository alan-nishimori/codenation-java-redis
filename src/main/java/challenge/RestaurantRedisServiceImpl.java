package challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantRedisServiceImpl implements RestaurantRedisService {

    @Autowired
    private RestaurantMongoRepository restaurantMongoRepository;

    private final Logger logger = LoggerFactory.getLogger(RestaurantRedisServiceImpl.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Cacheable(value="neighborhood", key="#key")
    public String getNeighborhoodWithRestaurants(NeighborhoodMongo neighborhoodMongo, String key) {
        logger.info("Not cached, search mongodb.");
        NeighborhoodRedis neighborhoodRedis = new NeighborhoodRedis();
        neighborhoodRedis.setId(neighborhoodMongo.getId().toString());
        neighborhoodRedis.setName(neighborhoodMongo.getName());

        List<RestaurantMongo> restaurants = restaurantMongoRepository
                .findAllByNeighborhood(neighborhoodMongo.getGeometry())
                .stream().sorted(Comparator.comparing(RestaurantMongo::getName)).collect(Collectors.toList());

        for (RestaurantMongo restaurant : restaurants) {
            RestaurantRedis restaurantRedis = new RestaurantRedis();
            restaurantRedis.setId(restaurant.getId().toString());
            restaurantRedis.setName(restaurant.getName());
            restaurantRedis.setX(restaurant.getLocation().getX());
            restaurantRedis.setY(restaurant.getLocation().getY());
            neighborhoodRedis.addRestaurant(restaurantRedis);
        }

        try {
            return mapper.writeValueAsString(neighborhoodRedis);
        } catch (Exception e) {
            logger.error("Error converting object to json");
            return null;
        }
    }
}
