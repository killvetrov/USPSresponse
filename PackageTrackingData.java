package com.letshindig.model.tracking;

import com.letshindig.model.usps.UspsTrackFieldResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Killvetrov on 07/10/2017.
 */

public class PackageTrackingData
        implements PackageTrackingDataSource {

    public static final int SHIPMENT_STAGE_UNKNOWN = 0;
    public static final int SHIPMENT_STAGE_PRE_SHIPMENT = 1;
    public static final int SHIPMENT_STAGE_ACCEPTED = 2;
    public static final int SHIPMENT_STAGE_IN_TRANSIT = 3;
    public static final int SHIPMENT_STAGE_READY_FOR_DELIVERY = 4;
    public static final int SHIPMENT_STAGE_DELIVERED = 5;

    private String trackId;
    private String expectedDeliveryDate;
    private String shippingService;
    private String shipmentStatus;
    private String shipmentStatusDetail;
    private String shipmentStatusSummary;
    private int shipmentStageCode;

    public static PackageTrackingData from(UspsTrackFieldResponse uspsTrackResponse) {
        PackageTrackingData data = new PackageTrackingData();
        data.trackId = uspsTrackResponse.getTrackId();
        data.expectedDeliveryDate = uspsTrackResponse.getExpectedDeliveryDate() != null
                ? uspsTrackResponse.getExpectedDeliveryDate()
                : uspsTrackResponse.getPredictedDeliveryDate();
        data.shippingService = "USPS";
        data.shipmentStatus = uspsTrackResponse.getStatusCategory();
        data.shipmentStatusDetail = uspsTrackResponse.getStatus();
        data.shipmentStatusSummary = uspsTrackResponse.getStatusSummary();

        data.shipmentStageCode = stageCodeForStatusString(data.shipmentStatus);
        if (data.shipmentStageCode == SHIPMENT_STAGE_UNKNOWN)
            data.shipmentStageCode = getLatestKnownStageCode(uspsTrackResponse.getTrackDetailList());

        return data;
    }

    private static int getLatestKnownStageCode(List<UspsTrackFieldResponse.TrackInfo.TrackDetail> trackEventList) {
        for (UspsTrackFieldResponse.TrackInfo.TrackDetail event : trackEventList) {
            int code = stageCodeForStatusString(event.getEventStatusCategory());
            if (code != SHIPMENT_STAGE_UNKNOWN) {
                return code;
            }
        }
        return SHIPMENT_STAGE_UNKNOWN;
    }

    private boolean statusEquals(String statusString) {
        return statusString.equalsIgnoreCase(shipmentStatus);
    }

    private static int stageCodeForStatusString(String statusString) {
        if (UspsTrackFieldResponse.USPS_STATUS_DELIVERED.equalsIgnoreCase(statusString))
            return SHIPMENT_STAGE_DELIVERED;
        else if (UspsTrackFieldResponse.USPS_STATUS_OUT_FOR_DELIVERY.equalsIgnoreCase(statusString))
            return SHIPMENT_STAGE_READY_FOR_DELIVERY;
        else if (UspsTrackFieldResponse.USPS_STATUS_IN_TRANSIT.equalsIgnoreCase(statusString))
            return SHIPMENT_STAGE_IN_TRANSIT;
        else if (UspsTrackFieldResponse.USPS_STATUS_ACCEPTED.equalsIgnoreCase(statusString))
            return SHIPMENT_STAGE_ACCEPTED;
        else if (UspsTrackFieldResponse.USPS_STATUS_PRE_SHIPMENT.equalsIgnoreCase(statusString))
            return SHIPMENT_STAGE_PRE_SHIPMENT;
        else
            return SHIPMENT_STAGE_UNKNOWN;
    }

    @Override
    public String getTrackId() {
        return trackId;
    }

    @Override
    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    @Override
    public String getShippingService() {
        return shippingService;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public String getShipmentStatusDetail() {
        return shipmentStatusDetail;
    }

    public String getShipmentStatusSummary() {
        return shipmentStatusSummary;
    }

    @Override
    public int getShipmentStageCode() {
        return shipmentStageCode;
    }

}
