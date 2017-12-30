var Webalyt =  function(webalytUrlTargetAddress) {

    var wpo_fp = [];
    new Fingerprint2().get(function (result, components) {
        var fpo = {};
        fpo.fp = result;
        //fpo.t = new Date();
        wpo_fp.push(fpo);
    });

    var webalytPlugins = [];


    this.addPlugin = function (plugin) {
        webalytPlugins.push(plugin);
        plugin.methodBody();
        plugin.onInit();
    };

    setInterval(function () {
        var obj = {};
        for (var i = 0; i < webalytPlugins.length; i++) {
            obj = mergeData(obj, webalytPlugins[i].shortName, webalytPlugins[i].getDataForSending());
        }
        sendData(obj);
        for (var j = 0; j < webalytPlugins.length; j++) {
            webalytPlugins[j].onAfterSend();
        }
    }, 3000);

    function mergeData(obj, plugin, message) {
        if (message !== undefined && message.length > 0) {
            obj[plugin] = message;
        }
        return obj;
    }

    function sendData(obj) {
        if (!isEmpty(obj)) {
            //persistent field
            obj = mergeData(obj, "WCdevId", wpo_fp);

            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", webalytUrlTargetAddress, true);
            xhttp.setRequestHeader("Content-type", "application/json");
            var data = JSON.stringify(obj);
            xhttp.send(data);
        }
    }

    function isEmpty(obj) {
        for (var prop in obj) {
            if (obj.hasOwnProperty(prop))
                return false;
        }
        return JSON.stringify(obj) === JSON.stringify({});
    }

    // function createWebalytRecordIdentifier() {
    //
    // }
};

var webalyt = new Webalyt("http://localhost:8080/");
