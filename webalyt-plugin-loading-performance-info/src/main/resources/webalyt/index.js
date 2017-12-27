var lpi = [];
window.addEventListener('load', function (e) {
    var timing = window.performance.timing;
    var userTime = timing.loadEventEnd - timing.navigationStart;
    var dns = timing.domainLookupEnd - timing.domainLookupStart;
    var connection = timing.connectEnd - timing.connectStart;
    var requestTime = timing.responseEnd - timing.requestStart;
    var fetchTime = timing.responseEnd - timing.fetchStart;

    var pl = {};
    //pl.t = new Date();
    pl.userTime = userTime;
    pl.dns = dns;
    pl.connection = connection;
    pl.requestTime = requestTime;
    pl.fetchTime = fetchTime;

    lpi.push(pl);

});
