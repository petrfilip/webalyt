var scrollRecorder = {
    shortName: "scroll-recorder",
    fullName: "Scroll recorder",
    scrollList: [],
    methodBody: function () {

        window.addEventListener('load', function (e) {
            var wri = {};
            wri.timestamp = new Date();
            var o = {
                wri: wri,
                top: Math.round(window.pageYOffset || document.documentElement.scrollTop),
                left: Math.round(window.pageXOffset || document.documentElement.scrollLeft)
            };

            scrollRecorder.scrollList.push(o);

        });


        window.addEventListener('scroll', function (e) {
            var wri = {};
            wri.timestamp = new Date();
            var o = {
                wri: wri,
                top: Math.round(this.scrollY),
                left: Math.round(this.scrollX)
            };

            scrollRecorder.scrollList.push(o);

        });
    },
    onInit: function () {
        this.scrollList = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.scrollList;
    }
};
webalyt.addPlugin(scrollRecorder);