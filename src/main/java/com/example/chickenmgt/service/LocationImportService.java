package com.example.chickenmgt.service;

import com.example.chickenmgt.model.*;
import com.example.chickenmgt.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class LocationImportService {

    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final SectorRepository sectorRepository;
    private final CellRepository cellRepository;
    private final VillageRepository villageRepository;
    private final ObjectMapper objectMapper;

    public LocationImportService(ProvinceRepository provinceRepository,
                                DistrictRepository districtRepository,
                                SectorRepository sectorRepository,
                                CellRepository cellRepository,
                                VillageRepository villageRepository,
                                ObjectMapper objectMapper) {
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.sectorRepository = sectorRepository;
        this.cellRepository = cellRepository;
        this.villageRepository = villageRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void importLocations(String jsonContent) throws IOException {
        JsonNode root = objectMapper.readTree(jsonContent);
        if (root.isArray()) {
            for (JsonNode provinceNode : root) {
                processProvince(provinceNode);
            }
        } else {
            processProvince(root);
        }
    }

    private void processProvince(JsonNode node) {
        String name = node.get("name").asText();
        String code = node.get("code").asText();

        Province province = provinceRepository.findByCode(code)
                .orElseGet(() -> provinceRepository.save(Province.builder().name(name).code(code).build()));

        JsonNode districtsNode = node.get("districts");
        if (districtsNode != null && districtsNode.isArray()) {
            for (JsonNode districtNode : districtsNode) {
                processDistrict(districtNode, province);
            }
        }
    }

    private void processDistrict(JsonNode node, Province province) {
        String name = node.get("name").asText();
        String code = node.get("code").asText();

        District district = districtRepository.findByCode(code)
                .orElseGet(() -> districtRepository.save(District.builder().name(name).code(code).province(province).build()));

        JsonNode sectorsNode = node.get("sectors");
        if (sectorsNode != null && sectorsNode.isArray()) {
            for (JsonNode sectorNode : sectorsNode) {
                processSector(sectorNode, district);
            }
        }
    }

    private void processSector(JsonNode node, District district) {
        String name = node.get("name").asText();
        String code = node.get("code").asText();

        Sector sector = sectorRepository.findByCode(code)
                .orElseGet(() -> sectorRepository.save(Sector.builder().name(name).code(code).district(district).build()));

        JsonNode cellsNode = node.get("cells");
        if (cellsNode != null && cellsNode.isArray()) {
            for (JsonNode cellNode : cellsNode) {
                processCell(cellNode, sector);
            }
        }
    }

    private void processCell(JsonNode node, Sector sector) {
        String name = node.get("name").asText();
        String code = node.get("code").asText();

        Cell cell = cellRepository.findByCode(code)
                .orElseGet(() -> cellRepository.save(Cell.builder().name(name).code(code).sector(sector).build()));

        JsonNode villagesNode = node.get("villages");
        if (villagesNode != null && villagesNode.isArray()) {
            for (JsonNode villageNode : villagesNode) {
                processVillage(villageNode, cell);
            }
        }
    }

    private void processVillage(JsonNode node, Cell cell) {
        String name = node.get("name").asText();
        String code = node.get("code").asText();

        villageRepository.findByCode(code)
                .orElseGet(() -> villageRepository.save(Village.builder().name(name).code(code).cell(cell).build()));
    }
}
