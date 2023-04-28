/* 비동기적으로 현재 위치를 알아내어 지정된 요소에 출력한다. */
function whereami() {
  // 이 객체를 getCurrentPosition() 메서드의 세번째 인자로 전달한다.
  var options = {
    // 가능한 경우, 높은 정확도의 위치(예를 들어, GPS 등) 를 읽어오려면 true로 설정
    // 그러나 이 기능은 배터리 지속 시간에 영향을 미친다.
    enableHighAccuracy: false, // 대략적인 값이라도 상관 없음: 기본값

    // 위치 정보가 충분히 캐시되었으면, 이 프로퍼티를 설정하자,
    // 위치 정보를 강제로 재확인하기 위해 사용하기도 하는 이 값의 기본 값은 0이다.
    maximumAge: 30000,     // 5분이 지나기 전까지는 수정되지 않아도 됨

    // 위치 정보를 받기 위해 얼마나 오랫동안 대기할 것인가?
    // 기본값은 Infinity이므로 getCurrentPosition()은 무한정 대기한다.
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
      for (let i = 0; i<keys.length;i++) {
        let td = document.createElement("td");
        if(keys[i] === "distance"){

          td.innerText = d[keys[i]].toFixed(4) + "km";
        }else{
          td.innerText = d[keys[i]];
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