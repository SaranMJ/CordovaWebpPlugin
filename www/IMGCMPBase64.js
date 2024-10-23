var exec = require('cordova/exec');

var IMGCMPBase64 = {
    exampleMethod: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'IMGCMPBase64', 'exampleMethod', []);
    },
    convertToWebP:function(base64ImageString, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, 'IMGCMPBase64', 'convertToWebP', [base64ImageString]);
	}
}

module.exports = IMGCMPBase64;