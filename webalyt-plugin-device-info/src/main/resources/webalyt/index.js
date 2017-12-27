var di = [];
window.addEventListener('load', function (e) {
    var o = {
        t: new Date(),
        sizeScreenW: screen.width,
        sizeScreenH: screen.height,
        sizeDocW: document.width,
        sizeDocH: document.height,
        sizeClientW: document.body.clientWidth,
        sizeClientH: document.body.clientHeight,
        sizeInW: window.innerWidth,
        sizeInH: window.innerHeight,
        sizeAvailW: screen.availWidth,
        sizeAvailH: screen.availHeight,
        zoom: getZoomLevel(),
        scrColorDepth: screen.colorDepth,
        scrPixelDepth: screen.pixelDepth
    };

    di.push(o);
});


function getZoomLevel() {
    ratio = document.body.clientWidth / window.innerWidth;
    return ratio;
}




