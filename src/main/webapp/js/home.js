

const getLocationEvent = () => {
  alert("Give Me Location!!");

}

const getNearWifiEvent = () => {
  alert("Give Me Wifi Info!!")
}

function addEventToLocationBtn() {
  const locationBtn = document.getElementById("my-location-btn");
  locationBtn.addEventListener('click', getLocationEvent)
}

function addEventToNearWifiBtn() {
  const nearWifiBtn = document.getElementById("near_wifi_btn");
  nearWifiBtn.addEventListener('click', getNearWifiEvent);
}

const init = () =>  {
  addEventToLocationBtn();
  addEventToNearWifiBtn();
}

init();