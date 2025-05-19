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
                riderRepository.save(DeliveryRider.builder()
                        .name("Rider A")
                        .phone("9876543210")
                        .latitude(28.6139)
                        .longitude(77.2090)
                        .build());

                riderRepository.save(DeliveryRider.builder()
                        .name("Rider B")
                        .phone("9999999999")
                        .latitude(28.7041)
                        .longitude(77.1025)
                        .build());
            }
        };
    }
}
