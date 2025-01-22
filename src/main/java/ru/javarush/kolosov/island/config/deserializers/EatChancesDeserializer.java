package ru.javarush.kolosov.island.config.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.kolosov.island.config.ConfigNameOrganismsClass;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class EatChancesDeserializer extends JsonDeserializer<Map<Class<? extends Organism>, Integer>> {
    @Override
    public Map<Class<? extends Organism>, Integer> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> classOrganismParamsMap = objectMapper.readValue(node.toString(), new TypeReference<>() {});

        return classOrganismParamsMap.entrySet().stream().collect(Collectors.toMap(entry -> ConfigNameOrganismsClass.getClassByName(entry.getKey()), entry -> entry.getValue()));
    }
}
