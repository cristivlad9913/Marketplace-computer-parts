package config;


import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Bean
    @Primary
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setAmbiguityIgnored(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
            .setImplicitMappingEnabled(true)
            .setFullTypeMatchingRequired(true)
            .setPropertyCondition(Conditions.isNotNull())
            .setSkipNullEnabled(true);

        return modelMapper;
    }
}
