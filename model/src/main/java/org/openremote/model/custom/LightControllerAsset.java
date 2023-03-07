/*
 * Copyright 2021, OpenRemote Inc.
 *
 * See the CONTRIBUTORS.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.openremote.model.custom;

import org.openremote.model.asset.Asset;
import org.openremote.model.asset.AssetDescriptor;
import org.openremote.model.attribute.MetaItem;
import org.openremote.model.value.*;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Optional;

import static org.openremote.model.Constants.UNITS_MINUTE;
import static org.openremote.model.Constants.UNITS_VOLT;

/**
 * This is an example of a custom {@link Asset} type; this must be registered via an
 * {@link org.openremote.model.AssetModelProvider} and must conform to the following requirements:
 *
 * <ul>
 * <li>Must have {@link Entity} annotation
 * <li>Optionally add {@link org.openremote.model.value.ValueDescriptor}s
 * <li>Optionally add {@link org.openremote.model.value.MetaItemDescriptor}s
 * <li>Optionally add {@link org.openremote.model.value.AttributeDescriptor}s
 * <li>Must have a public static final {@link org.openremote.model.asset.AssetDescriptor}
 * <li>Must have a protected no args constructor (for hydrators i.e. JPA/Jackson)
 * <li>For a given {@link Asset} type only one {@link org.openremote.model.asset.AssetDescriptor} can exist
 * <li>{@link org.openremote.model.value.AttributeDescriptor}s that override a super class descriptor cannot change the
 * value type; just the formatting etc.
 * <li>{@link org.openremote.model.value.MetaItemDescriptor}s names must be unique
 * <li>{@link org.openremote.model.value.ValueDescriptor}s names must be unique
 * </ul>
 */
@Entity
public class LightControllerAsset extends Asset<LightControllerAsset> {

    public enum ModeValueType {
        AUTO,
        ON,
        OFF
    }

    public static final ValueDescriptor<ModeValueType> MODE_VALUE_TYPE_VALUE_DESCRIPTOR =
            new ValueDescriptor<>("modeValueType", ModeValueType.class);

    public static final AttributeDescriptor<ModeValueType> MODE_VALUE_TYPE_ATTRIBUTE_DESCRIPTOR =
            new AttributeDescriptor<>("mode",
                    MODE_VALUE_TYPE_VALUE_DESCRIPTOR,
                    new MetaItem<>(MetaItemType.LABEL, "Controller Mode"));

    public static final AttributeDescriptor<String> IMEI =
            new AttributeDescriptor<>("imei",
                    ValueType.TEXT,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "IMEI"));

    public static final AttributeDescriptor<String> MAC_ADDRESS =
            new AttributeDescriptor<>("macAddress",
                    ValueType.TEXT,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "MAC Address"));

    public static final AttributeDescriptor<String> IP_ADDRESS =
            new AttributeDescriptor<>("ipAddress",
                    ValueType.IP_ADDRESS,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "IP Address"));

    public static final AttributeDescriptor<String> SERIAL_NR =
            new AttributeDescriptor<>("serialNr",
                    ValueType.TEXT,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "Serial Number"));

    public static final AttributeDescriptor<String> OS_VERSION =
            new AttributeDescriptor<>("osVersion",
                    ValueType.TEXT,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "OS Version"));

    public static final AttributeDescriptor<String> APP_VERSION =
            new AttributeDescriptor<>("appVersion",
                    ValueType.TEXT,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "App Version"));

    public static final AttributeDescriptor<Boolean> STATE =
            new AttributeDescriptor<>("state",
                    ValueType.BOOLEAN,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "State"))
                    .withFormat(new ValueFormat().setAsOnOff(false));

    public static final AttributeDescriptor<Boolean> UPDATE_ANALOG =
            new AttributeDescriptor<>("analogUpdate",
                    ValueType.BOOLEAN,
                    new MetaItem<>(MetaItemType.LABEL, "Update Analog Value"))
                    .withFormat(new ValueFormat().setAsMomentary(true));

    public static final AttributeDescriptor<Double> ANALOG_VALUE =
            new AttributeDescriptor<>("analogValue",
                    ValueType.NUMBER,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "Analog Value"))
                    .withUnits(UNITS_VOLT);

    public static final AttributeDescriptor<Double> ANALOG_THRESHOLD =
            new AttributeDescriptor<>("analogThreshold",
                    ValueType.NUMBER,
                    new MetaItem<>(MetaItemType.LABEL, "Set Analog Threshold"))
                    .withUnits(UNITS_VOLT)
                    .withConstraints(new ValueConstraint.Min(0), new ValueConstraint.Max(24))
                    .withFormat(new ValueFormat().setAsSlider(true));

    public static final AttributeDescriptor<Date> SUNRISE_TIME =
            new AttributeDescriptor<>("sunriseTime",
                    ValueType.DATE_AND_TIME,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "Sunrise Time"));

    public static final AttributeDescriptor<Integer> SUNRISE_OFFSET =
            new AttributeDescriptor<>("sunriseOffset",
                    ValueType.INTEGER,
                    new MetaItem<>(MetaItemType.LABEL, "Sunrise Offset"))
                    .withUnits(UNITS_MINUTE)
                    .withConstraints(new ValueConstraint.Min(-60), new ValueConstraint.Max(60))
                    .withFormat(new ValueFormat().setAsSlider(true));

    public static final AttributeDescriptor<Date> SUNSET_TIME =
            new AttributeDescriptor<>("sunsetTime",
                    ValueType.DATE_AND_TIME,
                    new MetaItem<>(MetaItemType.READ_ONLY),
                    new MetaItem<>(MetaItemType.LABEL, "Sunset Time"));

    public static final AttributeDescriptor<Integer> SUNSET_OFFSET =
            new AttributeDescriptor<>("sunsetOffset",
                    ValueType.INTEGER,
                    new MetaItem<>(MetaItemType.LABEL, "Sunset Offset"))
                    .withUnits(UNITS_MINUTE)
                    .withConstraints(new ValueConstraint.Min(-60), new ValueConstraint.Max(60))
                    .withFormat(new ValueFormat().setAsSlider(true));



    public static final AssetDescriptor<LightControllerAsset> LIGHT_CONTROLLER_ASSET_DESCRIPTOR =
            new AssetDescriptor<>("lightbulb", "0000aa", LightControllerAsset.class);

//    public Optional<Boolean> getOnOff() {
//        return getAttributes().getValue(UPDATE_ANALOG);
//    }
//
//    public LightControllerAsset setOnOff(Boolean value) {
//        getAttributes().getOrCreate(UPDATE_ANALOG).setValue(value);
//        return this;
//    }
//
//    public Optional<ModeValueType> getCustomAttribute() {
//        return getAttributes().getValue(MODE_VALUE_TYPE_ATTRIBUTE_DESCRIPTOR);
//    }
//
//    public LightControllerAsset setCustomAttribute(ModeValueType value) {
//        getAttributes().getOrCreate(MODE_VALUE_TYPE_ATTRIBUTE_DESCRIPTOR).setValue(value);
//        return this;
//    }
}
