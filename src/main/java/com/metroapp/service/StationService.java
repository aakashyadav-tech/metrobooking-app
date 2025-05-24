package com.metroapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metroapp.model.Station;
import com.metroapp.model.StationMap;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StationService {
    @Autowired
    private ObjectMapper objectMapper;
    @Getter
    private Map<String, Station> stationMap;
    @PostConstruct
    private void loadStations() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("stations.json");
        StationMap stationMap= objectMapper.readValue(classPathResource.getInputStream(), StationMap.class);
        this.stationMap = stationMap.getStations();
    }
    public List<String> getAllStations(){
       return new ArrayList<>(stationMap.keySet());
    }
}
