# Cancer200 Api Android Package

## Summary
* The Cancer200Api Android Package is a binary Android library that allows a cut down version of the Cancer200 map and associated tracking tools to be imbedded into another app. It has a simple interface and is packaged as an .aar with separate both debug and release libraries.
* Included in this repo is an example app showing a simple integration.
* In addition to linking with the library there are some capabilities and other configuration that is required for the sdk to work imbedded in Android app because the sdk needs various location permissions and other Manifest configugration to function properly.  Also required are dependen Android open source libraries which can be seen in the demo project config.


## Requirements
* compileSdkVersion 33
* targetSdkVersion 33
* minSdkVersion 23


## Integration of the Api
See the demo app to see how to do it.  Currently the libraries are bundled with the demo app as 
cancer200sdk-debug.arr and
cancer200sdk-release.arr
Hosting these artifacts in a gradle repository is being investigated, but for now integrating directly
into the host app should be sufficient


## Integration Requirements

### Working Example
See the example app Cancer200SdkDemo for a working example:

**Cancer200SdkDemo** - A simple example which opens a MapActivity Activity as the main window
in an app.   Preferences and Routes Activities are launced by buttons on the top left of the Map Activity.  Resources and object code versions of the Activities are contained in the libary


### Initialisation of the Cancer200Api
The following initialsation code needs to be called in the onCreate() function of the apps MainActivity, in order so that the Api can be initialised for correct operation.


```
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init the Map Api
        Cancer200.initApi(this)
        
        // Other ...

    }
}
```

### Imbedded MapActivity
The main integration is via MapActivity public class from the Cancer200Api library.  An example can be seen in the test app. This MapViewController class expects that the Cancer200.initApi(mainActivity) has been called prior to its instantiation.

```kotlin
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init the Map Api
        Cancer200.initApi(this)

        // Launch the map
        val mapActivity = MapActivity()
        val mapIntent = Intent(this, MapActivity::class.java)
        startActivity(mapIntent)
    }
}
```

### AndoidManifest.xml Permission Configuration
A set of Mapping, Location and Service permissions are required for the Imbedded map an tracking functionality to work.

```
    <permission
        android:name="com.mapimagery.mapswim.permission.MAPS_RECEIVE"
        android:protectionLevel="signature" />

    <uses-permission android:name="com.mapimagery.mapswim.permission.MAPS_RECEIVE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        tools:ignore="ScopedStorage" />

    <!-- Required to show current location -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
```


### AndroidManifest.xml Location Forground Service Configuration

Configuration of the following Foreground Service in the AndroidManifest.xml of the main app is required for Foreground service for obtaining location when other apps are being run

```
       <service
            android:name="com.mapswim.cancer200sdk.LocationService"
            android:exported="true"
            android:foregroundServiceType="location">
        </service>
```

### AndroidManifest.xml configuration of Activities in the library

Map, Options and Route Activities need to be declared in the AndroidManifest.xml of the main app


```
       <activity
            android:name="com.mapswim.cancer200sdk.MapActivity"
            android:label="Map" />
        <activity
            android:name="com.mapswim.cancer200sdk.OptionsActivity"
            android:label="Options" />
        <activity
            android:name="com.mapswim.cancer200sdk.RoutesActivity"
            android:label="Routes" />
```

### Dependencies on third party and open source Android libraries

Please see the build.gradle file of the example app for a list of dependent packages.   This may be incorporated into a published gradle module at some point in the future.

A valid google-services.json is required as the library uses a number of google services.   You will have to generate this for your app.

## Google Maps API Key

A valid google maps API Key is required for the imbedded map to work

```
     <meta-data
            android:name="com.google.android.maps.v2.API_KEY"
            android:value="<YOURANDROIDMAPSV2APIKEY" />
     <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
```

## AndroidManifest.xml entry related to clear text traffic

The library uses clear text comunication to talk to the mapswim.net servers to send and recieve tracking information and location of riders on the map.   So the following AndroidManifest.xml property needs to be true

```
        android:usesCleartextTraffic="true"
```





## License
The **Cancer 200 Api** Android framework is the property of MapImagery Pty Ltd and should only be used with the explicit consent of MapImagery Pty Ltd.
