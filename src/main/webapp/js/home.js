/* 비동기적으로 현재 위치를 알아내어 지정된 요소에 출력한다. */
function whereami() {
  // 이 객체를 getCurrentPosition() 메서드의 세번째 인자로 전달한다.
  var options = {
    enableHighAccuracy: false, // 대략적인 값이라도 상관 없음: 기본값
    maximumAge: 30000,     // 5분이 지나기 전까지는 수정되지 않아도 됨

    timeout: 15000    // 15초 이상 기다리지 않는다.
  }

  if(navigator.geolocation) // geolocation 을 지원한다면 위치를 요청한다.
    navigator.geolocation.getCurrentPosition(success, error, options);
  else
    console.log("이 브라우저에서는 Geolocation이 지원되지 않습니다.")


  // geolocation 요청이 실패하면 이 함수를 호출한다.
  function error(e) {
    console.log("Geolocation 오류 "+e.code +": " + e.message);
  }

  function success(pos) {
    document.getElementById("LAT").value = pos.coords.latitude
    document.getElementById("LNT").value =   pos.coords.longitude

  }
}

const getLocationEvent = () => {
  whereami();
}

const getNearWifiEvent = () => {
  let lat = document.getElementById("LAT").value;
  let lnt = document.getElementById("LNT").value;
  fetch("/near", {
    method : "POST",
    headers : {
      'Content-Type': 'application/json',
    },
    body : JSON.stringify({
      lat : lat, lnt : lnt
    })
  }).then(res => res.json())
  .then(data => {
    let body = document.querySelector(".wifi-body");
    let before = document.querySelector(".wifi-table-before-retrieve");

    data.forEach(d => {
      let tr = document.createElement("tr");
      let keys = Object.keys(d);
      tr.classList.add("table-row");
      for (const element of keys) {
        let td = document.createElement("td");
        if (element === "history_id") {
          continue;
        }

        if (element === "distance") {
          td.innerText = d[element].toFixed(4) + "km";
        } else if (element === "X_SWIFI_MAIN_NM") {
          const anchor = document.createElement("a");
          anchor.innerText = d[element];
          anchor.href = `/view/wifiDetail.jsp?historyId=${d.history_id}&wifiId=${d.X_SWIFI_MGR_NO}`
          td.append(anchor);
        } else {
          td.innerText = d[element];
        }
        tr.append(td);
      }
      body.append(tr);
    })
    before.style.display = 'none';

  })
  .catch(err => console.error(err))

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