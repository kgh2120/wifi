



const fetchSeoulWifiData = () => {
  const loading = document.querySelector(".wifi-loading-wrapper");
  const context = document.querySelector(".wifi-wrapper");
  fetch("/wifi",{
    method : "post"
  })
  .then(response => response.json())
  .then(data => {
    const result = document.querySelector('.wifi-result');
    console.log(data);
    context.style.display = "flex";
    loading.style.display = "none";
    result.innerText = `${data.count}개의 WIFi 정보를 정상적으로 저장하였습니다.`;
  })
  .catch(err => console.error(err));
}


function init(){
  fetchSeoulWifiData();
}

init();