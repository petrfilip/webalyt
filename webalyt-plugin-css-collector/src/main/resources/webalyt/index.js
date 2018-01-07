var cssCollector = {
    shortName: "css-collector",
    fullName: "CSS collector",
    urls: [],
    methodBody: function () {
        //on load
        window.addEventListener('load', function (e) {
            console.log("css collecting is not implemented");
        });

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

webalyt.addPlugin(cssCollector);

