var scrollRecorder = {
    shortName: "scroll-tracker",
    fullName: "Scroll tracker",
    scrollList: [],
    methodBody: function () {

        window.addEventListener('load', function (e) {
            var wri = {};
            wri.timestamp = new Date();
            var o = {
                wri: wri,
                x: Math.round(window.pageXOffset || document.documentElement.scrollLeft),
                y: Math.round(window.pageYOffset || document.documentElement.scrollTop)
            };

            scrollRecorder.scrollList.push(o);

        });


        window.addEventListener('scroll', function (e) {
            var wri = {};
            wri.timestamp = new Date();
            var o = {
                wri: wri,
                x: Math.round(this.scrollX),
                y: Math.round(this.scrollY)
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