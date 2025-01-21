package ru.javarush.kolosov.island.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.kolosov.island.entities.organisms.Organism;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class SettingsDeserializer extends JsonDeserializer<Map<Class<? extends Organism>, OrganismParams>> {

    @Override
    public Map<Class<? extends Organism>, OrganismParams> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, OrganismParams> classOrganismParamsMap = objectMapper.readValue(node.toString(), new TypeReference<>() {});

        return classOrganismParamsMap.entrySet().stream().collect(Collectors.toMap(entry -> ConfigNameOrganismsClass.getClassByName(entry.getKey()), entry -> entry.getValue()));
    }
}
