# Android Films List Sample Project (Assessment Task)

[![N|Solid](https://cdn1.imggmi.com/uploads/2019/2/10/56a2a12b1abab60f46270c96743d9a97-full.png)](https://cdn1.imggmi.com/uploads/2019/2/10/56a2a12b1abab60f46270c96743d9a97-full.png)

This Android project contains sample APIs integration with sample UI, APIs documentation is here [Document Link][df1], main purpose to develop this project is to explain architecture approach and patterns used.  

## What's Inside

Lets start :)

## App UI
[![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-15.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-15.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-16.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-16.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-19.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-19.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-22.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-22.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-27.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-27.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-31.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-31.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-42.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-42.png) [![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-46.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/Screenshot_2019-02-11-01-43-46.png)

## Features

* Home screen with films list
* Film detail screen
* Location screen
* People screen
* Vehicles screen
* Species screen
* Sample UI test case
* Sample unit test

### Architecture Design Pattern

Design pattern used in this project is ```MVC```. The model, view, controller approach separates your application at a macro level into 3 sets of responsibilities.

#### Model
The model is the Data + State + Business logic of our application. It’s the brains of our application. It is not tied to the view or controller, and because of this, it is reusable in many contexts.

### View
The view is the Representation of the Model. The view has a responsibility to render the User Interface (UI) and communicate to the controller when the user interacts with the application. In ```MVC``` architecture, Views are generally pretty “dumb” in that they have no knowledge of the underlying model and no understanding of state or what to do when a user interacts by clicking a button, typing a value, etc. The idea is that the less they know the more loosely coupled they are to the model and therefore the more flexible they are to change.

### Controller
The controller is Glue that ties the app together. It’s the master controller for what happens in the application. When the View tells the controller that a user clicked a button, the controller decides how to interact with the model accordingly. Based on data changing in the model, the controller may decide to update the state of the view as appropriate. In the case of an Android application, the controller is almost always represented by an Activity or Fragment.

### Diagram 
[![N|Solid](https://github.com/harisazam/Android_MoviesList/blob/master/screens/diagram.png)](https://github.com/harisazam/Android_MoviesList/blob/master/screens/diagram.png)

# Gradel
### SDK Support
Support from ```SDK version 17``` onwards

### Libraries Used
```ruby
dependencies {
	/*LIBRARY FOR SPLASH ANIMATION*/
    implementation'com.github.ViksaaSkool:AwesomeSplash:v1.0.0'
    
    /*LIBRARY FOR SCALE ABLE SCREEN SIZES*/
    implementation 'com.intuit.sdp:sdp-android:1.0.6'

    /*LIBRARY FOR VIEW BINDING*/
    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'

    /*LIBRARY FOR NETWORK CALLS*/
    implementation 'com.amitshekhar.android:android-networking:1.0.0'

    /*LIBRARY TO SHOW  SKELETON LOADING VIEW */
    implementation 'com.ethanhua:skeleton:1.1.2'
    implementation 'io.supercharge:shimmerlayout:2.1.0'

    /*LIBRARY FOR UNIT TESTING*/
    androidTestImplementation 'com.android.support.test:rules:1.0.2'
    testImplementation 'com.android.support.test:rules:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:3.0.2'
    testImplementation 'org.mockito:mockito-core:1.10.19'
    testImplementation 'com.squareup.okhttp3:mockwebserver:3.13.1'
	}
```
### AwesomeSplash
Library used for customizable animation work on ```Splash Screen```
### SDP
An android SDK that provides a new size unit - sdp (scalable dp). This size unit scales with the screen size. It help in support of multiple screens sizes.
### Butterknife
Library for field and method binding for Android views which uses annotation processing.
### Android-Networking
Fast Android Networking Library is a powerful library for doing any type of networking in Android applications which is made on top of OkHttp Networking Layer
### Skeleton
The library provides an easy way to show skeleton loading view like Facebook and Alipay. It now uses a memory optimised version of shimmer animation so it is even faster.
### Espresso
Espresso to write concise, beautiful, and reliable Android UI tests.
### Mockito
Mockito to write Android unit tests.
##  Version
Current Version ```1.0```

## Developed By
Harris Azam.

  [df1]: <https://ghibliapi.herokuapp.com/>
