let selected_tile = null;
let tiles = null;
let selected_tile_label = null;

$(document).ready(function () {
    selected_tile_label = document.getElementById("selected_tile_label");
    tiles = Array.from(document.getElementsByClassName("rtile"));
    tiles.forEach(function (item, idx, array) {
        item.setAttribute("onclick", "click_tile(this)")
    });
});

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



function click_tile(tile){
    if (selected_tile == null) {
        console.log("first click -> selected was null")
        if (!tile.textContent.trim() == "") {
            // String not empty or blank
            console.log("textcontent: ", tile.textContent.trim())

            selected_tile = tile;
            showSelectedTile(tile);
        }
    } else if (selected_tile == tile) {
        console.log("selected = tile")
        selected_tile = null
        invisibleSelectedTile()
    } else {
        console.log("second click -> selected is set")

        if (tile.textContent.trim() == "") {
            let sf = selected_tile.getAttribute("name").split("*")
            let c = tile.getAttribute("name").split("*")
            let redirectTo = "/moveTile/" + getColLetter(sf[1]) + sf[0] + "->"
                + getColLetter(c[1]) + c[0];
            console.log("redirect to " + redirectTo);
            window.location.replace(redirectTo);
        } else {
            selected_tile = tile;
            showSelectedTile(tile);
        }
    }
}

function getColLetter(col) {
    return String.fromCharCode(64 + parseInt(col));
}

function showSelectedTile(tile) {
    let split = tile.getAttribute("name").split("*");
    selected_tile_label.textContent = tile.textContent.trim() + " @ " +
        getColLetter(split[1]) + split[0];
    selected_tile_label.classList.remove("invisible")
}

function invisibleSelectedTile(text) {
    selected_tile_label.classList.add("invisible")
}