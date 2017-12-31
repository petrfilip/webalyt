var urlRecorder = {
    shortName: "url-recorder",
    fullName: "Record url",
    urls: [],
    methodBody: function () {
        //on load
        window.addEventListener('load', function (e) {
            var wri = {};
            wri.timestamp = new Date();

            var o = {
                wri: wri,
                fullUrl: window.location.href,
                pathName: window.location.pathname,
                hash: window.location.hash,
                search: window.location.search
            };

            urlRecorder.urls.push(o);
        });

        //todo on address/hash changed

    },
    onInit: function () {
        this.urls = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.urls;
    }

};

webalyt.addPlugin(urlRecorder);

