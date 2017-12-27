var wpc_click_tracker = [];
document.body.addEventListener('click', function (e) {
    var cp = {};
    // cp.t = new Date();
    cp.x = e.clientX;
    cp.y = e.clientY;
    wpc_click_tracker.push(cp);
});
