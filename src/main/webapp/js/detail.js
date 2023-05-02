function getQueryStringObject() {
  var a = window.location.search.substring(1).split('&');
  if (a == "") return {};
  var b = {};
  for (var i = 0; i < a.length; ++i) {
    var p = a[i].split('=', 2);
    if (p.length == 1)
      b[p[0]] = "";
    else
      b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
  }
  return b;
}

function fetchWifiDetail(historyId, wifiName){

  fetch(`/wifi/detail?historyId=${historyId}&wifiId=${wifiName}`)
  .then(res => res.json())
  .then(data => {
    let rows = document.getElementsByClassName("table-row");
    let keys = Object.keys(data);
    let rowIdx = 0;
    let keyIdx = 0;
    while(keys.length){
      if(keys[keyIdx] === "distanceId"){
        keyIdx++;
        continue;
      }
      console.log(data)

      let tableData = document.createElement("td");
      if(keys[keyIdx] === "distance")
        tableData.innerText = data[keys[keyIdx++]].toFixed(4) + "km";
      else
        tableData.innerText = data[keys[keyIdx++]];
      rows[rowIdx++].append(tableData);
    }
  })

}


function init(){
  let queryString = getQueryStringObject();
  fetchWifiDetail(queryString.historyId,queryString.wifiId);

}

init();