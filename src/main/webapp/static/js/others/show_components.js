var monitor_div = document.getElementById('monitor_div');
var ups_div = document.getElementById('ups_div');
var keyboard_div = document.getElementById('keyboard_div');
var mouse_div = document.getElementById('mouse_div');
var monitorIcon = document.getElementById('mon_icon');
var upsIcon = document.getElementById('ups_icon');

document.getElementById('mon_icon').onclick = function() {
    document.getElementById('monitor_div').style.display = 'block';
    document.getElementById('ups_div').style.display = 'none';
    document.getElementById('mouse_div').style.display = 'none';
    document.getElementById('keyboard_div').style.display = 'none';
}

document.getElementById('ups_icon').onclick = function () {
    document.getElementById('ups_div').style.display = 'block';
    document.getElementById('monitor_div').style.display = 'none';
    document.getElementById('mouse_div').style.display = 'none';
    document.getElementById('keyboard_div').style.display = 'none';
}

document.getElementById('mouse_icon').onclick = function () {
    document.getElementById('mouse_div').style.display = 'block';
    document.getElementById('ups_div').style.display = 'none';
    document.getElementById('monitor_div').style.display = 'none';
    document.getElementById('keyboard_div').style.display = 'none';
}

document.getElementById('key_icon').onclick = function () {
    document.getElementById('keyboard_div').style.display = 'block';
    document.getElementById('monitor_div').style.display = 'none';
    document.getElementById('mouse_div').style.display = 'none';
    document.getElementById('ups_div').style.display = 'none';
}