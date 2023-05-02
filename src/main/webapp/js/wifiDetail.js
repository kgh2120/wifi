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

let wifiId;
let distanceId;
let groupId;

function fetchWifiDetail(historyId, wifiName){
  this.wifiId = wifiName;
  fetch(`/wifi/detail?historyId=${historyId}&wifiId=${wifiName}`)
  .then(res => res.json())
  .then(data => {
    let rows = document.getElementsByClassName("table-row");
    let keys = Object.keys(data);
    let rowIdx = 0;
    let keyIdx = 0;
    this.distanceId = data.distanceId;
    while(keyIdx < keys.length){
      if(keys[keyIdx] === "distanceId"){
        keyIdx++;
        continue;
      }
      let tableData = document.createElement("td");
      if(keys[keyIdx] === "distance")
        tableData.innerText = data[keys[keyIdx++]].toFixed(4) + "km";
      else
        tableData.innerText = data[keys[keyIdx++]];
      rows[rowIdx++].append(tableData);
    }
  })

}
function fetchBookmarkGroup(){
  fetch(`/bookmark-group`)
  .then(res => res.json())
  .then(data => {
    let selector = document.querySelector(".bookmark-group-selector");
    data.forEach(group => {
      let option = document.createElement("option");
      option.innerText = group.name;
      option.value = group.id;
      selector.append(option);
    })
  })
}

const onAddBookMarkButtonClicked = () => {
  let selector = document.querySelector(".bookmark-group-selector");
  let option = selector.selectedOptions[0];

  this.groupId = option.value;
  console.log(this.wifiId)


  fetch(`/bookmark`,{
    method : "POST",
    headers : {
      'Content-Type': 'application/json',
    },
    body : JSON.stringify({
      distanceId : this.distanceId,
      wifiId : this.wifiId,
      bookmarkGroupId : this.groupId
    })
  }).then(() => alert("북마크가 추가되었습니다."))

}

function init(){
  let queryString = getQueryStringObject();
  fetchWifiDetail(queryString.historyId,queryString.wifiId);
  fetchBookmarkGroup()
  document.querySelector(".bookmark-add-btn")
  .addEventListener('click', onAddBookMarkButtonClicked);

}

init();