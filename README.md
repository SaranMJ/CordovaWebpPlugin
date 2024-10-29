Description.

The IMGCMPBase64 plugin allows developers to convert images from Base64 format to WebP format in their Cordova Ionic applications. This can help optimize image sizes, leading to improved performance and faster loading times.


Installation

To install the plugin, run the following command:
```
 npm i cordova-plugin-image-compression
```

Usage,
```
Convert Base64 to WebP
You can convert a Base64 encoded image to WebP format using the following method: 

 var base64Image = "data:image/jpeg;base64,..."; // Your Base64 string here
IMGCMPBase64.convertToWebP(base64Image, function(result) {
    console.log("Converted WebP Data: ", result.data);
}, function(error) {
    console.error("Error: ", error);
});

```

API Reference
```
convertToWebP(base64ImageString, successCallback, errorCallback): Converts a Base64 encoded image string to WebP format.
       base64ImageString: The Base64 string of the image you want to convert.
       successCallback: Callback function executed upon successful conversion.
       errorCallback: Callback function executed if there is an error during conversion.
```
Contact

For any inquiries or support, you can reach me.
