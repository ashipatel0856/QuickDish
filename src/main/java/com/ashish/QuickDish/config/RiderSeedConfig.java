package com.ashish.QuickDish.config;

import com.ashish.QuickDish.Entity.DeliveryRider;
import com.ashish.QuickDish.repository.DeliveryRiderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RiderSeedConfig {

    @Bean
    public CommandLineRunner loadRiders(DeliveryRiderRepository riderRepository) {
        return args -> {
            if (riderRepository.count() == 0) {
                DeliveryRider rider = new DeliveryRider();
                rider.setName("Rider A");
                rider.setPhone("9876543210");
                rider.setLatitude(28.6139);
                rider.setLongitude(77.2090);
                rider.setAvailable(true);

                riderRepository.save(rider);


                DeliveryRider riders = new DeliveryRider();
                rider.setName("Rider B");
                riders.setPhone("98765432g");
                riders.setLatitude(30.6139);
                riders.setLongitude(79.2090);
                riders.setAvailable(true);

                riderRepository.save(rider);

            }
        };
    }
}
