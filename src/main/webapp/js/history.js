function deleteHistory(id){
  if(confirm("정말로 삭제하시겠습니까?")){
    fetch(`/history?id=${id}`,{
      method : 'DELETE'
    }).then(() => window.location.reload())
    .catch(err => console.error(err));
  }
}

function fetchHistory(){
  const tableBody = document.querySelector('.history-body');
  fetch(`/history`)
    .then(res => res.json())
    .then(data => {

      data.forEach(h => {
        let row = document.createElement("tr");
        let id = document.createElement("td");
        id.innerText = h.id;
        let lat = document.createElement("td");
        lat.innerText = h.lat;
        let lnt = document.createElement("td");
        lnt.innerText = h.lnt;
        let createdAt = document.createElement("td");
        createdAt.innerText = h.createdAt;
        let buttonWrapper = document.createElement("td");
        let button = document.createElement("button");
        button.innerText = "삭제"
        button.addEventListener('click',() => deleteHistory(h.id));
        
        buttonWrapper.append(button);
        row.append(id);
        row.append(lat);
        row.append(lnt);
        row.append(createdAt);
        row.append(buttonWrapper);
        row.className = "history-body-row"
        tableBody.append(row);
      })

    }).catch(err => console.error(err))
}


function init(){
    fetchHistory();
}

init();