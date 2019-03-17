package challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRedisService restaurantRedisService;

    @Autowired
    private NeighborhoodMongoRepository neighborhoodMongoRepository;

    private final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public NeighborhoodRedis findInNeighborhood(double x, double y) {
        logger.info("Starting search for the neighborhood at {}, {}", x, y);
        NeighborhoodMongo neighborhoodMongo = neighborhoodMongoRepository.findNeighborhoodByPoint(x, y);

        logger.info("Starting search for the restaurants at {} neighborhood", neighborhoodMongo.getName());
        String neighborhoodJson = restaurantRedisService.getNeighborhoodWithRestaurants(
                neighborhoodMongo, "restaurant:" + neighborhoodMongo.getId().toString());
        NeighborhoodRedis neighborhoodRedis;
        try {
            neighborhoodRedis = mapper.readValue(neighborhoodJson, NeighborhoodRedis.class);
        } catch (Exception e) {
            logger.error("Error converting json to object.");
            return null;
        }

        logger.info("Returning restaurants found.");
        return neighborhoodRedis;
    }
}
