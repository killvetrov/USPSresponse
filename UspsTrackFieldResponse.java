package com.letshindig.model.usps;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Killvetrov on 26/09/2017.
 */

@Root(name = "TrackResponse")
public class UspsTrackFieldResponse {

    public static final String USPS_STATUS_PRE_SHIPMENT = "Pre-Shipment";
    public static final String USPS_STATUS_ACCEPTED = "Accepted";
    public static final String USPS_STATUS_IN_TRANSIT = "In Transit";
    public static final String USPS_STATUS_OUT_FOR_DELIVERY = "Out for Delivery";
    public static final String USPS_STATUS_DELIVERED = "Delivered";

    @Root(strict = false)
    @Default(value = DefaultType.FIELD, required = false)
    public static class TrackInfo {

        @Root(strict = false)
        @Default(value = DefaultType.FIELD, required = false)
        public static class TrackDetail {
            String EventTime;
            String EventDate;
            String Event;
            String EventCity;
            String EventState;
            int EventZipCode;
            String EventCountry;
            String FirmName;
            String Name;
            boolean AuthorizedAgent;
            String EventCode;
            String EventStatusCategory;

            public String getEventCode() {
                return EventCode;
            }

            public String getEventStatusCategory() {
                return EventStatusCategory;
            }

            public String getEvent() {
                return Event;
            }
        }

        @Attribute
        String ID;

        String ExpectedDeliveryDate;
        String PredictedDeliveryDate;
        String Status;
        String StatusCategory;
        String StatusSummary;
        TrackDetail TrackSummary;
        @ElementList(entry = "TrackDetail", inline = true, required = false, empty = false)
        List<TrackDetail> trackDetailList;
    }

    @Element(name = "TrackInfo")
    TrackInfo trackInfo;

    public String getTrackId() {
        return trackInfo.ID;
    }

    public String getExpectedDeliveryDate() {
        return trackInfo.ExpectedDeliveryDate;
    }

    public String getPredictedDeliveryDate() {
        return trackInfo.PredictedDeliveryDate;
    }

    public String getStatus() {
        return trackInfo.Status;
    }

    public String getStatusCategory() {
        return trackInfo.StatusCategory;
    }

    public String getStatusSummary() {
        return trackInfo.StatusSummary;
    }

    public TrackInfo.TrackDetail getTrackSummary() {
        return trackInfo.TrackSummary;
    }

    public List<TrackInfo.TrackDetail> getTrackDetailList() {
        return trackInfo.trackDetailList;
    }
}
