package com.letshindig.model.usps;

import org.simpleframework.xml.Default;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Root;

/**
 * Created by Killvetrov on 26/09/2017.
 */

@Root(name = "Error", strict = false)
@Default(value = DefaultType.FIELD, required = false)
public class UspsErrorResponse {
    String Number;
    String Source;
    String Description;
    String HelpFile;
    String HelpContext;

    public String getNumber() {
        return Number;
    }

    public String getSource() {
        return Source;
    }

    public String getDescription() {
        return Description;
    }

    public String getHelpFile() {
        return HelpFile;
    }

    public String getHelpContext() {
        return HelpContext;
    }
}
