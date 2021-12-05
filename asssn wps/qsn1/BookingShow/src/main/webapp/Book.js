/**
 * 
 */
var x = document.getElementById("demo");

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(showPosition);
  } else { 
    x.innerHTML = "Geolocation is not supported by this browser.";
  }
}

function showPosition(position) {
    lat=position.coords.latitude;
    lon=position.coords.longitude;
    displayLocation(lat,lon);
}
function displayLocation(latitude,longitude){
    var geocoder;
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(latitude, longitude);

    geocoder.geocode(
        {'latLng': latlng}, 
        function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    var add= results[0].formatted_address ;
                    var  value=add.split(",");

                    count=value.length;
                    country=value[count-1];
                    state=value[count-2];
                    city=value[count-3];
                     var hiddencity=document.getElementById("city1");
                     hiddencity.value=city;
                     x.innerHTML="city";
  }
                else  {
                    x.innerHTML = "address not found";
                }
            }
            else {
                x.innerHTML =  status;
            }
        }
    );
}