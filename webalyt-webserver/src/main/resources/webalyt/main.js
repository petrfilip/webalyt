var Webalyt =  function(webalytUrlTargetAddress) {


    //https://jsperf.com/uuid-generator-opt/4
    var lut = []; for (var i=0; i<256; i++) { lut[i] = (i<16?'0':'')+(i).toString(16); }
    function e5() {
        var k=['x','x','x','x','-','x','x','-','4','x','-','y','x','-','x','x','x','x','x','x'];
        var u='',i=0,rb=Math.random()*0xffffffff|0;
        while(i++<20) {
            var c=k[i-1],r=rb&0xff,v=c=='x'?r:(c=='y'?(r&0x3f|0x80):(r&0xf|0x40));
            u+=(c=='-')?c:lut[v];rb=i%4==0?Math.random()*0xffffffff|0:rb>>8
        }
        return u
    }

    function e7()
    {
        var d0 = Math.random()*0xffffffff|0;
        var d1 = Math.random()*0xffffffff|0;
        var d2 = Math.random()*0xffffffff|0;
        var d3 = Math.random()*0xffffffff|0;
        return lut[d0&0xff]+lut[d0>>8&0xff]+lut[d0>>16&0xff]+lut[d0>>24&0xff]+'-'+
            lut[d1&0xff]+lut[d1>>8&0xff]+'-'+lut[d1>>16&0x0f|0x40]+lut[d1>>24&0xff]+'-'+
            lut[d2&0x3f|0x80]+lut[d2>>8&0xff]+'-'+lut[d2>>16&0xff]+lut[d2>>24&0xff]+
            lut[d3&0xff]+lut[d3>>8&0xff]+lut[d3>>16&0xff]+lut[d3>>24&0xff];
    }


    var pageViewId = e7();

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
            obj = mergeData(obj, "pageViewId", pageViewId);

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
};

var webalyt = new Webalyt("http://localhost:8080/");
