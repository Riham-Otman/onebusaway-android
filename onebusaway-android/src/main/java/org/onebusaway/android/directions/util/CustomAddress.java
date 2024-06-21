/*
 * Copyright 2012 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onebusaway.android.directions.util;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.geojson.Feature;
import org.geojson.Point;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by foucelhas on 18/08/14.
 */
public class CustomAddress extends Address {

    // Seems that subThoroughFare is street number and thoroughfare is street name.

    private static final int ADDRESS_MAX_LINES_TO_SHOW = 5;

    private boolean isTransitCategory = false;

    public CustomAddress(Locale locale) {
        super(locale);
    }

    public CustomAddress() {
        super(Locale.getDefault());
    }

    /**
     * Creates a CustomAddress out of a Android Address from the Android platform Geocoder API
     * @param address
     */
    public CustomAddress(Address address) {
        super(address.getLocale());
        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            super.setAddressLine(i, address.getAddressLine(i));
        }
        super.setFeatureName(address.getFeatureName());
        super.setAdminArea(address.getAdminArea());
        super.setSubAdminArea(address.getSubAdminArea());
        super.setLocality(address.getLocality());
        super.setSubLocality(address.getSubLocality());
        super.setThoroughfare(address.getThoroughfare());
        super.setSubThoroughfare(address.getSubThoroughfare());
        super.setPostalCode(address.getPostalCode());
        super.setCountryCode(address.getCountryCode());
        super.setCountryName(address.getCountryName());
        super.setLatitude(address.getLatitude());
        super.setLongitude(address.getLongitude());
        super.setPhone(address.getPhone());
        super.setUrl(address.getUrl());
        super.setExtras(address.getExtras());
    }

    /**
     * Creates a CustomAddress out of a GeoJSON Feature (e.g., from Pelias Autocomplete API)
     * @param address
     */
    public CustomAddress(Feature address) {
        super(Locale.getDefault());

        // TODO - see if we need to fill out more fields
        // For example response with fields see https://github.com/CUTR-at-USF/pelias-client-library/blob/master/src/test/resources/autocomplete-with-focus.json
        // and https://github.com/CUTR-at-USF/pelias-client-library/blob/master/src/test/java/edu/usf/cutr/pelias/AutocompleteTest.java#L42
        super.setAddressLine(0, (String) address.getProperties().get("name"));
        super.setFeatureName((String) address.getProperties().get("label"));
        super.setPostalCode((String) address.getProperties().get("postalcode"));
        super.setCountryName((String) address.getProperties().get("country"));

        Point p = (Point) address.getGeometry();
        super.setLatitude(p.getCoordinates().getLatitude());
        super.setLongitude(p.getCoordinates().getLongitude());

        // Check if the geocoder marked this location as having a public transportation category
        ArrayList<String> categories = address.getProperty("category");
        if (categories != null) {
            for (String category : categories) {
                if (category.equalsIgnoreCase("transport:public")) {
                    isTransitCategory = true;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Prioritize feature name if available
        if (getFeatureName() != null) {
            sb.append(getFeatureName());
        }// Append other address components if they are not the same as the feature name
        appendIfDifferent(sb, ", ", getSubThoroughfare());
        appendIfDifferent(sb, " ", getThoroughfare());
        appendIfDifferent(sb, ", ", getSubAdminArea());
        appendIfDifferent(sb, ", ", getLocality());

        // If no components were added, use address lines (up to a limit)
        if (sb.length() == 0) {
            int maxLines = Math.min(ADDRESS_MAX_LINES_TO_SHOW, getMaxAddressLineIndex()+ 1);
            for (int i = 0; i < maxLines; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(getAddressLine(i));
            }
        }

        return sb.toString();
    }

    // Helper method to append a string if it's different from the existing content
    private void appendIfDifferent(StringBuilder sb, String prefix, String value) {
        if (value != null && !value.equals(getFeatureName())) {
            sb.append(prefix).append(value);
        }
    }

    public static final Parcelable.Creator<CustomAddress> CREATOR =
            new Parcelable.Creator<CustomAddress>() {
                public CustomAddress createFromParcel(Parcel in) {
                    String language = in.readString();
                    String country = in.readString();
                    Locale locale = country.length() > 0 ?
                            new Locale(language, country) :
                            new Locale(language);
                    CustomAddress a = new CustomAddress(locale);

                    int N = in.readInt();
                    if (N > 0) {
                        for (int i = 0; i < N; i++) {
                            int index = in.readInt();
                            String line = in.readString();
                            a.setAddressLine(index, line);
                        }
                    }

                    a.setFeatureName(in.readString());
                    a.setAdminArea(in.readString());
                    a.setSubAdminArea(in.readString());
                    a.setLocality(in.readString());
                    a.setSubLocality(in.readString());
                    a.setThoroughfare(in.readString());
                    a.setSubThoroughfare(in.readString());
                    a.setPremises(in.readString());
                    a.setPostalCode(in.readString());
                    a.setCountryCode(in.readString());
                    a.setCountryName(in.readString());
                    boolean mHasLatitude = in.readInt() != 0;
                    if (mHasLatitude) {
                        a.setLatitude(in.readDouble());
                    }
                    boolean mHasLongitude = in.readInt() != 0;
                    if (mHasLongitude) {
                        a.setLongitude(in.readDouble());
                    }
                    a.setPhone(in.readString());
                    a.setUrl(in.readString());
                    a.setExtras(in.readBundle());
                    return a;
                }

                public CustomAddress[] newArray(int size) {
                    return new CustomAddress[size];
                }
            };

    /**
     * Is this custom address set.
     *
     * @return true if this address has a valid latitude and longitude
     */
    public boolean isSet() {
        return getLatitude() != Double.MAX_VALUE && getLongitude() != Double.MAX_VALUE;
    }

    /**
     * Create a blank CustomAddress.
     *
     * @return CustomAddress with default locale and unset latitude and longitude.
     */
    public static CustomAddress getEmptyAddress() {
        Locale locale = Locale.getDefault();
        CustomAddress addr = new CustomAddress(locale);
        addr.setLatitude(Double.MAX_VALUE);
        addr.setLongitude(Double.MAX_VALUE);
        return addr;
    }

    /**
     * Return true if this location has been labeled under the category of "public transportation",
     * false if it has not
     *
     * @return true if this location has been labeled under the category of "public transportation",
     * false if it has not
     */
    public boolean isTransitCategory() {
        return isTransitCategory;
    }
}
