var mousePositionPlugin = {
    shortName: "mp",
    fullName: "Mouse position plugin",
    mousePositions: [],
    methodBody: function () {
        console.log(this.mousePositions.length);
        document.body.addEventListener('mousemove', function (e) {
            var mp = {};
            var wri = {};
            wri.timestamp = new Date();
            mp.wri = wri;
            mp.x = e.clientX;
            mp.y = e.clientY;
            mousePositionPlugin.mousePositions.push(mp);
        });
    },
    onInit: function () {
        this.mousePositions = [];
    },
    onAfterSend: function () {
        this.onInit();
    },
    getDataForSending: function () {
        return this.mousePositions;
    }
};
webalyt.addPlugin(mousePositionPlugin);