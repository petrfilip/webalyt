//todo vyčlenit do modulu zvlášť -- je to core-webalyt d
var wpo_fp = [];
new Fingerprint2().get(function (result, components) {
    var fpo = {};
    fpo.fp = result;
    //fpo.t = new Date();
    wpo_fp.push(fpo);
});


//base method
function mergeData(obj, plugin, message) {
    if (message !== undefined && message.length > 0) {
        obj[plugin] = message;
    }
    return obj;
}

//base method
setInterval(function () {
    var obj = {};

    obj = mergeData(obj, "cp", wpc_click_tracker);
    obj = mergeData(obj, "mp", wpc_mouseposition);
    obj = mergeData(obj, "di", di);
    obj = mergeData(obj, "lpi", lpi);
    sendData(obj);
}, 3000);


//base method
function sendData(obj) {
    if (!isEmpty(obj)) {
        //persistent field
        obj = mergeData(obj, "WCdevId", wpo_fp);


        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        var data = JSON.stringify(obj);
        xhttp.send(data);

        //non persistent-field
        wpc_mouseposition = [];
        wpc_click_tracker = [];
        di = [];
        lpi = [];
    }
}


function isEmpty(obj) {
    for (var prop in obj) {
        if (obj.hasOwnProperty(prop))
            return false;
    }

    return JSON.stringify(obj) === JSON.stringify({});
}
