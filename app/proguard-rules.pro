# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in I:\android studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *;}

-dontwarn com.rengwuxian.materialedittext.**
-keep class com.rengwuxian.materialedittext.** { *;}


-dontwarn de.hdodenhof.circleimageview.**
-keep class de.hdodenhof.circleimageview.** { *;}


-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties

-keep class org.jsoup.**

-dontwarn com.igexin.**

-keep class com.igexin.**{*;}
#
# compile 'com.rengwuxian.materialedittext:library:1.7.1'
#    compile 'de.hdodenhof:circleimageview:1.2.1'
#    compile files('libs/greendao-1.3.7.jar')
#    compile files('libs/xcl-charts.jar')
#    compile files('libs/greendao-generator-1.3.1.jar')
#    compile files('libs/freemarker-2.3.21.jar')
#    compile 'com.android.support:cardview-v7:21.0.3'
#    compile 'com.android.support:recyclerview-v7:21.0.3'
#    compile 'fr.opensagres.xdocreport:fr.opensagres.xdocreport.openoffice.macro:1.0.4'
#    compile 'com.nineoldandroids:library:2.4.0'
#    compile 'com.daimajia.easing:library:1.0.0@aar'
#    compile 'com.daimajia.androidanimations:library:1.1.2@aar'
#    compile 'org.jsoup:jsoup:1.8.1'
#    compile 'com.github.eluleci:flatui:3.0.0'
