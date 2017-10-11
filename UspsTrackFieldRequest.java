package com.letshindig.model.usps;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by Killvetrov on 26/09/2017.
 */

@Root(name = "TrackFieldRequest")
@Order(elements = {
        "Revision",
        "ClientIp",
        "SourceId",
        "TrackID"
})
public class UspsTrackFieldRequest {

    @Root(name = "TrackID")
    public static class TrackId {
        @Attribute(name = "ID")
        String id;

        public TrackId(String id) {
            this.id = id;
        }
    }

    @Attribute(name = "USERID")
    String userId;
    @Element(name = "Revision")
    int revision;
    @Element(name = "ClientIp")
    String clientIp;
    @Element(name = "SourceId")
    String sourceId;
    @Element(name = "TrackID")
    TrackId trackId;

    public UspsTrackFieldRequest(String userId, String trackId) {
        this.userId = userId;
        this.revision = 1;
        this.clientIp = "127.0.0.1";
        this.sourceId = "USPSTOOLS";
        this.trackId = new TrackId(trackId);
    }
}
