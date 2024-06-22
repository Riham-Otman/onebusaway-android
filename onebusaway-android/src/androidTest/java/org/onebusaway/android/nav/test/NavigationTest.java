package org.onebusaway.android.nav.test;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.onebusaway.android.util.TestUtils.isRunningOnCI;

import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import org.apache.commons.io.IOUtils;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onebusaway.android.io.test.ObaTestCase;
import org.onebusaway.android.mock.Resources;
import org.onebusaway.android.nav.NavigationServiceProvider;
import org.onebusaway.android.nav.model.Path;
import org.onebusaway.android.nav.model.PathLink;
import org.onebusaway.android.util.LocationUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Loads previously recorded trips (GPS data) and replays them through the NavigationServiceProvider
 * via the NavigationSimulation class.
 *
 * For creating new test methods - see the file DESTINATION_REMINDERS.md
 */
@RunWith(AndroidJUnit4.class)
public class NavigationTest extends ObaTestCase {

    private static final String TAG = "NavigationTest";
    private static final long SPEED_UP = 1000000L;

    private static void checkCI() {
        if (isRunningOnCI()) {
            Assume.assumeTrue("Skipping NavigationTest tests - this is expected behavior when running on CI (#1010,#1072)", false);
        }
    }

    private void runSimulation(String csvFileName, int expectedGetReadyIndex, int expectedPullCordIndex) throws IOException {
        Reader reader = Resources.read(getTargetContext(), Resources.getTestUri(csvFileName));
        String csv = IOUtils.toString(reader);
        NavigationSimulation trip = new NavigationSimulation(csv);
        trip.runSimulation(expectedGetReadyIndex, expectedPullCordIndex);
    }

    @Test
    public void testTrip1() throws IOException {
        checkCI();
        runSimulation("nav_trip1", 848, 978);
    }

    @Test
    public void testTrip1C() throws IOException {
        checkCI();
        runSimulation("nav_trip1c", 665, 929);
    }

    @Test
    public void testTrip2() throws IOException {
        checkCI();
        runSimulation("nav_trip2", 0, 15);
    }

    @Test
    public void testTrip2C() throws IOException {
        runSimulation("nav_trip2c", 0, 64);
    }

    @Test
    public void testTrip3() throws IOException {
        checkCI();
        runSimulation("nav_trip3", 95, 111);
    }

    @Test
    public void testTrip3C() throws IOException {
        checkCI();
        runSimulation("nav_trip3c", 87, 109);
    }

    @Test
    public void testTrip4() throws IOException {
        checkCI();
        runSimulation("nav_trip4", 294, 329);
    }

    @Test
    public void testTrip4C() throws IOException {
        checkCI();
        runSimulation("nav_trip4c", 253, 329);
    }

    @Test
    public void testTrip5() throws IOException {
        checkCI();
        runSimulation("nav_trip5", 372, 660);
    }

    @Test
    public void testTrip5C() throws IOException {
        checkCI();
        runSimulation("nav_trip5c", 284, 492);
    }

    @Test
    public void testTrip6() throws IOException {
        checkCI();
        runSimulation("nav_trip6", 801, 837);
    }

    @Test
    public void testTrip6C() throws IOException {
        checkCI();
        runSimulation("nav_trip6c", 676, 704);
    }

    @Test
    public void testTrip7() throws IOException {
        checkCI();
        runSimulation("nav_trip7", 183, 208);
    }

    @Test
    public void testTrip7C() throws IOException {
        checkCI();
        runSimulation("nav_trip7c", 172, 285);
    }

    @Test
    public void testTrip8() throws IOException {
        checkCI();
        runSimulation("nav_trip8", 0, 18);
    }

    @Test
    public void testTrip8C() throws IOException {
        checkCI();
        runSimulation("nav_trip8c", 0, 20);
    }

    @Test
    public void testTrip9() throws IOException {
        checkCI();
        runSimulation("nav_trip9", 1041, 1071);
    }

    @Test
    public void testTrip9C() throws IOException {
        checkCI();
        runSimulation("nav_trip9c", 570, 634);
    }

    @Test
    public void testTrip10() throws IOException {
        checkCI();
        runSimulation("nav_trip10", 589, 605);
    }

    @Test
    public void testTrip10C() throws IOException {
        checkCI();
        runSimulation("nav_trip10c", 484, 509);
    }

    @Test
    public void testTrip11() throws IOException {
        checkCI();
        runSimulation("nav_trip11", 331, 371);
    }

    @Test
    public void testTrip11C() throws IOException {
        checkCI();
        runSimulation("nav_trip11c", 119, 158);
    }

    @Test
    public void testTrip12() throws IOException {
        checkCI();
        runSimulation("nav_trip12", 69, 199);
    }

    @Test
    public void testTrip12C() throws IOException {
        checkCI();
        runSimulation("nav_trip12c", 880, 901);
    }

    @Test
    public void testTrip13() throws IOException {
        checkCI();
        runSimulation("nav_trip13", 2549, 2732);
    }

    @Test
    public void testTrip13C() throws IOException {
        checkCI();
        runSimulation("nav_trip13c", 230, 519);
    }

    @Test
    public void testTrip14() throws IOException {
        checkCI();
        runSimulation("nav_trip14", 548, 571);
    }

    @Test
    public void testTrip14C() throws IOException {
        checkCI();
        runSimulation("nav_trip14c", 387, 429);
    }

    @Test
    public void testTrip15() throws IOException {
        checkCI();
        runSimulation("nav_trip15", 225, 417);
    }

    @Test
    public void testTrip15C() throws IOException {
        checkCI();
        runSimulation("nav_trip15c", 217, 287);
    }

    @Test
    public void testTrip16() throws IOException {
        checkCI();
        runSimulation("nav_trip16", 142, 192);
    }

    @Test
    public void testTrip16C() throws IOException {
        checkCI();
        runSimulation("nav_trip16c", 121, 168);
    }

    @Test
    public void testTrip17() throws IOException {
        checkCI();
        runSimulation("nav_trip17", 100, 181);
    }

    @Test
    public void testTrip18() throws IOException {
        checkCI();
        runSimulation("nav_trip18", 31, 51);
    }

    @Test
    public void testTrip18C() throws IOException {
        checkCI();
        runSimulation("nav_trip18c", 31, 58);
    }

    @Test
    public void testTrip20() throws IOException {
        checkCI();
        runSimulation("nav_trip20", 133, 195);
    }

    @Test
    public void testTrip20C() throws IOException {
        checkCI();
        runSimulation("nav_trip20c", 53, 117);
    }

    @Test
    public void testTrip21() throws IOException {
        checkCI();
        runSimulation("nav_trip21", 547, 686);
    }

    @Test
    public void testTrip21C() throws IOException {
        checkCI();
        runSimulation("nav_trip21c", 323, 402);
    }

    @Test
    public void testTrip22() throws IOException {
        checkCI();
        runSimulation("nav_trip22", 1085, 1198);
    }

    @Test
    public void testTrip22C() throws IOException {
        checkCI();
        runSimulation("nav_trip22c", 1032, 1099);
    }

    @Test
    public void testTrip23() throws IOException {
        checkCI();
        runSimulation("nav_trip23", 0, 24);
    }

    @Test
    public void testTrip23C() throws IOException {
        checkCI();
        runSimulation("nav_trip23c", 0, 19);
    }

    @Test
    public void testTrip24() throws IOException {
        checkCI();
        runSimulation("nav_trip24", 115, 178);
    }

    @Test
    public void testTrip24C() throws IOException {
        checkCI();
        runSimulation("nav_trip24c", 203, 262);
    }

    @Test
    public void testTrip25() throws IOException {
        checkCI();
        runSimulation("nav_trip25", 209, 235);
    }

    @Test
    public void testTrip25C() throws IOException {
        checkCI();
        runSimulation("nav_trip25c", 217, 247);
    }

    @Test
    public void testTrip26() throws IOException {
        checkCI();
        runSimulation("nav_trip26", 367, 463);
    }

    @Test
    public void testTrip27() throws IOException {
        checkCI();
        runSimulation("nav_trip27", 909, 944);
    }

    @Test
    public void testTrip28() throws IOException {
        checkCI();
        runSimulation("nav_trip28", 400, 473);
    }

    @Test
    public void testTrip29() throws IOException {
        checkCI();
        runSimulation("nav_trip29", 472, 594);
    }

    @Test
    public void testTrip30() throws IOException {
        checkCI();
        runSimulation("nav_trip30", 1001, 1048);
    }

    @Test
    public void testTrip31() throws IOException {
        checkCI();
        runSimulation("nav_trip31", 2508, 2654);
    }

    @Test
    public void testTrip32() throws IOException {
        checkCI();
        runSimulation("nav_trip32", 1847, 1888);
    }

    @Test
    public void testTrip33() throws IOException {
        checkCI();
        runSimulation("nav_trip33", 335, 401);
    }

    // Class for holding relevant details for testing.
    class NavigationSimulation {

        String mTripId;
        String mDestinationId;
        String mBeforeId;
        Location mDestinationLocation;
        Location mSecondToLastLocation;
        Location[] mLocations;
        long[] mTimes;
        int mAutomatedGetReadyIndex = -1;
        boolean useElapsedNanos = false;

        /**
         * Loads recorded user location data into the simulation for testing navigation.  Recorded user
         * data simulates a real-time trip.
         *
         * @param csv string with the above format
         */
        NavigationSimulation(String csv) {
            String[] lines = csv.split("\n");

            // Setup meta data.
            String[] details = lines[0].split(",");
            mTripId = details[0];
            mDestinationId = details[1];
            mBeforeId = details[4];

            mDestinationLocation = new Location(LocationManager.GPS_PROVIDER);
            mDestinationLocation.setLatitude(Double.parseDouble(details[2]));
            mDestinationLocation.setLongitude(Double.parseDouble(details[3]));

            mSecondToLastLocation = new Location(LocationManager.GPS_PROVIDER);
            mSecondToLastLocation.setLatitude(Double.parseDouble(details[5]));
            mSecondToLastLocation.setLongitude(Double.parseDouble(details[6]));

            mLocations = new Location[lines.length - 1];

            // Skip header and run through csv.
            // Rows are formatted like this:
            // realtime Nanos Elapsed,time,lat,lng,altitude,speed,bearing,accuracy,satellites,provider.
            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(",");
                String provider = values[12];

                mLocations[i - 1] = new Location(provider);

                if (!values[3].equals("") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    useElapsedNanos = true;
                    mLocations[i - 1].setElapsedRealtimeNanos(Long.parseLong(values[3]));
                }

                mLocations[i - 1].setTime(Long.parseLong(values[4]));
                mLocations[i - 1].setLatitude(Double.parseDouble(values[5]));
                mLocations[i - 1].setLongitude(Double.parseDouble(values[6]));
                mLocations[i - 1].setAltitude(Double.parseDouble(values[7]));
                mLocations[i - 1].setBearing(Float.parseFloat(values[9]));
                mLocations[i - 1].setAccuracy(Float.parseFloat(values[10]));
                mLocations[i - 1].setSpeed(Float.parseFloat(values[8]));
            }

            // Compute index of point nearest to second to last stop.
            double minDist = Double.MAX_VALUE;
            for (int i = 0; i < mLocations.length; i++) {
                if (mLocations[i].distanceTo(mSecondToLastLocation) < minDist) {
                    minDist = mLocations[i].distanceTo(mSecondToLastLocation);
                    mAutomatedGetReadyIndex = i;
                }
            }

            // Compute time differences between readings in ms, if realtime ns is available, use it
            // else use getTime.
            mTimes = new long[mLocations.length];
            if (useElapsedNanos && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                for (int i = 1; i < mLocations.length; i++) {
                    mTimes[i] = (mLocations[i].getElapsedRealtimeNanos() - mLocations[i - 1].getElapsedRealtimeNanos()) / 1000000;
                }
            } else {
                for (int i = 1; i < mLocations.length; i++) {
                    mTimes[i] = mLocations[i].getTime() - mLocations[i - 1].getTime();
                }
            }
        }

        void runSimulation(int expectedGetReadyIndex, int expectedPullCordIndex) {
            NavigationServiceProvider provider = new NavigationServiceProvider(mTripId, mDestinationId);
            Location prevLocation = null;
            // Use the first location time as the starting time for this PathLink
            // TODO - capture PathLink nav starting time in logs
            PathLink link = new PathLink(mLocations[0].getTime(), null, mSecondToLastLocation, mDestinationLocation, mTripId);

            // Begin navigation & simulation for a single path link
            provider.navigate(new Path(new ArrayList<>(Collections.singletonList(link))));

            for (int i = 0; i <= expectedGetReadyIndex; i++) {
                Location l = mLocations[i];

                try {
                    Thread.sleep(mTimes[i] / SPEED_UP);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

                if (prevLocation == null || !LocationUtils.isDuplicate(prevLocation, l)) {
                    provider.locationUpdated(l);
                }
                prevLocation = l;

                if (provider.getGetReady() && i < expectedGetReadyIndex) {
                    fail("Get ready triggered too soon");
                }
                if (provider.getGetReady() && i < expectedGetReadyIndex) {
                    fail("Get ready triggered too soon");
                }

                Log.d(TAG, String.format("%d: (%f, %f, %f)\tR:%s  F:%s", i,
                        l.getLatitude(), l.getLongitude(), l.getSpeed(),
                        Boolean.toString(provider.getGetReady()),
                        Boolean.toString(provider.getFinished())));

            }

            Boolean check1 = provider.getGetReady() && !provider.getFinished();
            assertTrue(check1);

            if(expectedGetReadyIndex != 0){
                prevLocation = mLocations[expectedGetReadyIndex - 1];
            }

            for (int i = expectedGetReadyIndex; i <= expectedPullCordIndex; i++) {
                Location l = mLocations[i];
                try {
                    Thread.sleep((mTimes[i] / SPEED_UP));
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

                // Code added to check for duplicate locations in the .csv log files
                if (prevLocation == null || !LocationUtils.isDuplicate(prevLocation, l)) {
                    provider.locationUpdated(l);
                }
                prevLocation = l;

                if (provider.getFinished() && i < expectedPullCordIndex) {
                    fail("Pull the Cord triggered too soon");
                }

                Log.d(TAG, String.format("%d: (%f, %f, %f)\tR:%s  F:%s", i,
                        l.getLatitude(), l.getLongitude(), l.getSpeed(),
                        Boolean.toString(provider.getGetReady()),
                        Boolean.toString(provider.getFinished())));
            }

            Boolean check2 = provider.getGetReady() && provider.getFinished();
            assertTrue(check2);
        }
    }
}



