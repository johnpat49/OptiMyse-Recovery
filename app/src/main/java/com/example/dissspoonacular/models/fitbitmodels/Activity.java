package com.example.dissspoonacular.models.fitbitmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.List;
/**
 * Model of the Fitbit Activity Response.
 */
public class Activity {

    @SerializedName("activeDuration")
    @Expose
    private Integer activeDuration;
    @SerializedName("activityLevel")
    @Expose
    private List<ActivityLevel> activityLevel = null;
    @SerializedName("activityName")
    @Expose
    private String activityName;
    @SerializedName("activityTypeId")
    @Expose
    private Integer activityTypeId;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("hasActiveZoneMinutes")
    @Expose
    private Boolean hasActiveZoneMinutes;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("logId")
    @Expose
    private BigInteger logId;
    @SerializedName("logType")
    @Expose
    private String logType;
    @SerializedName("manualValuesSpecified")
    @Expose
    private ManualValuesSpecified manualValuesSpecified;
    @SerializedName("originalDuration")
    @Expose
    private Integer originalDuration;
    @SerializedName("originalStartTime")
    @Expose
    private String originalStartTime;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("steps")
    @Expose
    private Integer steps;

    public Integer getActiveDuration() {
        return activeDuration;
    }

    public void setActiveDuration(Integer activeDuration) {
        this.activeDuration = activeDuration;
    }

    public List<ActivityLevel> getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(List<ActivityLevel> activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getHasActiveZoneMinutes() {
        return hasActiveZoneMinutes;
    }

    public void setHasActiveZoneMinutes(Boolean hasActiveZoneMinutes) {
        this.hasActiveZoneMinutes = hasActiveZoneMinutes;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public BigInteger getLogId() {
        return logId;
    }

    public void setLogId(BigInteger logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public ManualValuesSpecified getManualValuesSpecified() {
        return manualValuesSpecified;
    }

    public void setManualValuesSpecified(ManualValuesSpecified manualValuesSpecified) {
        this.manualValuesSpecified = manualValuesSpecified;
    }

    public Integer getOriginalDuration() {
        return originalDuration;
    }

    public void setOriginalDuration(Integer originalDuration) {
        this.originalDuration = originalDuration;
    }

    public String getOriginalStartTime() {
        return originalStartTime;
    }

    public void setOriginalStartTime(String originalStartTime) {
        this.originalStartTime = originalStartTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }


}
