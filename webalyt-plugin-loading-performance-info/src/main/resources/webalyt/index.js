var onLoadPerformanceInfo = {
    shortName: "lpi",
    fullName: "Performance information about page loading",
    performanceInformations: [],
    methodBody: function () {
        window.addEventListener('load', function (e) {
            var timing = window.performance.timing;
            var userTime = timing.loadEventEnd - timing.navigationStart;
            var dns = timing.domainLookupEnd - timing.domainLookupStart;
            var connection = timing.connectEnd - timing.connectStart;
            var requestTime = timing.responseEnd - timing.requestStart;
            var fetchTime = timing.responseEnd - timing.fetchStart;

            var pl = {};
            var wri = {};
            wri.timestamp = new Date();
            pl.wri = wri;
            pl.userTime = userTime;
            pl.dns = dns;
            pl.connection = connection;
            pl.requestTime = requestTime;
            pl.fetchTime = fetchTime;

            onLoadPerformanceInfo.performanceInformations.push(pl);


        });
    },
    onInit: function () {
        this.performanceInformations = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.performanceInformations;
    }
};
webalyt.addPlugin(onLoadPerformanceInfo);