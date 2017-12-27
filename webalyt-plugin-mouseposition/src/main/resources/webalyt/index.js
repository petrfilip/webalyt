
var wpc_mouseposition = [];
document.body.addEventListener('mousemove', function (e) {
    var mp = {};
    // mp.t = new Date();
    mp.x = e.clientX;
    mp.y = e.clientY;
    wpc_mouseposition.push(mp);
});

