package pl.beerapp.dto;

import pl.beerapp.dto.RecipeDTOs.*;
import pl.beerapp.dto.RecipeDTOs.GrainDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeDTO implements Serializable {
    private String name;
    private String style;
    private Boolean visible;
    private Double batchSize;
    private Double efficiency;
    private Double estBoilSize;
    private Double estBoilTime;
    private FermentationDTO fermentationDTO;
    private Long yeastID;
    private ArrayList<GrainDTO> grainList = new ArrayList<>();
    private ArrayList<HopStoreDTO> hopStoreList = new ArrayList<>();
    private ArrayList<MashingDTO> mashingList = new ArrayList<>();

    public RecipeDTO() {
    }

    public RecipeDTO(String name, String style, Boolean visible, Double batchSize, Double efficiency, Double estBoilSize, Double estBoilTime, FermentationDTO fermentationDTO, Long yeastID, ArrayList<GrainDTO> grainList, ArrayList<HopStoreDTO> hopStoreList, ArrayList<MashingDTO> mashingList) {
        this.name = name;
        this.style = style;
        this.visible = visible;
        this.batchSize = batchSize;
        this.efficiency = efficiency;
        this.estBoilSize = estBoilSize;
        this.estBoilTime = estBoilTime;
        this.fermentationDTO = fermentationDTO;
        this.yeastID = yeastID;
        this.grainList = grainList;
        this.hopStoreList = hopStoreList;
        this.mashingList = mashingList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getEstBoilSize() {
        return estBoilSize;
    }

    public void setEstBoilSize(Double estBoilSize) {
        this.estBoilSize = estBoilSize;
    }

    public Double getEstBoilTime() {
        return estBoilTime;
    }

    public void setEstBoilTime(Double estBoilTime) {
        this.estBoilTime = estBoilTime;
    }

    public FermentationDTO getFermentationDTO() {
        return fermentationDTO;
    }

    public void setFermentationDTO(FermentationDTO fermentationDTO) {
        this.fermentationDTO = fermentationDTO;
    }

    public Long getYeastID() {
        return yeastID;
    }

    public void setYeastID(Long yeastID) {
        this.yeastID = yeastID;
    }

    public List<GrainDTO> getGrainList() {
        return grainList;
    }

    public void setGrainList(ArrayList<GrainDTO> grainList) {
        this.grainList = grainList;
    }

    public ArrayList<HopStoreDTO> getHopStoreList() {
        return hopStoreList;
    }

    public void setHopStoreList(ArrayList<HopStoreDTO> hopStoreList) {
        this.hopStoreList = hopStoreList;
    }

    public ArrayList<MashingDTO> getMashingList() {
        return mashingList;
    }

    public void setMashingList(ArrayList<MashingDTO> mashingList) {
        this.mashingList = mashingList;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", visible=" + visible +
                ", batchSize=" + batchSize +
                ", efficiency=" + efficiency +
                ", estBoilSize=" + estBoilSize +
                ", estBoilTime=" + estBoilTime +
                ", fermentationDTO=" + fermentationDTO +
                ", yeastID=" + yeastID +
                ", grainList=" + grainList +
                ", hopStoreList=" + hopStoreList +
                ", mashingList=" + mashingList +
                '}';
    }
}
