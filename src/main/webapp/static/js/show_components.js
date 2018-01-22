var monitorIcon = document.getElementById('mon_icon');
var upsIcon = document.getElementById('ups_icon');

monitorIcon.onclick = function() {
    document.getElementById('monitor_div').style.display = 'block';
}

upsIcon.onclick = function () {
    document.getElementById('monitor_div').style.display = 'none';
}