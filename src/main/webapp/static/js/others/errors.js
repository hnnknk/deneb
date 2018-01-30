function handleErrors (errorType) {

    if(errorType == 409) {
        $(".confalert").removeClass("in").show();
        $(".confalert").delay(1000).addClass("in").fadeOut(4000)
    } else if (errorType == 400) {
        $(".badalert").removeClass("in").show();
        $(".badalert").delay(1000).addClass("in").fadeOut(4000)
    }
}

function handleSuccessCreate() {
    $(".alert").removeClass("in").show();
    $(".alert").delay(1000).addClass("in").fadeOut(4000)
}

function handleSuccessUpdate() {
    $(".upalert").removeClass("in").show();
    $(".upalert").delay(1000).addClass("in").fadeOut(4000)
}