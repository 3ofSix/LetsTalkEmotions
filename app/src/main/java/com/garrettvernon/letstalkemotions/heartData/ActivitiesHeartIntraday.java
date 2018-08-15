package com.garrettvernon.letstalkemotions.heartData;


public class ActivitiesHeartIntraday {
    private String datasetType;

    private Dataset[] dataset;

    private String datasetInterval;

    public String getDatasetType() {
        return datasetType;
    }

    public void setDatasetType(String datasetType) {
        this.datasetType = datasetType;
    }

    public Dataset[] getDataset() {
        return dataset;
    }

    public void setDataset(Dataset[] dataset) {
        this.dataset = dataset;
    }

    public String getDatasetInterval() {
        return datasetInterval;
    }

    public void setDatasetInterval(String datasetInterval) {
        this.datasetInterval = datasetInterval;
    }

    @Override
    public String toString() {
        return "ClassPojo [datasetType = " + datasetType + ", dataset = " + dataset + ", datasetInterval = " + datasetInterval + "]";
    }
}
