if (window.console) {
    console.log("Welcome to your Play application's JavaScript!");
}

window.onscroll = function() {stickyHeader()};

var header = document.getElementById("header");

var sticky = header.offsetTop;

function stickyHeader() {
    console.log("scroll")
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky-scroll");
    } else {
        header.classList.remove("sticky-scroll");
    }
}