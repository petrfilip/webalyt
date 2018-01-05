var resizeRecorder = {
    shortName: "resize-recorder",
    fullName: "Record resizing",
    sizes: [],
    methodBody: function () {
        //on load
        window.addEventListener('load', function (e) {
            processData();
        });

        //on size changed
        window.addEventListener('resize', function (e) {
            processData();
        });

        //on device orientation changed
        window.addEventListener('orientationchange', function (e) {
            processData();
        });

        function processData() {
            var wri = {};
            wri.timestamp = new Date();
            var o = prepareData(wri);
            resizeRecorder.sizes.push(o);
        }

        function prepareData(wri) {
            var orientation = screen.orientation || screen.mozOrientation || screen.msOrientation;

            return {
                wri: wri,
                width: window.innerWidth,
                height: window.innerHeight,
                zoom: document.body.clientWidth / window.innerWidth,
                orientation: orientation.type
            };
        }


    },
    onInit: function () {
        this.sizes = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.sizes;
    }

};

webalyt.addPlugin(resizeRecorder);

